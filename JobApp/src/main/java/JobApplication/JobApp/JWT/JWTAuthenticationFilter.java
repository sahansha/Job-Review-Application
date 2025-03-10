package JobApplication.JobApp.JWT;

import JobApplication.JobApp.DAO.AppUserRepository;
import JobApplication.JobApp.Service.AuthService;
import JobApplication.JobApp.Service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String header=request.getHeader("Authorization");
            String token=null;
            String username=null;

            if(header==null || !header.startsWith("Bearer "))
            {
                filterChain.doFilter(request,response);
                return;
            }
            token=header.substring(7);
            username=this.jwtService.extractUserName(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                var userDetails=this.appUserRepository.findByUsername(username).orElseThrow();
                Collection<SimpleGrantedAuthority> roles=userDetails.getRoles()
                        .stream().map(SimpleGrantedAuthority::new).toList();

                UsernamePasswordAuthenticationToken utoken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,roles
                );
                utoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(utoken);
            }
            filterChain.doFilter(request,response);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
