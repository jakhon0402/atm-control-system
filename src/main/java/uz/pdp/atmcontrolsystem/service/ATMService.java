package uz.pdp.atmcontrolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.atmcontrolsystem.entity.*;
import uz.pdp.atmcontrolsystem.payload.ApiResponse;
import uz.pdp.atmcontrolsystem.payload.AtmDto;
import uz.pdp.atmcontrolsystem.payload.PaymentDto;
import uz.pdp.atmcontrolsystem.payload.SetOfBill;
import uz.pdp.atmcontrolsystem.repository.*;

import java.util.*;

@Service
public class ATMService {
    @Autowired
    ATMRepo atmRepo;

    @Autowired
    MoneyBillRepo moneyBillRepo;

    @Autowired
    AtmBillsRepo atmBillsRepo;

    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    AtmHistoryRepo atmHistoryRepo;

    @Autowired
    CardTypeRepo cardTypeRepo;

    @Autowired
    BankRepo bankRepo;

    public ApiResponse addAtm(AtmDto atmDto){
        Optional<Bank> optionalBank = bankRepo.findById(atmDto.getBankId());
        if(!optionalBank.isPresent())
            return new ApiResponse("Bunday idlik bank mavjud emas!",false);

        Set<CardType> cardTypeSet = new HashSet<CardType>();
        for (Integer cardTypeId:atmDto.getCardTypeSetIds()) {
            Optional<CardType> optionalCardType = cardTypeRepo.findById(cardTypeId);
            if(!optionalCardType.isPresent())
                return new ApiResponse("Bunday idlik cardType mavjud emas!",false);
            cardTypeSet.add(optionalCardType.get());
        }
        ATM atm = new ATM();
        atm.setCardTypeSet(cardTypeSet);
        atm.setBank(optionalBank.get());
        atm.setCommissionIsBelongIncome(atmDto.getCommissionIsBelongIncome());
        atm.setCommissionIsBelongOutcome(atmDto.getCommissionIsBelongOutcome());
        atm.setCommissionIsNotBelongIncome(atmDto.getCommissionIsNotBelongIncome());
        atm.setCommissionIsNotBelongOutcome(atmDto.getCommissionIsNotBelongOutcome());
        atm.setMaxOutMoney(atmDto.getMaxOutMoney());
        atm.setMinBalance(atmDto.getMinBalance());
        atmRepo.save(atm);
        return new ApiResponse("Atm qo'shildi!",true);
    }

    public ApiResponse fillAtmBills(PaymentDto paymentDto){
        Optional<ATM> optionalATM = atmRepo.findById(paymentDto.getATMid());
        if(!optionalATM.isPresent()){
            return new ApiResponse("Bunday idlik ATM mavjud emas",false);
        }
        List<SetOfBill> setOfBillsCash = paymentDto.getSetOfBills();
        Collections.sort(setOfBillsCash,Collections.reverseOrder());
        for (SetOfBill billCash:setOfBillsCash) {
            Optional<MoneyBill> optionalMoneyBill = moneyBillRepo.findById(billCash.getMoneyBillId());
            if(!optionalATM.isPresent())
                return new ApiResponse("Kiritilgan kupyura tizimda mavjud emas!",false);
        }
        List<AtmBills> atmBills = atmBillsRepo.findAllByAtm_IdAndCurrency_Id(paymentDto.getATMid(), currencyRepo.findByName("UZS").get().getId());
        Collections.sort(atmBills,Collections.reverseOrder());
        int amount = 0;
        for (AtmBills atmBill:atmBills) {
            int i = 0;

            if(atmBill.getBillAmount().equals(setOfBillsCash.get(i))){
                atmBill.setQuantity(atmBill.getQuantity()+setOfBillsCash.get(i).getQuantity());
                atmBillsRepo.save(atmBill);
                i+=1;
                amount = amount + setOfBillsCash.get(i).getQuantity()*atmBill.getBillAmount();
            }
        }

        AtmHistory atmHistory = new AtmHistory();
        atmHistory.setAtm(optionalATM.get());
        atmHistory.setCurrency(currencyRepo.findByName("UZS").get());
        atmHistory.setPaymentAmount(amount);
        atmHistory.setPaymentType("Fill");
        atmHistoryRepo.save(atmHistory);

        return new ApiResponse("ATM ga kupyuralar to'dirildi!",true);
    }


}
