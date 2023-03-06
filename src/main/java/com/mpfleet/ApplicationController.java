package com.mpfleet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/hr") // HUMAN RESOURCES (HR)
    public String hr(){
        return "/hr/index";
    }

    @GetMapping("/accounts") // ACCOUNTS
    public String accounts(){
        return "/accounts/index";
    }

    @GetMapping("/admin") // ADMIN
    public String test(){
        return "/admin/index";
    }

    @GetMapping("/fleet") // FLEET
    public String fleet(){
        return "/fleet/index";
    }

    @GetMapping("/payroll") // PAYROLL
    public String payroll(){
        return "/payroll/index";
    }

    @GetMapping("/tickets") // TICKETS
    public String tickets(){
        return "/tickets/index";
    }
}
