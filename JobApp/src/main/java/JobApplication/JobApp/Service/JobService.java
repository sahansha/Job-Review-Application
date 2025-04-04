package JobApplication.JobApp.Service;

import JobApplication.JobApp.DAO.JobRepository;
import JobApplication.JobApp.Exception.NotFoundException;
import JobApplication.JobApp.Model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    public ResponseEntity<Job> addJob(Job job)
    {
        try{
            Job addedJob=this.jobRepo.save(job);
            URI location= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                    .path("/{id}").buildAndExpand(addedJob.getJobId())
                    .toUri();
            return ResponseEntity.created(location).body(addedJob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Job> getAllJobs()
    {
        return jobRepo.findAll();
    }

    public ResponseEntity<Job> getJobById(Long id) {

               Job job= this.jobRepo.findById(id).orElseThrow(()-> new NotFoundException(String.format("Job with id %d is not found",id)));
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    public ResponseEntity<Job> updateJob(Long id, Job job) {
        try{
            Job oldjob=this.jobRepo.findById(id).orElseThrow(()-> new NotFoundException(String.format("Job with id %d is not found",id)));
           oldjob.setJobDesc(job.getJobDesc());
           oldjob.setJobTitle(job.getJobTitle());
           oldjob.setCompany(job.getCompany());
            Job updatedJob=this.jobRepo.save(oldjob);
            return new ResponseEntity<>(updatedJob,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
