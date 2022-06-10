package ma.cigma.security.integration.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ma.cigma.security.domaine.EmpVo;
import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.TokenVo;
import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.jwt.JwtUtils;
import ma.cigma.security.service.IEmpService;
import ma.cigma.security.service.IUserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestEmpController {
	@LocalServerPort
	private int port;

	@MockBean
	private IEmpService service;

	@Spy
	private IUserService userService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JwtUtils jwtUtils;

	@Test
	void testgetEmp() {
		//userService.save(new RoleVo("ADMIN"));
		UserVo user = new UserVo("admin1", "admin1", Arrays.asList(userService.getRoleByName("ADMIN")));
		//userService.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);

		String token = jwtUtils.generateJwtTokenWithString(user.getUsername());

		headers.add("Authorization", "Bearer " + token);

		HttpEntity<EmpVo[]> entity = new HttpEntity<EmpVo[]>(headers);

		// TokenVo tokenTest=new TokenVo()

		ResponseEntity<EmpVo[]> response = this.restTemplate.exchange("http://localhost:" + port + "/employees",
				HttpMethod.GET, entity, EmpVo[].class);
		assertThat(response.getBody()).isNotNull();


	}

}
