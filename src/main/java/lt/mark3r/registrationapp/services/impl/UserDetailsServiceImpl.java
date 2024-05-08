package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), getAuthorities(appUser));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(AppUser appUser) {
		return Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole().name()));
	}
}