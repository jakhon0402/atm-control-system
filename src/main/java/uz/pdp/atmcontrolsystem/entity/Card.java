package uz.pdp.atmcontrolsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,length = 16)
    private String cardNumber;

    @ManyToOne
    private Bank bank;

    @Column(nullable = false,length = 3)
    private String cvvCode;

    @Column(nullable = false,length = 50)
    private String fullName;

    @Column(nullable = false)
    private Date expireDate;

    @Column(nullable = false,length = 4)
    private String pinCode;

    @Column(nullable = false)
    private double balance;


    @ManyToOne(fetch = FetchType.EAGER)
    private CardType cardType;


    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    @CreatedBy
    private UUID createdBy;

    @LastModifiedBy
    private UUID updatedBy;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.pinCode;
    }

    @Override
    public String getUsername() {
        return this.cardNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
