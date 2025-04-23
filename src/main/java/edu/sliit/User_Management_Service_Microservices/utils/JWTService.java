//package edu.sliit.User_Management_Service_Microservices.utils;
//
//import edu.sliit.User_Management_Service_Microservices.document.User;
//import edu.sliit.User_Management_Service_Microservices.dto.TokenValidationResponse;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JWTService {
//
//    @Value("${token.signing.key}")
//    private String jwtSigningKey;
//
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public String extractRole(String token) {
//        return extractAllClaims(token).get("role", String.class);
//    }
//
//    public Boolean extractIsVerified(String token) {
//        return extractAllClaims(token).get("isVerified", Boolean.class);
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        User user = (User) userDetails;
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("authorities", userDetails.getAuthorities());
//        claims.put("role", user.getRole());
//        claims.put("typ", "access-token");
//
//        if ("DRIVER".equalsIgnoreCase(user.getRole())) {
//            claims.put("isVerified", user.isVerified());
//        }
//
//        return generateToken(claims, userDetails);
//    }
//
//    public String generateRefreshToken(UserDetails userDetails) {
//        User user = (User) userDetails;
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("authorities", userDetails.getAuthorities());
//        claims.put("role", user.getRole());
//        claims.put("typ", "refresh-token");
//
//        if ("DRIVER".equalsIgnoreCase(user.getRole())) {
//            claims.put("isVerified", user.isVerified());
//        }
//
//        return generateRefreshToken(claims, userDetails);
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//
//    public Mono<TokenValidationResponse> validateToken(String token) {
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(getSigningKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            return Mono.just(TokenValidationResponse.builder()
//                    .valid(true)
//                    .username(claims.getSubject())
//                    .role(claims.get("role", String.class))
//                    .isVerified(claims.get("isVerified", Boolean.class))
//                    .build());
//        } catch (Exception e) {
//            return Mono.just(TokenValidationResponse.builder().valid(false).build());
//        }
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolvers.apply(claims);
//    }
//
//    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // 30 days
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    private String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}




package edu.sliit.User_Management_Service_Microservices.utils;

import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.TokenValidationResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public Boolean extractIsVerified(String token) {
        return extractAllClaims(token).get("isVerified", Boolean.class);
    }

    public String generateToken(UserDetails userDetails) {
        return generateTokenWithType(userDetails, "access-token");
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateTokenWithType(userDetails, "refresh-token");
    }

    private String generateTokenWithType(UserDetails userDetails, String type) {
        User user = (User) userDetails;
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        claims.put("role", user.getRole());
        claims.put("typ", type);

        if ("DRIVER".equalsIgnoreCase(user.getRole())) {
            claims.put("isVerified", user.isVerified());
        }

        return buildToken(claims, userDetails);
    }

    private String buildToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // 30 days
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Mono<TokenValidationResponse> validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Mono.just(TokenValidationResponse.builder()
                    .valid(true)
                    .username(claims.getSubject())
                    .role(claims.get("role", String.class))
                    .isVerified(claims.get("isVerified", Boolean.class))
                    .build());
        } catch (Exception e) {
            return Mono.just(TokenValidationResponse.builder().valid(false).build());
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
