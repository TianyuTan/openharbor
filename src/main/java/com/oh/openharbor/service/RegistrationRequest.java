package com.oh.openharbor.service;

import com.oh.openharbor.enums.CustomerRole;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String AccountType;
    private String username;
    private String emailAddress;
    private String phoneCountryCode;
    private String phoneNumber;
    private String KYCLevel;
    private String password;
    private CustomerRole customerRole;

}
