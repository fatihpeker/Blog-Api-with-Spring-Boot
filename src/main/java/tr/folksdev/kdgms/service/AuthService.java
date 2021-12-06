package tr.folksdev.kdgms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.folksdev.kdgms.dto.JwtResponse;
import tr.folksdev.kdgms.dto.MyUserDetails;
import tr.folksdev.kdgms.dto.RegisterRequest;
import tr.folksdev.kdgms.dto.SigninRequest;
import tr.folksdev.kdgms.model.User;
import tr.folksdev.kdgms.repository.UserRepository;
import tr.folksdev.kdgms.security.JwtUtils;

@Service
public class AuthService {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?>registerUser(RegisterRequest registerRequest){
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        User user = new User(registerRequest.getUsername(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getName(),
                registerRequest.getSurname());

        userRepository.save(user);

        return ResponseEntity.ok("User registired successfully");

    }

    public ResponseEntity<JwtResponse> authenticateUser(SigninRequest signinRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
    }

}
