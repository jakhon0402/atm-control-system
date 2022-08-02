package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.MoneyBill;

import java.util.UUID;

public interface MoneyBillRepo extends JpaRepository<MoneyBill, UUID> {
}
