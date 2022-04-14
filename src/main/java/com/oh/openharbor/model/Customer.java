package com.oh.openharbor.model;


import com.oh.openharbor.enums.CustomerRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "ClientAccount")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long accountId;
    private String accountType;
    private String username;
    private String emailAddress;
    private String phoneCountryCode;
    private String phoneNumber;
    private String KYCLevel;
    private String password;
    @Enumerated(EnumType.STRING)
    private CustomerRole customerRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public Customer(String accountType, String username, String emailAddress) {
        this.accountType = accountType;
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public Customer(Long accountId, String accountType, String username, String emailAddress, String phoneCountryCode, String phoneNumber, String KYCLevel, String password, CustomerRole customerRole, Boolean locked, Boolean enabled) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNumber = phoneNumber;
        this.KYCLevel = KYCLevel;
        this.password = password;
        this.customerRole = customerRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    public Customer(String accountType, String username, String emailAddress, String phoneCountryCode, String phoneNumber, String KYCLevel, String password, CustomerRole customerRole) {
        this.accountType = accountType;
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneNumber = phoneNumber;
        this.KYCLevel = KYCLevel;
        this.password = password;
        this.customerRole = customerRole;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(customerRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
