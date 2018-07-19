package ru.nikitakhrenov.auth.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikitakhrenov.auth.domain.Origin;
import ru.nikitakhrenov.auth.domain.User;
import ru.nikitakhrenov.auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username);

		if (user == null || !Origin.NATIVE.equals(user.getOrigin())) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
