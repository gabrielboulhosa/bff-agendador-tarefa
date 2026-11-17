package com.boulhosa.bffagendadortarefa.infrastructure.client;


import com.boulhosa.bffagendadortarefa.business.dto.in.EnderecoDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.LoginRequestDto;
import com.boulhosa.bffagendadortarefa.business.dto.in.TelefoneDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.UsuarioDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.out.EnderecoDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.TelefoneDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {


    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDto loginRequestDto);

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(name = "Authorization", required = false) String token);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosDeUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader(name = "Authorization", required = false) String token);



    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader(name = "Authorization", required = false) String token);



    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader(name = "Authorization", required = false) String token);

}
