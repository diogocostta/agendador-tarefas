package com.diogocosta.agendadortarefas.business;

import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.business.mapper.TarefasConverter;
import com.diogocosta.agendadortarefas.business.mapper.TarefasUpdateConverter;
import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.diogocosta.agendadortarefas.infrastructure.enums.StatusNoficacaoEnum;
import com.diogocosta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.diogocosta.agendadortarefas.infrastructure.repository.TerefasRepository;
import com.diogocosta.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TerefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {

        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataEvento(LocalDateTime.now());
        dto.setStatusNoficacaoEnum(StatusNoficacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween
                (dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deleTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por ID, ID inexistente "
                    + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNoficacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNoficacaoEnum(status);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause()); //8:30
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa nãoe encontrada " + id));
            tarefasUpdateConverter.updateTarefas(dto, entity);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}
