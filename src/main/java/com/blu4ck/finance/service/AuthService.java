package com.blu4ck.finance.service;

import com.blu4ck.finance.model.JwtUtil;
import com.blu4ck.finance.model.Role;
import com.blu4ck.finance.model.User;
import com.blu4ck.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // Burada JwtUtil enjekte edildi!

    public String register(User user, boolean isAdmin) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Şifre encode ediliyor mu?
        user.setRole(isAdmin ? Role.ADMIN : Role.USER);
        userRepository.save(user);
        return "Kullanıcı başarıyla kaydedildi!";
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(user.get().getEmail(), user.get().getRole()); // JWT token oluşturuluyor
        }
        throw new RuntimeException("Geçersiz kimlik bilgileri!");
    }
}
