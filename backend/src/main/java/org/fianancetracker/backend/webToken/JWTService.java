package org.fianancetracker.backend.webToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.fianancetracker.backend.dto.UserDTO;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JWTService {
    private static final String SECRET = "611EE1BC904EB2E7C63E3AA1CC26563E920607FC4A3C0974354210765B126B4C154D431DA9C88F3BF361FA9081BF17BDAA08459790057B062ECB4A996BCEE5F1";
    private static final long VALIDATY = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDTO userDTO) {
        Map<String, String> claims = new HashMap<>();

        return Jwts.builder()
                .subject(userDTO.getFirstName())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDATY)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodeKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodeKey);
    }
}
