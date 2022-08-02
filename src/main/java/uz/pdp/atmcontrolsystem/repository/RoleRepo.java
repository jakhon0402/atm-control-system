package uz.pdp.atmcontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.atmcontrolsystem.entity.Role;
import uz.pdp.atmcontrolsystem.entity.enums.RoleName;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
