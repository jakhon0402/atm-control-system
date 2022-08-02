package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.ATM;
import uz.pdp.atmcontrolsystem.entity.AtmBills;

import java.util.List;
import java.util.UUID;

public interface AtmBillsRepo extends JpaRepository<AtmBills, UUID> {
    List<AtmBills> findAllByAtm_IdAndCurrency_Id(UUID atmId,Integer currencyId);
}
