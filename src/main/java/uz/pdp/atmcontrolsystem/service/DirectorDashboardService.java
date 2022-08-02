package uz.pdp.atmcontrolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.atmcontrolsystem.entity.ATM;
import uz.pdp.atmcontrolsystem.entity.AtmHistory;
import uz.pdp.atmcontrolsystem.entity.User;
import uz.pdp.atmcontrolsystem.repository.ATMRepo;
import uz.pdp.atmcontrolsystem.repository.AtmHistoryRepo;
import uz.pdp.atmcontrolsystem.repository.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DirectorDashboardService {
    @Autowired
    AtmHistoryRepo atmHistoryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ATMRepo atmRepo;

    public List<AtmHistory> getAllHistory(UUID atmId) {
        Optional<ATM> optionalATM = atmRepo.findById(atmId);
        if (!optionalATM.isPresent() && checkAtmId(atmId))
            return null;
        return atmHistoryRepo.findAllByAtm_Id(atmId);
    }

    public List<AtmHistory> getIncomeHistory(UUID atmId) {
        Optional<ATM> optionalATM = atmRepo.findById(atmId);
        if (!optionalATM.isPresent() && checkAtmId(atmId))
            return null;
        return atmHistoryRepo.getIncomeHistory(atmId);
    }

    public List<AtmHistory> getOutcomeHistory(UUID atmId) {
        Optional<ATM> optionalATM = atmRepo.findById(atmId);
        if (!optionalATM.isPresent() && checkAtmId(atmId))
            return null;
        return atmHistoryRepo.getOutcomeHistory(atmId);
    }

    public List<AtmHistory> getFillHistory(UUID atmId) {
        Optional<ATM> optionalATM = atmRepo.findById(atmId);
        if (!optionalATM.isPresent() && checkAtmId(atmId))
            return null;
        return atmHistoryRepo.getFillHistory(atmId);
    }

    public List<ATM> getAtms() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return atmRepo.findAllByBank_Id(user.getBank().getId());
    }

    public boolean checkAtmId(UUID atmId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return atmRepo.existsByBank_IdAndId(user.getBank().getId(), atmId);
    }

}
