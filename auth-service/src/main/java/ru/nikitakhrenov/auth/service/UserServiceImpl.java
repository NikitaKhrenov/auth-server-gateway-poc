package ru.nikitakhrenov.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nikitakhrenov.auth.domain.Origin;
import ru.nikitakhrenov.auth.domain.User;
import ru.nikitakhrenov.auth.exception.UsernameAlreadyExistsException;
import ru.nikitakhrenov.auth.repository.RoleRepository;
import ru.nikitakhrenov.auth.repository.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User create(User user, Origin origin) {

		User existing = repository.findByEmail(user.getEmail());

		if (existing != null) {
			log.info("user {} already exists", existing.getEmail());
			throw new UsernameAlreadyExistsException(existing.getEmail());
		}

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		user.setOrigin(origin);
		User saved = repository.save(user);

		log.info("new user has been created: {}", user.getUsername());
		return saved;
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
