/**
 * File: JwtService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the JwtService class, which provides methods for handling JWT tokens.
 */

package lt.mark3r.registrationapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The JwtService class provides methods for handling JWT tokens.
 * It includes methods for extracting claims from a token, generating a token, and validating a token.
 * <p>
 * Annotations:
 * - @Service: Indicates that an annotated class is a "Service", originally defined by Domain-Driven Design
 * (Evans, 2003) as "an operation offered as an interface that stands alone in the model".
 */
@Service
public class JwtService {

	// The secret key used for signing the JWT tokens
	private static final String SECRET_KEY = "9NXiJVh9IggaC6C1tTn3Bdr3WDvYrNEAcLbcNNJ6cck";

	/**
	 * Extracts the username from a JWT token.
	 *
	 * @param token The JWT token.
	 * @return The username.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts a claim from a JWT token.
	 *
	 * @param token          The JWT token.
	 * @param claimsResolver The function to apply to the claims.
	 * @return The claim.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}


	/**
	 * Generates a JWT token for a user.
	 *
	 * @param userDetails The user's details.
	 * @param userId      The user's ID.
	 * @return The JWT token.
	 */
	public String generateToken(UserDetails userDetails, String userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);


		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		List<String> roles = authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		claims.put("roles", roles);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
				.signWith(SignatureAlgorithm.HS256, getSignInKey())
				.compact();
	}


	/**
	 * Generates a JWT token for a user.
	 *
	 * @param userDetails The user's details.
	 * @return The JWT token.
	 */
	public String generateToken(UserDetails userDetails) {
		if (userDetails instanceof lt.mark3r.registrationapp.model.AppUser) {
			lt.mark3r.registrationapp.model.AppUser appUser = (lt.mark3r.registrationapp.model.AppUser) userDetails;
			return generateToken(userDetails, appUser.getId().toString());
		}
		throw new IllegalArgumentException
				("UserDetails must be an instance of lt.mark3r.registrationapp.model.AppUser");
	}

	/**
	 * Checks if a JWT token is valid for a user.
	 *
	 * @param token       The JWT token.
	 * @param userDetails The user's details.
	 * @return True if the token is valid, false otherwise.
	 */
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	/**
	 * Checks if a JWT token has expired.
	 *
	 * @param token The JWT token.
	 * @return True if the token has expired, false otherwise.
	 */
	private boolean isTokenExpired(String token) {
		return extractExpirationDate(token).before(new Date());
	}

	/**
	 * Extracts the expiration date from a JWT token.
	 *
	 * @param token The JWT token.
	 * @return The expiration date of the token.
	 */
	private Date extractExpirationDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extracts all claims from a JWT token.
	 *
	 * @param token The JWT token.
	 * @return The claims in the token.
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

	}

	/**
	 * Gets the sign-in key for the JWT tokens.
	 *
	 * @return The sign-in key.
	 */
	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

