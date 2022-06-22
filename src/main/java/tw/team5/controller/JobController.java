package tw.team5.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.team5.bean.Job;
import tw.team5.service.JobService;
@RequestMapping("/job")
@Controller	
public class JobController {
	@Autowired
	private JobService jobService;
	
	@GetMapping("/list")
	private String allJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "JobDashBoard";
	}
	
	@GetMapping("/showForm")
	private String showFormForAdd(Model m){
		Job job = new Job();
		m.addAttribute("job", job);
		return "JobCreate";
	}
	
	@PostMapping("/saveJob")
	private String saveJob(@ModelAttribute("job") Job job){
		jobService.save(job);
		return "redirect:/job/list";
	}
	@GetMapping("/showupdateinformation")
	private String showupdateinformation(@RequestParam("jobId") Integer updateId,Model m){
		Job jobupdate = jobService.getJob(updateId);
		m.addAttribute("jobupdate", jobupdate);
		return "JobUpdate";
	}
	@PostMapping("/update")
	public String ProcessUpdate(Job job) {
		jobService.update(job);
		return "redirect:/job/list";
	}
	@GetMapping("/delete")
	private String processDelete(@RequestParam("jobId") Integer deleteId){
		jobService.delete(deleteId);
		return "redirect:/job/list";
	}

}
