package com.boulhosa.bffagendadortarefa.controller;


import com.boulhosa.bffagendadortarefa.business.TarefaService;
import com.boulhosa.bffagendadortarefa.business.dto.out.TarefaDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.in.TarefaDTORequest;
import com.boulhosa.bffagendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.boulhosa.bffagendadortarefa.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro e gerenciamento de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salva uma nova tarefa", description = "Salva uma nova tarefa no sistema")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTORequest,
                                                          @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTORequest));
    }

    @GetMapping("/evento")
    @Operation(summary = "Busca tarefas agendadas por período", description = "Busca as tarefas agendadas do usuário dentro de um período de datas")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaTarefaAgendadaPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaAgendadaPorPeriodo(dataInicio, dataFim, token));

    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email", description = "Busca as tarefas do usuário identificado pelo token")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta uma tarefa por id", description = "Deleta uma tarefa do sistema pelo id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera o status de uma tarefa", description = "Altera o status de notificação de uma tarefa pelo id")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> alterarStatus(@RequestParam("status") StatusNotificacaoEnum status,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza uma tarefa", description = "Atualiza os dados de uma tarefa pelo id")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> alterarTarefa(@RequestParam("id") String id,
                                                           @RequestBody TarefaDTORequest tarefaDTORequest,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alterarTarefa(id, tarefaDTORequest, token));
    }
}
