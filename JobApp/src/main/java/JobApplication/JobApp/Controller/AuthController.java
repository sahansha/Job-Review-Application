package JobApplication.JobApp.Controller;

import JobApplication.JobApp.DTO.LoginRequestDTO;
import JobApplication.JobApp.DTO.LoginResponseDTO;
import JobApplication.JobApp.DTO.RegisterUserDTO;
import JobApplication.JobApp.Model.AppUser;
import JobApplication.JobApp.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/createnormaluser")
    public ResponseEntity<AppUser> createNormalUser(@RequestBody RegisterUserDTO registerUserDTO)
    {
        return this.authService.createNormalUser(registerUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        return this.authService.login(loginRequestDTO);
    }
}
