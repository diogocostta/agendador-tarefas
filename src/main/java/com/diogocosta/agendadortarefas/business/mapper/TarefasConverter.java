package com.diogocosta.agendadortarefas.business.mapper;

import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity (TarefasDTO dto);

    TarefasDTO paraTarefasDTO (TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO (List<TarefasEntity> entities);

}
