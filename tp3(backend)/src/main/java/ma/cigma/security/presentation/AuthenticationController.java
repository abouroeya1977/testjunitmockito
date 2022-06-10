package ma.cigma.security.presentation;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.cigma.security.domaine.ChangePasswordVo;
import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.TokenVo;
import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.jwt.JwtUtils;
import ma.cigma.security.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private IUserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<TokenVo> authenticateUser(@Valid @RequestBody UserVo userVo) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userVo.getUsername(), userVo.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		TokenVo tokenVo = new TokenVo();
		tokenVo.setJwttoken(jwt);
		tokenVo.setUsername(userVo.getUsername());
		Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
		for (GrantedAuthority authorite : list) {
			tokenVo.getRoles().add(authorite.getAuthority());
		}

		return ResponseEntity.ok(tokenVo);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserVo userVo) {
		if (userService.existsByUsername(userVo.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		// par défaut, le client a le rôle CLIENT
		userVo.getRoles().add(new RoleVo("CLIENT"));
		userService.save(userVo);
		return ResponseEntity.ok("User registered successfully!");
	}

	@PostMapping("/changepassword")
	public ResponseEntity<?> changePasswordUser(@Valid @RequestBody ChangePasswordVo vo) {
		userService.changePassword(vo);
		return ResponseEntity.ok("A new password is correctyl registered");
	}

	@GetMapping(path = "/hello")
	public String hello() {
		return "Bonjour tout le monde";
	}
	
	@GetMapping(path = "/hello2")
	public String hello2() {
		return "Bonjour tout le monde";
	}
}