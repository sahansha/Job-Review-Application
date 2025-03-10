package JobApplication.JobApp.Service;

import JobApplication.JobApp.DAO.AppUserRepository;
import JobApplication.JobApp.DTO.LoginRequestDTO;
import JobApplication.JobApp.DTO.LoginResponseDTO;
import JobApplication.JobApp.DTO.RegisterUserDTO;
import JobApplication.JobApp.Exception.NotFoundException;
import JobApplication.JobApp.JWT.JWTService;
import JobApplication.JobApp.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public ResponseEntity<AppUser> findByUsername(String username)
    {
        try{
            AppUser user=this.appUserRepository.findByUsername(username)
                    .orElseThrow(()->new NotFoundException(String.format("User with username %s is not found",username)));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<AppUser> createNormalUser(RegisterUserDTO registerUserDTO) {
        try{
            List<String> roles=new ArrayList<>();
            roles.add("ROLE_USER");
            AppUser appUser=AppUser.builder()
                    .email(registerUserDTO.getEmail())
                    .username(registerUserDTO.getUsername())
                    .password(passwordEncoder.encode( registerUserDTO.getPassword()))
                    .roles(roles)
                    .build();
            AppUser addedUser=this.appUserRepository.save(appUser);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedUser.getUserid())
                    .toUri();
            return ResponseEntity.created(location).body(addedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<AppUser> createAdminUser(RegisterUserDTO registerUserDTO) {
        try {
            List<String> roles=new ArrayList<>();
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");
            AppUser appUser=AppUser.builder()
                    .email(registerUserDTO.getEmail())
                    .password(passwordEncoder.encode( registerUserDTO.getPassword()))
                    .username(registerUserDTO.getUsername())
                    .roles(roles)
                    .build();
            AppUser addedUser=this.appUserRepository.save(appUser);
            URI location=ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(addedUser.getUserid())
                    .toUri();
            return ResponseEntity.created(location).body(addedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername()
                    ,loginRequestDTO.getPassword()));

            String token=this.jwtService.generateToken(loginRequestDTO.getUsername());
            LoginResponseDTO loginResponseDTO=LoginResponseDTO.builder()
                    .token(token)
                    .username(loginRequestDTO.getUsername())
                    .build();
            return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
