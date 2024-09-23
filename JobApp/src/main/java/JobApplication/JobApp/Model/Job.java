package JobApplication.JobApp.Model;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Getter
public class Job {
    @Getter(AccessLevel.NONE)
    private static int jobIdCounter=1;
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
}
