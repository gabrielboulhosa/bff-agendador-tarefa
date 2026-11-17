package com.boulhosa.bffagendadortarefa.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {

    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
}
