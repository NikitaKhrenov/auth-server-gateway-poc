package ru.nikitakhrenov.auth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.nikitakhrenov.auth.domain.Origin;
import ru.nikitakhrenov.auth.domain.User;
import ru.nikitakhrenov.auth.service.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class ThirdPartyLoginController {

    @Value("${google.client.clientId}")
    private String CLIENT_ID;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationServerTokenServices tokenService;

    @RequestMapping(value = "/{provider}")
    public ResponseEntity<OAuth2AccessToken> login(@PathVariable("provider") String provider,
                                                   @RequestParam Map<String, String> parameters) {
        String idToken = parameters.get("id_token");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idToken;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String aud = root.path("aud").asText();
        if (!CLIENT_ID.equals(aud)) {
            throw new OAuth2AccessDeniedException("Client is not registered: " + aud);
        }

        JsonNode emailNode = Objects.requireNonNull(root).path("email");
        String email = emailNode.asText();
        User user;
        User existing = userService.findByEmail(email);
        if (existing == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword("");
            user = userService.create(newUser, Origin.GOOGLE);
        } else {
            user = existing;
        }
        OAuth2AccessToken accessToken = tokenService.createAccessToken(convertAuthentication(user));
        return getResponse(accessToken);
    }

    private OAuth2Authentication convertAuthentication(User user) {
        OAuth2Request request = new OAuth2Request(null, "browser", null, true, null, null, null, null, null);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, "N/A", user.getAuthorities());
        return new OAuth2Authentication(request, authenticationToken);
    }

    private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<OAuth2AccessToken>(accessToken, headers, HttpStatus.OK);
    }

}
