package py.edu.uca.test.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import py.edu.uca.test.domain.UserEntity;
import py.edu.uca.test.security.AuthenticationValidation;
import py.edu.uca.test.service.AuthenticationService;
import py.edu.uca.test.service.UserService;
import py.edu.uca.test.web.session.SessionManager;
import py.edu.uca.test.web.session.UserCurrentInfo;

@Api(value = "Autenticacion", description = "Operaciones para hacer login y logout")
@Controller
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private UserService userService;

	private static Collection<GrantedAuthority> getGrantedAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		Collection<GrantedAuthority> unmodifiableCollection = Collections
				.unmodifiableCollection(authorities);
		return unmodifiableCollection;
	}

	@ApiOperation(value = "User authentication", notes = "Authenticate a user")
	@RequestMapping(value = "/api/authentication/login", method = RequestMethod.POST)
	public @ResponseBody AuthenticationResponse authenticate(
			HttpServletRequest request, HttpServletResponse httpResponse,
			@RequestBody AuthenticationRequest authenticationRequest) {
		AuthenticationResponse authResponse = new AuthenticationResponse();
		String user = authenticationRequest.getUser();
		if (user == null || user.trim().isEmpty()) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			authResponse
					.setMessage("El usuario no puede ser nulo o vacío");
			return authResponse;
		}

		String password = authenticationRequest.getPassword();
		if (password == null || password.trim().isEmpty()) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			authResponse
					.setMessage("La contraseña no puede ser nula o vacía");
			return authResponse;
		}

		AuthenticationValidation checkCredentials = authenticationService
				.checkCredentials(user, password);
		if (!checkCredentials.getSuccess()) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			authResponse.setMessage(checkCredentials.getMessage());
		} else {
			authResponse.setAuthenticated(true);
			authResponse.setMessage("Autenticado");
			UserEntity userEntity = userService.findByMail(user);
			SessionManager sessionManager = new SessionManager(request);
			sessionManager.setUser(UserCurrentInfo.fromUserEntity(userEntity));
			// Add user to Spring security context
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
					user, password, getGrantedAuthorities());
			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(userToken);
		}

		return authResponse;
	}

	@ApiOperation(value = "Logout", notes = "End current session (if any)")
	@RequestMapping(value = "/api/authentication/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SessionManager mgr = new SessionManager(request);
		if (mgr.isOpen()) {
			mgr.invalidate();
		}
	}

	public static class AuthenticationResponse {
		private String message;

		private boolean authenticated;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public boolean isAuthenticated() {
			return authenticated;
		}

		public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}

	}

	public static class AuthenticationRequest {

		private String user;

		private String password;

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

}
