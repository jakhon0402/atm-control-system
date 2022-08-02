package uz.pdp.atmcontrolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.atmcontrolsystem.payload.ApiResponse;
import uz.pdp.atmcontrolsystem.payload.PaymentDto;
import uz.pdp.atmcontrolsystem.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/getCash")
    public HttpEntity<?> getCash(@RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = paymentService.cashWithdrawal(paymentDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping("/fillBalance")
    public HttpEntity<?> fillBalnce(@RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = paymentService.fillBalanceCard(paymentDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }
}
