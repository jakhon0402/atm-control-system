package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.ATM;

import java.util.List;
import java.util.UUID;

public interface ATMRepo extends JpaRepository<ATM, UUID> {
    List<ATM> findAllByBank_Id(UUID bankId);
    boolean existsByBank_IdAndId(UUID bankId,UUID atmId);
}
