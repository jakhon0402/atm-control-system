package uz.pdp.atmcontrolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.atmcontrolsystem.entity.ATM;
import uz.pdp.atmcontrolsystem.entity.AtmHistory;
import uz.pdp.atmcontrolsystem.service.DirectorDashboardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DirectorDashboardController {

    @Autowired
    DirectorDashboardService directorDashboardService;

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @GetMapping("/atmHistory")
    public List<ATM> getAtms(){
        return directorDashboardService.getAtms();
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @GetMapping("/atmHistory")
    public List<AtmHistory> getHistory(@RequestParam UUID atmId){
        return directorDashboardService.getAllHistory(atmId);
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @GetMapping("/atmHistoryIn")
    public List<AtmHistory> getIncomeHistory(@RequestParam UUID atmId){
        return directorDashboardService.getIncomeHistory(atmId);
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @GetMapping("/atmHistoryOut")
    public List<AtmHistory> getOutcomeHistory(@RequestParam UUID atmId){
        return directorDashboardService.getOutcomeHistory(atmId);
    }

    @PreAuthorize("hasRole('BANK_DIRECTOR')")
    @GetMapping("/atmHistoryFill")
    public List<AtmHistory> getFillHistory(@RequestParam UUID atmId){
        return directorDashboardService.getFillHistory(atmId);
    }
}
