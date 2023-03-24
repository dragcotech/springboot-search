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

    @GetMapping("/hr/jobtitles")
    public String parameters(Model model){
        List<JobTitle> jobTitles = jobTitleService.findAll();
        model.addAttribute("jobTitles", jobTitles);
        return "hr/jobtitles/jobTitles";
    }


    @GetMapping("/hr/jobtitles/{id}")
    @ResponseBody
    public JobTitle getById(@PathVariable Long id){
        return jobTitleService.findById(id).orElse(null);
    }

    @PostMapping("/hr/jobtitles")
    public String save(JobTitle jobTitle){
        jobTitleService.save(jobTitle);
        return "redirect:/hr/jobtitles";
    }

    @RequestMapping(value="/hr/jobtitles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        jobTitleService.delete(id);
        return "redirect:/hr/jobtitles";
    }
}
