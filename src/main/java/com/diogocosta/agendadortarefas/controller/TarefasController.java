package com.diogocosta.agendadortarefas.controller;


import com.diogocosta.agendadortarefas.business.TarefasService;
import com.diogocosta.agendadortarefas.business.dto.TarefasDTO;
import com.diogocosta.agendadortarefas.infrastructure.enums.StatusNoficacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefas(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalDateTime dataInical,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInical, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);

    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id) {
        tarefasService.deleTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNoficacaoEnum status,
                                                              @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }

    @PutMapping
    ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
    }

}
