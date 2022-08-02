package uz.pdp.atmcontrolsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.atmcontrolsystem.entity.Role;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UUID bankId;
}
