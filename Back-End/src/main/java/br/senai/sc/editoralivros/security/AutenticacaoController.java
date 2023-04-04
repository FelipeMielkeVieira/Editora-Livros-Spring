package br.senai.sc.editoralivros.security;

import br.senai.sc.editoralivros.security.users.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/editoralivros/login")
public class AutenticacaoController {

    private TokenUtils tokenUtils = new TokenUtils();

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<Object> autenticacao(
            @RequestBody @Valid UsuarioDTO usuarioDTO,
            HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            UserJpa userJpa = (UserJpa) authentication.getPrincipal();
            response.addCookie(tokenUtils.gerarCookie(authentication));
            return ResponseEntity.status(HttpStatus.OK).body(userJpa.getPessoa());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
