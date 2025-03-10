package JobApplication.JobApp.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.app")
@Getter
@Setter
public class JwtProperties {
    private String secret;
}
