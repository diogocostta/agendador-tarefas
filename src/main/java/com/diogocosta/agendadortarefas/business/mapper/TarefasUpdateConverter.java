package com.diogocosta.agendadortarefas.business.mapper;

import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    //Está dizendo que o a "entity" será o princiapl, caso o "dto" esteja null.
    void updateTarefas (TarefasDTO dto,@MappingTarget TarefasEntity entity);
}
