package com.boulhosa.bffagendadortarefa.controller;


import com.boulhosa.bffagendadortarefa.business.UsuarioServise;
import com.boulhosa.bffagendadortarefa.business.dto.in.EnderecoDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.LoginRequestDto;
import com.boulhosa.bffagendadortarefa.business.dto.in.TelefoneDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.in.UsuarioDTORequest;
import com.boulhosa.bffagendadortarefa.business.dto.out.EnderecoDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.TelefoneDTOResponse;
import com.boulhosa.bffagendadortarefa.business.dto.out.UsuarioDTOResponse;
import com.boulhosa.bffagendadortarefa.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioServise usuarioServise;


    @PostMapping
    @Operation(summary = "Salva um novo usuário", description = "Salva um novo usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){

        return ResponseEntity.ok(usuarioServise.salvaUsuario(usuarioDTO));

    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Realiza o login de um usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDto loginRequestDto){
        return usuarioServise.loginUsuario(loginRequestDto);
    }

    @GetMapping
    @Operation(summary = "Busca um usuário por email", description = "Busca um usuário no sistema pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(usuarioServise.buscarPorEmail(email, token));

    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta um usuário por email", description = "Deleta um usuário no sistema pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioServise.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosDeUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServise.atualizaDadosDeUsuario(token, usuarioDTO));

    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza um endereço", description = "Atualiza um endereço no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServise.atualizaEndereco(id, enderecoDTORequest, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza um telefone", description = "Atualiza um telefone no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestParam("id") Long id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServise.atualizaTelefone(id, telefoneDTORequest, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva um novo endereço", description = "Salva um novo endereço no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServise.cadastraEndereco(enderecoDTORequest, token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva um novo telefone", description = "Salva um novo telefone no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServise.cadastraTelefone(telefoneDTORequest, token));
    }

}
