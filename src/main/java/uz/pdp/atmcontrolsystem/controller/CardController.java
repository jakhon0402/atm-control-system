package uz.pdp.atmcontrolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.atmcontrolsystem.entity.Card;
import uz.pdp.atmcontrolsystem.payload.ApiResponse;
import uz.pdp.atmcontrolsystem.payload.CardDto;
import uz.pdp.atmcontrolsystem.repository.CardRepo;
import uz.pdp.atmcontrolsystem.service.CardService;

@RestController
@RequestMapping("api/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PreAuthorize("hasAnyRole('BANK_DIRECTOR', 'BANK_EMPLOYEE')")
    @PostMapping("/addUzCard")
    public HttpEntity<?> addUzCard(@RequestBody CardDto cardDto) {
        ApiResponse apiResponse = cardService.addUzCard(cardDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('BANK_DIRECTOR', 'BANK_EMPLOYEE')")
    @PostMapping("/addHumoCard")
    public HttpEntity<?> addHumoCard(@RequestBody CardDto cardDto) {
        ApiResponse apiResponse = cardService.addHumo(cardDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('BANK_DIRECTOR', 'BANK_EMPLOYEE')")
    @PostMapping("/AddVisaCard")
    public HttpEntity<?> addVisaCard(@RequestBody CardDto cardDto) {
        ApiResponse apiResponse = cardService.addVisa(cardDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }
}
