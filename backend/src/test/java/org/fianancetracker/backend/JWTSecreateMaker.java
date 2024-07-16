package org.fianancetracker.backend;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

@SpringBootTest
public class JWTSecreateMaker {

    @Test
    public void generateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encodeKey = DatatypeConverter.printHexBinary(key.getEncoded());

        System.out.println(encodeKey);

    }
}
