package com.boulhosa.bffagendadortarefa.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {

    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
}
