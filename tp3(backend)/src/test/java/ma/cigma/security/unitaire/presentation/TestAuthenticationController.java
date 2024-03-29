package ma.cigma.security.unitaire.presentation;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.jwt.AuthEntryPointJwt;
import ma.cigma.security.jwt.JwtUtils;
import ma.cigma.security.presentation.AuthenticationController;
import ma.cigma.security.service.IEmpService;
import ma.cigma.security.service.IUserService;
import ma.cigma.security.service.formation.IFormationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthenticationController.class)
public class TestAuthenticationController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AuthenticationManager authenticationManager;

	@MockBean
	private IUserService userService;

	@MockBean
	private JwtUtils jwtUtils;

	@MockBean
	AuthEntryPointJwt authEntryPointJwt;

	@MockBean
	IEmpService empService;

	@MockBean
	IFormationService formationService;

	@Test
	public void testHelloShouldWork() throws Exception {
		this.mockMvc.perform(get("/auth/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is("Bonjour tout le monde")));
	}

	@Test
	public void testHelloShouldNotWork() throws Exception {
		this.mockMvc.perform(get("/auth/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", not("bbbbbbbb tout le monde")));
	}

	@Test
	void testauthenticateUser() throws Exception {
		String tokenTest = "AAAABBBBCCCCDDD";
		UserVo userVoTest = new UserVo();
		userVoTest.setUsername("admin");
		userVoTest.setPassword("admin");

//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//				userVoTest.getUsername(), userVoTest.getPassword());
//		
		Authentication authenticationResult = new UsernamePasswordAuthenticationToken(userVoTest.getUsername(),
				userVoTest.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ADMIN")));

		when(authenticationManager.authenticate(Mockito.any())).thenReturn(authenticationResult);
		when(jwtUtils.generateJwtToken(Mockito.any())).thenReturn(tokenTest);
		
		// when(authenticationManager.authenticate(authentication)).thenReturn(authenticationResult);
		// when(jwtUtils.generateJwtToken(authenticationResult)).thenReturn(tokenTest);
		

		mockMvc.perform(post("/auth/signin").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.jwttoken").value(tokenTest))
				.andExpect(jsonPath("$.username").value(userVoTest.getUsername()))
				.andExpect(jsonPath("$.roles[0]").value("ADMIN"));

	}

	@Test
	
	void testregisterUser_ExistDeja() throws Exception {
		UserVo userVoTest = new UserVo();
		userVoTest.setUsername("admin");
		userVoTest.setPassword("admin");

		when(userService.existsByUsername(userVoTest.getUsername())).thenReturn(true);
		mockMvc.perform(post("/auth/signup").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$").value("Error: Username is already taken!"));
	}

	@Test
	@Disabled
	void testregisterUser_DoesntExist() throws Exception {
		UserVo userVoTest = new UserVo();
		userVoTest.setUsername("admin");
		userVoTest.setPassword("admin");

		when(userService.existsByUsername(userVoTest.getUsername())).thenReturn(false);
		doNothing().when(userService).save(userVoTest);

		mockMvc.perform(post("/auth/signup").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk())
		.andExpect(jsonPath("$").value("User registered successfully!"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
