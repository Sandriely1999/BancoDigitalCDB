package org.cdb.bancodigitalcdb.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("chave-secreta-simples-mas-longa-o-suficiente-123456".getBytes());

    @GetMapping
    public String generateToken() {
        return Jwts.builder()
                .setSubject("admin")
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }
}