package com.boulhosa.bffagendadortarefa.infrastructure.client;


import com.boulhosa.bffagendadortarefa.business.dto.out.TarefaDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.in.TarefaDTORequest;
import com.boulhosa.bffagendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${tarefas.url}")
public interface TarefaClient {

    @PostMapping
    public TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest tarefaDTORequest,
                                          @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/evento")
    public List<TarefaDTOResponse> buscaTarefaAgendadaPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                                                                 @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    public List<TarefaDTOResponse> buscarTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    public void deletarTarefaPorId(@RequestParam("id") String id,
                                   @RequestHeader(name = "Authorization", required = false) String token);


    @PatchMapping
    public TarefaDTOResponse alterarStatus(@RequestParam("status") StatusNotificacaoEnum status,
                                           @RequestParam("id") String id,
                                           @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    public TarefaDTOResponse alterarTarefa(@RequestParam("id") String id,
                                           @RequestBody TarefaDTORequest tarefaDTORequest,
                                           @RequestHeader(name = "Authorization", required = false) String token);

}