package JobApplication.JobApp.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class Job {
    private static int jobIdCounter=1;
    @Setter
    private int jobId;
    private String jobTitle;
    private String jobDesc;
    private int noOfPositions;
    private List<String> requiredSkills;

    public Job() {
    }

    public Job(String jobTitle, String jobDesc,int noOfPositions, List<String> requiredSkills) {
        this.jobId=jobIdCounter++;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.noOfPositions=noOfPositions;
        this.requiredSkills = requiredSkills;
    }

    public static int getIdCounter() {
        log.info("Test");
        return jobIdCounter++;
    }
}
