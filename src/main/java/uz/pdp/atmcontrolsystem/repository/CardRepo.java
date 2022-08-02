package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.Card;

import java.util.Optional;
import java.util.UUID;

public interface CardRepo extends JpaRepository<Card, UUID> {
    Optional<Card> findByCardNumber(String cardNumber);
}
