package ma.cigma.security.integration.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.TokenVo;
import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.presentation.AuthenticationController;
import ma.cigma.security.service.IUserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestAuthenticationController {

	@Autowired
	private IUserService userService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AuthenticationController authenticationController;

	@Test
	public void testHello() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/auth/hello",
				String.class)).contains("Bonjour tout le monde");
	}

	@Test
	public void testauthenticateUserIsNotNull() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);

		UserVo user = new UserVo();
		user.setUsername("admin1");
		user.setPassword("admin1");

		HttpEntity<UserVo> entity = new HttpEntity<UserVo>(user, headers);

		// TokenVo tokenTest=new TokenVo()

		assertThat(this.restTemplate.exchange("http://localhost:" + port + "/auth/signin", HttpMethod.POST, entity,
				TokenVo.class)).isNotNull();

	}

	@Test
	public void testauthenticateUserHasToken() throws Exception {

		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");
		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
		UserVo admin2 = new UserVo("admin2", "admin2", Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
		UserVo client2 = new UserVo("client2", "client2", Arrays.asList(roleClient));
		userService.save(admin1);
		userService.save(client1);
		userService.save(client2);
		userService.save(admin2);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);

		UserVo user = new UserVo();
		user.setUsername("admin1");
		user.setPassword("admin1");

		HttpEntity<UserVo> entity = new HttpEntity<UserVo>(user, headers);

		// TokenVo tokenTest=new TokenVo()

		ResponseEntity<TokenVo> response = this.restTemplate.exchange("http://localhost:" + port + "/auth/signin",
				HttpMethod.POST, entity, TokenVo.class);
		assertThat(response.getBody()).isNotNull();
		TokenVo t = (TokenVo) response.getBody();

		assertThat(((TokenVo) response.getBody()).getJwttoken()).isNotNull();
		assertThat(((TokenVo) response.getBody()).getRoles()).isNotEmpty();


	}
//
//	@Test
//	public void testauthenticateUserHasRole() throws Exception {
//
//		userService.save(new RoleVo("ADMIN"));
//		userService.save(new RoleVo("CLIENT"));
//
//		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
//		RoleVo roleClient = userService.getRoleByName("CLIENT");
//		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
//		UserVo admin2 = new UserVo("admin2", "admin2", Arrays.asList(roleAdmin));
//		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
//		UserVo client2 = new UserVo("client2", "client2", Arrays.asList(roleClient));
//		userService.save(admin1);
//		userService.save(client1);
//		userService.save(client2);
//		userService.save(admin2);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//		// Request to return JSON format
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		UserVo user = new UserVo();
//		user.setUsername("admin1");
//		user.setPassword("admin1");
//
//		HttpEntity<UserVo> entity = new HttpEntity<UserVo>(user, headers);
//
//		// TokenVo tokenTest=new TokenVo()
//
//		ResponseEntity<TokenVo> response = this.restTemplate.exchange("http://localhost:" + port + "/auth/signin",
//				HttpMethod.POST, entity, TokenVo.class);
//		assertThat(response.getBody()).isNotNull();
//		TokenVo t = (TokenVo) response.getBody();
//		List<String> roles = t.getRoles();
//
//		//assertThat(roles).isNotEmpty();
//		assertThat(t).isNotNull();
//		assertThat(roles).isNotNull();
//		assertThat(roles.get(0)).isEqualTo("ADMIN");
//	}

}
