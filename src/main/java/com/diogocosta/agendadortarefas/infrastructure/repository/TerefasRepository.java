package com.diogocosta.agendadortarefas.infrastructure.repository;

import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerefasRepository extends MongoRepository<TarefasEntity, String> {

}
