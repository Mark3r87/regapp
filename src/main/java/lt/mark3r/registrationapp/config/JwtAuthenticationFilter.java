/**
 * File: JwtAuthenticationFilter.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the JwtAuthenticationFilter class, which extends OncePerRequestFilter to provide a filter that
 * executes once per request.
 */

package lt.mark3r.registrationapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The JwtAuthenticationFilter class extends OncePerRequestFilter to provide a filter that executes once per request.
 * It checks if the request has a valid JWT token in the Authorization header and sets the authentication in the
 * context.
 * <p>
 * Annotations:
 * - @Component: Indicates that an annotated class is a "component". Such classes are considered as candidates for
 * auto-detection when using annotation-based configuration and classpath scanning.
 * - @RequiredArgsConstructor: Generates a constructor with 1 parameter for each field that requires special handling.
 * All non-initialized final fields get a parameter, as well as any fields that are marked as @NonNull that aren't
 * initialized where they are declared.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	// Dependencies are automatically injected by Spring Boot
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;


	/**
	 * Checks if the request has a valid JWT token in the Authorization header and sets the authentication in
	 * the context.
	 *
	 * @param request     The HttpServletRequest.
	 * @param response    The HttpServletResponse.
	 * @param filterChain The FilterChain.
	 * @throws ServletException If there's an error during the filtering process.
	 * @throws IOException      If there's an error during the filtering process.
	 */
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
				);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}

