package uz.pdp.atmcontrolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.atmcontrolsystem.entity.Bank;
import uz.pdp.atmcontrolsystem.entity.Role;
import uz.pdp.atmcontrolsystem.entity.User;
import uz.pdp.atmcontrolsystem.entity.enums.RoleName;
import uz.pdp.atmcontrolsystem.payload.ApiResponse;
import uz.pdp.atmcontrolsystem.payload.EmployeeDto;
import uz.pdp.atmcontrolsystem.payload.LoginDto;
import uz.pdp.atmcontrolsystem.repository.BankRepo;
import uz.pdp.atmcontrolsystem.repository.RoleRepo;
import uz.pdp.atmcontrolsystem.repository.UserRepo;
import uz.pdp.atmcontrolsystem.security.JwtProvider;

import java.util.Optional;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BankRepo bankRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse addDirector(EmployeeDto employeeDto){
        Optional<Bank> optionalBank = bankRepo.findById(employeeDto.getBankId());
        if (!optionalBank.isPresent())
            return new ApiResponse("Bunday idlik bank mavjud emas!",false);
        User user = new User();
        user.setBank(optionalBank.get());
        user.setFirstName(employeeDto.getFirstName());
        user.setLastName(employeeDto.getLastName());
        user.setEmail(employeeDto.getEmail());
        user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        user.setRole(roleRepo.findByRoleName(RoleName.BANK_DIRECTOR));
        userRepo.save(user);
        return new ApiResponse("Direktor qo'shildi!",true);
    }

    public ApiResponse addEmployee(EmployeeDto employeeDto){
        Optional<Bank> optionalBank = bankRepo.findById(employeeDto.getBankId());
        if (!optionalBank.isPresent())
            return new ApiResponse("Bunday idlik bank mavjud emas!",false);
        boolean sendEmail = sendEmail(employeeDto.getEmail());
        if(sendEmail)
            return new ApiResponse("Emailga yuborishda xatolik!",false);
        User user = new User();
        user.setBank(optionalBank.get());
        user.setFirstName(employeeDto.getFirstName());
        user.setLastName(employeeDto.getLastName());
        user.setEmail(employeeDto.getEmail());
        user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        user.setRole(roleRepo.findByRoleName(RoleName.BANK_EMPLOYEE));
        userRepo.save(user);
        return new ApiResponse("Employee qo'shildi! Iltimos akkountni tasdiqlang !",true);
    }

    public boolean sendEmail(String sendingEmail){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("jahon99king@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Akkountni tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?email="+sendingEmail+"'></a>");
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ApiResponse verifyEmail(String emailCode, String email) {
        Optional<User> optionalUser = userRepo.findByEmailAndEmailCode(email, emailCode);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEnabled(true);
            userRepo.save(user);
            return new ApiResponse("Akkount tasdiqlandi!",true);
        }

        return new ApiResponse("Akkount allaqachon tasdiqlangan!",false);

    }

    public ApiResponse login(LoginDto loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getEmail(), user.getRole());
            return new ApiResponse("Token",true,token);
        }
        catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Parol yoki login xato",false);

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username + " topilmadi"));
    }

}
