package ru.nikitakhrenov.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitakhrenov.auth.domain.Origin;
import ru.nikitakhrenov.auth.domain.User;
import ru.nikitakhrenov.auth.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getCurrent(Principal principal) {
	    OAuth2Authentication authentication = (OAuth2Authentication) principal;
		return principal;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void create(@Valid @RequestBody User user) {
        userService.create(user, Origin.NATIVE);
    }
}
