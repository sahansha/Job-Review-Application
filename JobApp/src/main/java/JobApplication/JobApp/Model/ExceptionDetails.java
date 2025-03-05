package JobApplication.JobApp.Model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDetails{

    private Instant timeStamp;
    private String message;
    private String description;
}
