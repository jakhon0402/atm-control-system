package uz.pdp.atmcontrolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.atmcontrolsystem.entity.Bank;
import uz.pdp.atmcontrolsystem.entity.Card;
import uz.pdp.atmcontrolsystem.entity.CardType;
import uz.pdp.atmcontrolsystem.entity.enums.CardTypeName;
import uz.pdp.atmcontrolsystem.payload.ApiResponse;
import uz.pdp.atmcontrolsystem.payload.CardDto;
import uz.pdp.atmcontrolsystem.repository.BankRepo;
import uz.pdp.atmcontrolsystem.repository.CardRepo;
import uz.pdp.atmcontrolsystem.repository.CardTypeRepo;

import java.sql.Date;
import java.util.Optional;

@Service
public class CardService implements UserDetailsService {

    @Autowired
    CardRepo cardRepo;

    @Autowired
    BankRepo bankRepo;

    @Autowired
    CardTypeRepo cardTypeRepo;

    private static final long EXPIRE_DATE = 126_227_808_000L; // 4 years
    private static final double INITIAL_BALANCE = 100.0;

    private ApiResponse addCard(CardDto cardDto, CardTypeName cardTypeName){
        Optional<Card> optionalCard = cardRepo.findByCardNumber(cardDto.getCardNumber());
        if(!optionalCard.isPresent())
            return new ApiResponse("Bunday CardNumber tizimda mavjud",false);
        Optional<Bank> optionalBank = bankRepo.findById(cardDto.getBankId());
        if(!optionalBank.isPresent())
            return new ApiResponse("Bunday bank tizimda mavjud emas",false);

        Card card = new Card();
        card.setCardNumber(card.getCardNumber());
        card.setBank(optionalBank.get());
        card.setCvvCode(card.getCvvCode());
        card.setFullName(card.getFullName());
        card.setBalance(INITIAL_BALANCE);
        card.setExpireDate(new Date(System.currentTimeMillis()+EXPIRE_DATE));
        card.setPinCode(card.getPinCode());
        card.setCardType(cardTypeRepo.findByCardTypeName(cardTypeName));
        cardRepo.save(card);
        return new ApiResponse(cardTypeName.name()+" qushildi",true);

    }

    public ApiResponse addUzCard(CardDto cardDto){
        return addCard(cardDto,CardTypeName.UZCARD);
    }
    public ApiResponse addHumo(CardDto cardDto){
        return addCard(cardDto,CardTypeName.HUMO);
    }
    public ApiResponse addVisa(CardDto cardDto){
        return addCard(cardDto,CardTypeName.VISA);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cardRepo.findByCardNumber(username).orElseThrow(()-> new UsernameNotFoundException(username + " topilmadi"));
    }
}
