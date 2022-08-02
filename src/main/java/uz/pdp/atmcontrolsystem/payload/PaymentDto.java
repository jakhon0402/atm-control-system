package uz.pdp.atmcontrolsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.atmcontrolsystem.entity.enums.BillName;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private double amount;
    private List<SetOfBill> setOfBills;
    private UUID ATMid;
}

