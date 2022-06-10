package ma.cigma.security.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.UserVo;

class Tests {

	@Test
	void test() {
		// basic assertions
		UserVo vo=new UserVo("admin", "admin", Arrays.asList(new RoleVo("ADMIN"),new RoleVo("CLIENT")));
		assertThat(vo.getUsername()).isEqualTo("admin");
		assertThat(vo.getUsername()).isNotEqualTo("wrongusername");

		// chaining string specific assertions
		assertThat(vo.getUsername()).startsWith("a")
		                           .endsWith("in")
		                           .isEqualToIgnoringCase("ADMIN");

		// collection specific assertions (there are plenty more)
		// in the examples below fellowshipOfTheRing is a List<TolkienCharacter>
		assertThat(vo.getRoles()).hasSize(2)
		                               .contains(new RoleVo("ADMIN"))
		                               .doesNotContain(new RoleVo("SUPER_ADMIN"));

		// as() is used to describe the test and will be shown before the error message
		assertThat(vo.getRoles()).as("check %s's roles", vo.getUsername()).contains(new RoleVo("SUPER_ADMIN"));
		
		// exception assertion, standard style ...
		assertThatThrownBy(() -> { throw new Exception("boom!"); }).hasMessage("boom!");
		// ... or BDD style
		Throwable thrown = catchThrowable(() -> { throw new Exception("boom!"); });
		assertThat(thrown).hasMessageContaining("boom");
	}

}
