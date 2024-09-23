package JobApplication.JobApp.Service;

import JobApplication.JobApp.DAO.JobRepository;
import JobApplication.JobApp.Model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    public Job addJob(Job job)
    {
        return jobRepo.addJob(job);
    }

    public List<Job> getAllJobs()
    {
        return jobRepo.getAllJobs();
    }
}
