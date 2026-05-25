package com.diogocosta.agendadortarefas.infrastructure.repository;

import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TerefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween (LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario (String email);

}
