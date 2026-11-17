package com.boulhosa.bffagendadortarefa.business;

import com.boulhosa.bffagendadortarefa.business.dto.in.EnderecoDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.LoginRequestDto;
import com.boulhosa.bffagendadortarefa.business.dto.in.TelefoneDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.UsuarioDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.out.EnderecoDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.TelefoneDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.UsuarioDTOResponse;
import com.boulhosa.bffagendadortarefa.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServise {

    private final UsuarioClient usuarioClient;


    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDto loginRequestDto){
        return usuarioClient.login(loginRequestDto);
    }

    public UsuarioDTOResponse buscarPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosDeUsuario(String token, UsuarioDTORequest usuarioDTO){
        return usuarioClient.atualizaDadosDeUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizaEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTOResponse cadastraEndereco(EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }

}
