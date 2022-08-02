package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.CardType;
import uz.pdp.atmcontrolsystem.entity.enums.CardTypeName;

public interface CardTypeRepo extends JpaRepository<CardType,Integer> {
    CardType findByCardTypeName(CardTypeName cardTypeName);
}
