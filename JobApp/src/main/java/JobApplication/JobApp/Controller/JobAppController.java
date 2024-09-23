package JobApplication.JobApp.Controller;

import JobApplication.JobApp.Model.Job;
import JobApplication.JobApp.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobAppController {

    @Autowired
    private JobService jobService;

    @GetMapping(path = {"/jobs"},produces = "application/json")
    public ResponseEntity<List<Job>> getAllJobs()
    {
        List<Job> jobs=this.jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping(path = {"/jobs"},consumes = "application/json")
    public ResponseEntity<Job> addJob(@RequestBody Job job)
    {
        Job addedJob=this.jobService.addJob(job);
        return ResponseEntity.ok().body(addedJob);
    }

}
