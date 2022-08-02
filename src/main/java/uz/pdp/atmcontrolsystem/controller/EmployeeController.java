package uz.pdp.atmcontrolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.atmcontrolsystem.payload.*;
import uz.pdp.atmcontrolsystem.service.ATMService;
import uz.pdp.atmcontrolsystem.service.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ATMService atmService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDirector")
    public HttpEntity<?> addDirector(@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse apiResponse = employeeService.addDirector(employeeDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @PostMapping("/addManager")
    public HttpEntity<?> addManager(@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse apiResponse = employeeService.addEmployee(employeeDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @PostMapping("/addATM")
    public HttpEntity<?> addAtm(@Valid @RequestBody AtmDto atmDto){
        ApiResponse apiResponse = atmService.addAtm(atmDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasRole('BANK_EMPLOYEE')")
    @PostMapping("/fillAtmBalance")
    public HttpEntity<?> addAtm(@Valid @RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = atmService.fillAtmBills(paymentDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode,@RequestParam String email){
        ApiResponse apiResponse = employeeService.verifyEmail(emailCode,email);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse = employeeService.login(loginDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }


}
