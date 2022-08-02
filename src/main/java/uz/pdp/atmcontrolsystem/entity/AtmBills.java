package uz.pdp.atmcontrolsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.atmcontrolsystem.entity.enums.BillName;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AtmBills {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private Integer billAmount;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @ManyToOne
    private Currency currency;

    @ManyToOne
    private ATM atm;
}
