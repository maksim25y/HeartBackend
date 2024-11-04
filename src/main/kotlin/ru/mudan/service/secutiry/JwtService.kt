package ru.mudan.service.secutiry

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey

@Service
@RequiredArgsConstructor
class JwtService(
    val jwtSecretKey: String,
    val tokenTTL: Duration,
    val refreshTokenTTL: Duration){
    fun extractEmail(token: String?): String {
        return extractClaim(token) { obj: Claims -> obj.subject }
    }

    fun isTokenValid(token: String?, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return (email == userDetails.username) && !isTokenExpired(token)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(java.util.Map.of(), userDetails)
    }

    fun <T> extractClaim(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    fun generateToken(
        extraClaims: Map<String?, Any?>?,
        userDetails: UserDetails
    ): String {
        return generateTokenByTTL(extraClaims,userDetails,tokenTTL)
    }

    fun generateRefreshToken(
        extraClaims: Map<String?, Any?>?,
        userDetails: UserDetails
    ): String {
        return generateTokenByTTL(extraClaims,userDetails,refreshTokenTTL)
    }

    private fun generateTokenByTTL(extraClaims: Map<String?, Any?>?,
                                   userDetails: UserDetails,
                                   ttl:Duration): String {
        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + ttl.toMillis()))
            .signWith(signInKey, Jwts.SIG.HS256)
            .compact()
    }

    private fun isTokenExpired(token: String?): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String?): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser()
            .verifyWith(signInKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private val signInKey: SecretKey
        get() {
            val keyBytes = Decoders.BASE64.decode(jwtSecretKey)
            return Keys.hmacShaKeyFor(keyBytes)
        }
}
