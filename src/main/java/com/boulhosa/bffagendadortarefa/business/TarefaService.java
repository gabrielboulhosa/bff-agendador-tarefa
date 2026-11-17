package com.boulhosa.bffagendadortarefa.business;


import com.boulhosa.bffagendadortarefa.business.dto.out.TarefaDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.in.TarefaDTORequest;
import com.boulhosa.bffagendadortarefa.infrastructure.client.TarefaClient;
import com.boulhosa.bffagendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

   private final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa(String token, TarefaDTORequest tarefaDTORequest) {
        return tarefaClient.gravarTarefa(tarefaDTORequest, token);
    }

    public List<TarefaDTOResponse> buscaTarefaAgendadaPorPeriodo(LocalDateTime dataInicio,
                                                                 LocalDateTime dataFim,
                                                                 String token) {

        return tarefaClient.buscaTarefaAgendadaPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefaDTOResponse> buscaTarefaPorEmail(String token) {
        return tarefaClient.buscarTarefaPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
        tarefaClient.deletarTarefaPorId(id, token);
    }

    public TarefaDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alterarStatus(status, id, token);
    }

    public TarefaDTOResponse alterarTarefa(String id, TarefaDTORequest tarefaDTORequest, String token) {
        return tarefaClient.alterarTarefa(id, tarefaDTORequest, token);
    }
}
