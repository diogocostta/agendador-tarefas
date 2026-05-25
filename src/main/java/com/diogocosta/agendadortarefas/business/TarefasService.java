package com.diogocosta.agendadortarefas.business;

import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.business.mapper.TarefasConverter;
import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.diogocosta.agendadortarefas.infrastructure.enums.StatusNoficacaoEnum;
import com.diogocosta.agendadortarefas.infrastructure.repository.TerefasRepository;
import com.diogocosta.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TerefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas (String token, TarefasDTO dto){

        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataEvento(LocalDateTime.now());
        dto.setStatusNoficacaoEnum(StatusNoficacaoEnum.PENDENTE);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

}
