package tr.folksdev.kdgms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.folksdev.kdgms.dto.JwtResponse;
import tr.folksdev.kdgms.dto.RegisterRequest;
import tr.folksdev.kdgms.dto.SigninRequest;
import tr.folksdev.kdgms.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/1.0/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "signup")
    public ResponseEntity<?>registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping(value = "signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody SigninRequest signinRequest){
        return authService.authenticateUser(signinRequest);
    }

}
