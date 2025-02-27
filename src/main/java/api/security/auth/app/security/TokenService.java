package api.security.auth.app.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import api.security.auth.app.model.UserModel;

@Service
public class TokenService{

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("auth-api")
                            .withSubject(username.getUsername())
                            .withExpiresAt(generateExpirationDate())
                            .sign(algorithm);

            return token;
        }catch(JWTCreationException e){
            throw new RuntimeException("Erro na criacao do token");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("auth-api")
            .build()
            .verify(token)
            .getSubject();
        }catch(JWTVerificationException e){
            return "";
        }
    } 


    private Date generateExpirationDate() {
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(2);
        return Date.from(expirationDateTime.toInstant(ZoneOffset.of("-03:00")));
    }
    
}
