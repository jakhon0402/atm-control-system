package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.Bank;

import java.util.UUID;

public interface BankRepo extends JpaRepository<Bank, UUID> {
}
