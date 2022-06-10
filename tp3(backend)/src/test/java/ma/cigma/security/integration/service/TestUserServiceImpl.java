package ma.cigma.security.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.cigma.security.dao.EmpRepository;
import ma.cigma.security.dao.RoleRepository;
import ma.cigma.security.dao.UserRepository;
import ma.cigma.security.service.IUserService;
import ma.cigma.security.service.model.Role;
import ma.cigma.security.service.model.User;

@SpringBootTest
class TestUserServiceImpl {

	@Autowired
	private IUserService userService;

	@MockBean
	private UserRepository userRepository;
	@MockBean
	private RoleRepository roleRepository;
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@MockBean
	private EmpRepository empRepository;

	@Test
	public void test_loadUserByUsername() {

		User userTest = new User();
		userTest.setId(1l);
		userTest.setUsername("admin");
		userTest.setPassword("admin");
		userTest.setRoles(Arrays.asList(new Role("ADMIN")));

		when(userRepository.findByUsername("admin")).thenReturn(userTest);

		UserDetails result = userService.loadUserByUsername("admin");

		assertThat(result.getUsername()).isEqualTo(userTest.getUsername());
		assertThat(result.getAuthorities()).hasSize(1);
		assertThat(((GrantedAuthority) (result.getAuthorities().toArray())[0]).getAuthority()).isEqualTo("ADMIN");

	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
		for (Role r : roles) {
			springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		return springSecurityAuthorities;
	}

	@Test
	public void checkDatabase() {

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
//		assertThat(userService.findByUsername("admin1")).isNotNull();
	}

}
