package JobApplication.JobApp.Controller;

import JobApplication.JobApp.DTO.RegisterUserDTO;
import JobApplication.JobApp.Model.AppUser;
import JobApplication.JobApp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthService authService;

    @PostMapping("/createadminuser")
    public ResponseEntity<AppUser> addAdminUser(@RequestBody RegisterUserDTO registerUserDTO)
    {
        return this.authService.createAdminUser(registerUserDTO);
    }
}
