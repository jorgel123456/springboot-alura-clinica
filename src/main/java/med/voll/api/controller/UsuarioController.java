package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DTOAutenticacionAusuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.AutenticacionService;
import med.voll.api.infra.security.DTOJwtToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DTOAutenticacionAusuario dtoAutenticacionAusuario){
        System.out.println(dtoAutenticacionAusuario.login() + dtoAutenticacionAusuario.clave());
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(dtoAutenticacionAusuario.login(),dtoAutenticacionAusuario.clave());
       var usuarioAutenticado= authenticationManager.authenticate(AuthToken);
        var jwtToken=tokenService.generartoken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(jwtToken);
        return ResponseEntity.ok(new DTOJwtToken(jwtToken));
    }

}
