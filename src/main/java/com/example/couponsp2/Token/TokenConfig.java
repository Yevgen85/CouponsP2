package com.example.couponsp2.Token;

import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.LoggedClientType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenConfig {

    private final String SECRET_KEY = "1234";
    private final LoggedClientType loggedClientType;
//    private int id;

    public String generateToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000 * 30))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        System.out.println(token);
        return token;
    }

    public boolean isExpirationToken(String token) {
        return new Date().before(this.getExpirationFromToken(token));
    }

    public Date getExpirationFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }

    public String getUserNameFromToken(String token) {
        String userName = null;
        try {
            userName = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("username").toString();
            System.out.println(userName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userName;
    }

    public String getClientTypeFromToken(String token) {
        String clientType = null;
        try {
            clientType = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("clientType").toString();
            System.out.println(clientType);
            loggedClientType.setClientType(ClientType.valueOf(clientType));
            System.out.println("client type set to: " + ClientType.valueOf(clientType));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(clientType);
        return clientType;
    }


    public int getIdFromToken(String token) {
        int id = 0;
        try {
            id = (int) Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id");
            System.out.println("id => " + id);
            loggedClientType.setId(id);
            System.out.println("logged id set to: " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(id);
        return id;
    }
}
