package com.mpfleet;

import com.mpfleet.accounts.repositories.TransactionRepository;
import com.mpfleet.admin.repositories.LocationRepository;
import com.mpfleet.fleet.repositories.VehicleRepository;
import com.mpfleet.hr.repositories.EmployeeRepository;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public ApplicationController(EmployeeRepository employeeRepository, VehicleRepository vehicleRepository, LocationRepository locationRepository, TransactionRepository transactionRepository) {
        this.employeeRepository = employeeRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/index") // INDEX PAGE
    @PageTitle("Index")
    public String home(Model model){
        model.addAttribute("employees", (long) employeeRepository.findAll().size());
        model.addAttribute("vehicles", (long) vehicleRepository.findAll().size());
        model.addAttribute("locations", (long) locationRepository.findAll().size());
        model.addAttribute("transactions", (long) transactionRepository.findAll().size());
        return "index";
    }

    @GetMapping("/hr") // HUMAN RESOURCES (HR)
    @PageTitle("HR")
    public String hr(){
        return "/hr/index";
    }

    @GetMapping("/accounts") // ACCOUNTS
    @PageTitle("Accounts")
    public String accounts(){
        return "/accounts/index";
    }

    @GetMapping("/admin") // ADMIN
    @PageTitle("Admin")
    public String test(){
        return "/admin/index";
    }

    @GetMapping("/fleet") // FLEET
    @PageTitle("Fleet")
    public String fleet(){
        return "/fleet/index";
    }

    @GetMapping("/tickets") // TICKETS
    @PageTitle("Tickets")
    public String tickets(){
        return "/tickets/index";
    }

    @GetMapping("/security") // TICKETS
    @PageTitle("Security")
    public String security(){
        return "/security/index";
    }

   // @GetMapping("/profile")
   // @PageTitle("Profile")
  //  public String profile(){
        // return "/profile";
  //  }
}
