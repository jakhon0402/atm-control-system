package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.Currency;

import java.util.Optional;

public interface CurrencyRepo extends JpaRepository<Currency,Integer> {
    Optional<Currency> findByName(String name);
}
