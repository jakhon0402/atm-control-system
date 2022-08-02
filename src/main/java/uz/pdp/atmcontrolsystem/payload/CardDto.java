package uz.pdp.atmcontrolsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.atmcontrolsystem.entity.Bank;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private String cardNumber;

    private UUID bankId;

    private String cvvCode;

    private String fullName;

    private String pinCode;
}
