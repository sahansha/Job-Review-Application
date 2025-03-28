package JobApplication.JobApp.JWT;

import JobApplication.JobApp.Model.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Service
public class JWTService {

//    @Autowired
//    private JwtProperties jwtProperties;

    //@Value("${jwt.secret}")
    //for testing purpose
   private final String key="DGTE12#IKO&GCBEYAPLMQ15*UQBZTE@}";
    private final SecretKey secret=Keys.hmacShaKeyFor(key.getBytes());

    public String generateToken(String username)
    {
        Map<String, Objects> claims=new HashMap<>();
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(864000)))
                .signWith(secret)
                .compact();
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(secret)
                .build().parseSignedClaims(token).getPayload();
    }

    public String extractUserName(String token)
    {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        String username=extractAllClaims(token).getSubject();
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(Date.from(Instant.now()));
    }
    public Date extractExpiration(String token)
    {
        return extractAllClaims(token).getExpiration();
    }

}
