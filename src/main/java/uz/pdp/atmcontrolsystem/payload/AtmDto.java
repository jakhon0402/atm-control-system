package uz.pdp.atmcontrolsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.atmcontrolsystem.entity.Address;
import uz.pdp.atmcontrolsystem.entity.AtmBills;
import uz.pdp.atmcontrolsystem.entity.Bank;
import uz.pdp.atmcontrolsystem.entity.CardType;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmDto {

    private UUID bankId;

    private Set<Integer> cardTypeSetIds;

    private double commissionIsBelongIncome;

    private double commissionIsBelongOutcome;

    private double commissionIsNotBelongIncome;

    private double commissionIsNotBelongOutcome;

    private double maxOutMoney;

    private double minBalance;

    private UUID addressId;
}
