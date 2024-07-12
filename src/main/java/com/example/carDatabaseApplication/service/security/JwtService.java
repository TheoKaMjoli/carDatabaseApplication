package com.example.carDatabaseApplication.service.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    static final long EXPIRATIONTIME =  86400000; //defines expiration time of token
    static final String PREFIX = "Bearer"; //prefix of the token, the "bearer" is the schema used in the jwt

    //Generate secret key.Only for demo purposes.
    //in production, it should be read from application config
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Generate signed JWT token
    public String getToken(String username){

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();

        return token;
    }

    //get token from request authorization header, verify the token and get username.
    public String getAuthUser(HttpServletRequest request){

        String token = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);

        if(token != null){

            //parserBuilder method provided by the jjwt library to create a JwtParserBuilder instance
            String user = Jwts.parser()
                    //The setSigningKey method is used to specify a secret key for token verification
                    .setSigningKey(key)
                    .build()
                    //The parseClaimsJws method removes the Bearer prefix from the Authorization header
                    .parseClaimsJws(token.replace(PREFIX, " "))
                    .getBody()
                    .getSubject(); // getSubject method to get the username.

            if(user != null){
                return user;
            }
        }
        return  null;
    }
}
