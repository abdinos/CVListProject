package myboot.app1.web;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import myboot.app1.security.JwtProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import myboot.app1.model.XUser;
import myboot.app1.security.UserService;
import org.springframework.web.servlet.ModelAndView;

/**
 * L'API d'authentification
 */
@RestController
@RequestMapping("/secu-users")
@Profile("usejwt")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtProvider jwtProvider;

	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Authentification et récupération d'un JWT
	 */

	@PostMapping("/login")
	public String login(//
			@RequestParam String username, //
			@RequestParam String password) {
		return userService.login(username, password);
	}

	/**
	 * Ajouter un utilisateur
	 */
	@PostMapping("/signup")
	public String signup(@RequestBody XUserDTO user) {
		return userService.signup(modelMapper.map(user, XUser.class));
	}

	/**
	 * Supprimer un utilisateur
	 */
	@DeleteMapping("/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String delete(@PathVariable String username) {
		System.out.println("delete " + username);
		userService.delete(username);
		return username;
	}

	/**
	 * Récupérer des informations sur un utilisateur
	 */
	@GetMapping("/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public XUserDTO search(@PathVariable String username) {
		return modelMapper.map(userService.search(username), XUserDTO.class);
	}

	/**
	 * Récupérer des informations sur l'utilisateur courant
	 */
	@GetMapping(value = "/me")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public XUserDTO whoami(HttpServletRequest req) {
		return modelMapper.map(userService.whoami(req), XUserDTO.class);
	}

	/**
	 * Récupérer un nouveau JWT
	 */
	@GetMapping("/refresh")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}

	@GetMapping("/logout")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String logout(@RequestHeader ("Authorization") String res) {
//		HttpHeaders headers = new HttpHeaders();
//		var res = headers.getFirst("Authorization"); // Bearer JWT
		String jwt = res.split(" ")[1];
		jwtProvider.getJWTs().remove(jwt);
		return "user logged out";
	}
}
