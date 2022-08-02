package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.atmcontrolsystem.entity.AtmHistory;

import java.util.List;
import java.util.UUID;

public interface AtmHistoryRepo extends JpaRepository<AtmHistory, UUID> {
    List<AtmHistory> findAllByAtm_Id(UUID atmId);

    @Query(nativeQuery = true, value =
            "select * from atm_history ah" +
                    " where ah.atm_id=:atmId " +
                    "and where ah.payment_type=:'InCome'")
    List<AtmHistory> getIncomeHistory(@Param("atmId") UUID atmId);

    @Query(nativeQuery = true, value =
            "select * from atm_history ah" +
                    " where ah.atm_id=:atmId " +
                    "and where ah.payment_type=:'OutCome'")
    List<AtmHistory> getOutcomeHistory(@Param("atmId") UUID atmId);

    @Query(nativeQuery = true, value =
            "select * from atm_history ah" +
                    " where ah.atm_id=:atmId " +
                    "and where ah.payment_type=:'Fill'")
    List<AtmHistory> getFillHistory(@Param("atmId") UUID atmId);
}
