package JobApplication.JobApp.Controller;

import JobApplication.JobApp.Model.Job;
import JobApplication.JobApp.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobAppController {

    @Autowired
    private JobService jobService;

    @GetMapping(path = {"/"},produces = "application/json")
    public ResponseEntity<List<Job>> getAllJobs()
    {
        List<Job> jobs=this.jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",consumes = "application/json")
    public ResponseEntity<Job> getJobById(@PathVariable Long id)
    {
        return this.jobService.getJobById(id);
    }

    @PostMapping(path = {"/addjob"},consumes = "application/json")
    public ResponseEntity<Job> addJob(@RequestBody Job job)
    {
        return this.jobService.addJob(job);
    }

    @PutMapping(path = "/{id}",consumes = "application/json")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,@RequestBody Job job)
    {
        return this.jobService.updateJob(id,job);
    }

}
