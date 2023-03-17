package com.mpfleet.hr.controllers;

import com.mpfleet.hr.models.JobTitle;
import com.mpfleet.hr.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobTitleController {

    private final JobTitleService jobTitleService;

    @Autowired
    public JobTitleController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping("/jobtitles")
    public String parameters(Model model){
        List<JobTitle> jobTitles = jobTitleService.findAll();
        model.addAttribute("jobTitles", jobTitles);
        return "hr/jobtitles/jobTitles";
    }


    @GetMapping("/jobtitles/{id}")
    @ResponseBody
    public JobTitle getById(@PathVariable Long id){
        return jobTitleService.findById(id).orElse(null);
    }

    @PostMapping("/jobtitles")
    public String save(JobTitle jobTitle){
        jobTitleService.save(jobTitle);
        return "redirect:/jobtitles";
    }

    @RequestMapping(value="/jobtitles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        jobTitleService.delete(id);
        return "redirect:/jobtitles";
    }
}
