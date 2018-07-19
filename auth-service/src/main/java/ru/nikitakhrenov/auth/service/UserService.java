package ru.nikitakhrenov.auth.service;

import ru.nikitakhrenov.auth.domain.Origin;
import ru.nikitakhrenov.auth.domain.User;

public interface UserService {

	/**
	 * Creates new user
	 *
	 * @param user
	 * @return created user or null if user with such username already exists
	 */
	User create(User user, Origin origin);

	User findByEmail(String email);

}
