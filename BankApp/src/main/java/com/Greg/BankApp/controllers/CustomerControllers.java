package com.Greg.BankApp.controllers;
import com.Greg.BankApp.Services.CustomerService;
import com.Greg.BankApp.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"cusIntersession", "accountNumber","login","password"})
public class CustomerControllers {

    @Autowired
    CustomerService customerService;

    @GetMapping("/changePersonalDetailsForm")
    public String changePersonalDetailsForm(){
      return "changePersonalDetailsForm";
    }
    @GetMapping("/changePersonalDetails")
    public  String changePersonalDetails(@RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("city") String city,
                                         @RequestParam("street") String street,
                                         @RequestParam("zipcode") String zipcode,
                                         @RequestParam("email") String email,
                                         @RequestParam("phone") String phone,
                                         Model model){

        Customer customer = (Customer)model.getAttribute("cusIntersession");

        if(firstName != ""){
            customer.setFirstname(firstName);
            customerService.saveCustomer(customer);
        }
        if(lastName !="") {
            customer.setLastname(lastName);
            customerService.saveCustomer(customer);
        }
        if(city !="") {
            customer.setCity(city);
            customerService.saveCustomer(customer);
        }
        if(street !="") {
            customer.setStreet(street);
            customerService.saveCustomer(customer);
        }
        if(zipcode !="") {
            customer.setZipcode(zipcode);
            customerService.saveCustomer(customer);
        }
        if(email !="") {
            customer.setEmail(email);
            customerService.saveCustomer(customer);
        }
        if(phone !="") {
            customer.setTelephone(phone);
            customerService.saveCustomer(customer);
        }
        return "changePersonalDetailsApproval";
    }

    @RequestMapping("/newCustomerAccountForm")
    public String newCustomerAccountForm(Model model){
        model.addAttribute("newCustomer", new Customer());
        return "newCustomerAccountForm";
    }

    @PostMapping("/newCustomerAccountPost")
    public String saveAccount(Customer customer){
        customerService.createCustomer(customer);
        return "redirect:/mainMenu";
    }
    
}
