package com.boulhosa.bffagendadortarefa.infrastructure.client;

import com.boulhosa.bffagendadortarefa.business.dto.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse tarefaDTOResponse);

}
