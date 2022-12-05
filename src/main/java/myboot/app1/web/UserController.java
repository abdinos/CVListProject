package myboot.app1.web;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.Person;
import myboot.app1.security.JwtProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import myboot.app1.model.XUser;
import myboot.app1.security.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;

/**
 * L'API d'authentification
 */
@RestController
@RequestMapping("/users")
@Profile("usejwt")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	XUserRepository xUserRepository;

	/*** Authentification et récupération d'un JWT*/
	@PostMapping("/loginUser")
	public String login(@RequestBody XUser body) {
		return userService.login(body.getUsername(), body.getPassword());
	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class UserData{
		private String username;
		private String password;

	}

	@GetMapping("/userInfo")
	public ResponseEntity<String> getUsername(@RequestHeader String token){
		var username =jwtProvider.getUsername(token);
		return ResponseEntity.ok(username);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Token{
		private String token;
	}

	/**
	 * Ajouter un utilisateur
	 */
	/**
	 * Supprimer un utilisateur
	 */
	@DeleteMapping("/{username}")
	public String delete(@PathVariable String username) {
		System.out.println("delete " + username);
		userService.delete(username);
		return username;
	}

	/**
	 * Récupérer des informations sur un utilisateur
	 */

	/**
	 * Récupérer des informations sur l'utilisateur courant
	 */
	/**
	 * Récupérer un nouveau JWT
	 */
	@GetMapping("/refresh")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}

	@GetMapping("/logout")
	public String logout(@RequestHeader ("Authorization") String res) {
		String jwt = res.split(" ")[1];
		jwtProvider.getJWTs().remove(jwt);
		return "user logged out";
	}


}


