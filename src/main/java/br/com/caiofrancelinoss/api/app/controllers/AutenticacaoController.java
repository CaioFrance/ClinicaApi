package br.com.caiofrancelinoss.api.app.controllers;

import br.com.caiofrancelinoss.api.app.dto.DadosAutenticacao;
import br.com.caiofrancelinoss.api.app.dto.DadosTokenJWTDto;
import br.com.caiofrancelinoss.api.app.services.TokenService;
import br.com.caiofrancelinoss.api.domain.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var jwtToken = tokenService.gerarToken((Usuario) authentication);

        return ResponseEntity.ok(new DadosTokenJWTDto(jwtToken));
    }

}
