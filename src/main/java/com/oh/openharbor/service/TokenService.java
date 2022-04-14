package com.oh.openharbor.service;

import com.oh.openharbor.dao.TokenRepository;
import com.oh.openharbor.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(Token token){
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
