package com.boulhosa.bffagendadortarefa.business;

import com.boulhosa.bffagendadortarefa.business.dto.out.TarefaDTOResponse;
import com.boulhosa.bffagendadortarefa.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient notificacaoClient;


    public void enviarEmail(TarefaDTOResponse tarefaDTOResponse) {

        notificacaoClient.enviarEmail(tarefaDTOResponse);

    }
}
