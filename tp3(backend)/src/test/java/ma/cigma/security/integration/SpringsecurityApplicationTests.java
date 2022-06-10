package ma.cigma.security.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.cigma.security.presentation.AuthenticationController;

@SpringBootTest
class SpringsecurityApplicationTests {
	
	@Autowired
	private AuthenticationController authenticationController;

	@Test
	void contextLoads() {
		assertThat(authenticationController).isNotNull(); 
	}

}
