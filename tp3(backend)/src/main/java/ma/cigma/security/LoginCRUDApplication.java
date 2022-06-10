package ma.cigma.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.cigma.security.domaine.EmpVo;
import ma.cigma.security.domaine.FormationVo;
import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.service.IEmpService;
import ma.cigma.security.service.IUserService;
import ma.cigma.security.service.formation.IFormationService;

@SpringBootApplication
public class LoginCRUDApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;
	@Autowired
	private IEmpService empService;
	
	@Autowired
	private IFormationService formationService;

	public static void main(String[] args) {
		SpringApplication.run(LoginCRUDApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		userService.cleanDataBase();
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

		// *************
		empService.save(new EmpVo("emp1", 10000d, "Fonction1"));
		empService.save(new EmpVo("emp2", 20000d, "Fonction3"));
		empService.save(new EmpVo("emp3", 30000d, "Fonction4"));
		empService.save(new EmpVo("emp4", 40000d, "Fonction5"));
		empService.save(new EmpVo("emp5", 50000d, "Fonction6"));
		
 		
		List<FormationVo> formations=Arrays.asList(
				new FormationVo("Java SE", "Classe, Interfaces, Polymorphisme ..."),
				new FormationVo("Java EE","JPA, JSF, Servlet, JSP, ..."),
				new FormationVo("Spring","Spring IOC, Spring AOP, Spring MVC ..."),
				new FormationVo("Angular","Module, Component, Template, Directive, Service, FormsModule, Reactive Forms...")
				
				);
		
		formationService.saveAll(formations);
		
	}

}
