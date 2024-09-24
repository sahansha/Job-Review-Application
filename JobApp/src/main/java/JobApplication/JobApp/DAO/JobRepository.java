package JobApplication.JobApp.DAO;

import JobApplication.JobApp.Model.Job;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobRepository {
    private List<Job> jobs=new ArrayList<>();

    //Adding default jobs
    public JobRepository() {
        // Java Developer Job Post
        jobs.add(new Job("Java Developer", "Must have good experience in core Java and advanced Java", 2,
                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));

        // Frontend Developer Job Post
        jobs.add(
                new Job("Frontend Developer", "Experience in building responsive web applications using React",
                        3, List.of("HTML", "CSS", "JavaScript", "React")));

        // Data Scientist Job Post
        jobs.add(new Job("Data Scientist", "Strong background in machine learning and data analysis", 4,
                List.of("Python", "Machine Learning", "Data Analysis")));

        // Network Engineer Job Post
        jobs.add(new Job("Network Engineer",
                "Design and implement computer networks for efficient data communication", 5,
                List.of("Networking", "Cisco", "Routing", "Switching")));

        // Mobile App Developer Job Post
        jobs.add(new Job( "Mobile App Developer", "Experience in mobile app development for iOS and Android",
                3, List.of("iOS Development", "Android Development", "Mobile App")));
    }
    public Job addJob(Job job)
    {

        this.jobs.add(job);
        if(this.jobs.contains(job))
        {
            return job;
        }
        else{
            return new Job();
        }
    }

    public List<Job> getAllJobs()
    {
        return jobs;
    }

}
