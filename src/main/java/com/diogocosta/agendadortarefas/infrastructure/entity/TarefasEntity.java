package com.diogocosta.agendadortarefas.infrastructure.entity;

import com.diogocosta.agendadortarefas.infrastructure.enums.StatusNoficacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("tafera")
public class TarefasEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataEvento;
    private LocalDateTime localEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNoficacaoEnum statusNoficacaoEnum;
}
