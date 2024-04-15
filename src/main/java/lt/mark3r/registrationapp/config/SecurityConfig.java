package lt.mark3r.registrationapp.config;

import lt.mark3r.registrationapp.services.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(new OrRequestMatcher(
								new AntPathRequestMatcher("/admin/**"),
								new AntPathRequestMatcher("/barber/**")
						)).hasAnyRole("ADMIN", "BARBER")
						.requestMatchers(String.valueOf(RequestMethod.GET), "/api/barbers/**").permitAll()
						.requestMatchers(new AntPathRequestMatcher("/guest/**")).permitAll()
						.requestMatchers(new AntPathRequestMatcher("/adminOnly/**")).hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}