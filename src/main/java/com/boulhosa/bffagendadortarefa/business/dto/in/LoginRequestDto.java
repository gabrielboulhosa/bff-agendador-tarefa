package com.boulhosa.bffagendadortarefa.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String email;
    private String senha;
}
