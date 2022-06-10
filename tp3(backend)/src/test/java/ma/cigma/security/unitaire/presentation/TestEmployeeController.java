package ma.cigma.security.unitaire.presentation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ma.cigma.security.domaine.EmpVo;
import ma.cigma.security.jwt.AuthEntryPointJwt;
import ma.cigma.security.jwt.JwtUtils;
import ma.cigma.security.presentation.EmployeeController;
import ma.cigma.security.service.IEmpService;
import ma.cigma.security.service.IUserService;
import ma.cigma.security.service.formation.IFormationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class, 
			useDefaultFilters = false, 
			includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = EmployeeController.class) })
@WithMockUser(authorities = {"ADMIN"},password = "admin1",username = "admin1")
public class TestEmployeeController {
	@MockBean
	private IEmpService service;

	@Autowired
	private MockMvc mvc;

	@MockBean
	AuthenticationManager authenticationManager;

	@MockBean
	private IUserService userService;

	@MockBean
	private JwtUtils jwtUtils;

	@MockBean
	AuthEntryPointJwt authEntryPointJwt;

	@MockBean
	IFormationService formationService;

	@Test
	void testgetAll() throws Exception {
		List<EmpVo> employees = Arrays.asList(
				new EmpVo("emp1", 10000d, "Fonction1"),
				new EmpVo("emp2", 20000d, "Fonction2"), 
				new EmpVo("emp", 30000d, "Fonction3"));
		when(service.getEmployees()).thenReturn(employees);
		mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("emp1"))
				.andExpect(jsonPath("$[1].fonction").value("Fonction2"))
				.andExpect(jsonPath("$[1].salary").value(20000d))
				.andExpect(jsonPath("$[2].salary").value(30000d));
	}

	@Test
	void testgetAll_empty() throws Exception {
		when(service.getEmployees()).thenReturn(null);
		mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$").doesNotExist());
	}
	@Test
	void testgetEmpByIdEmployeeExist() throws Exception {
		EmpVo empTest=new EmpVo();
		empTest.setId(1l);
		empTest.setFonction("INGENIEUR");
		empTest.setSalary(10000d);
		empTest.setName("Foulane");
		
		when(service.getEmpById(Mockito.any())).thenReturn(empTest);
		mvc.perform(get("/employees/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(empTest.getId()))
		.andExpect(jsonPath("$.name").value(empTest.getName()))
		.andExpect(jsonPath("$.fonction").value(empTest.getFonction()))
		.andExpect(jsonPath("$.salary").value(empTest.getSalary()));
	}
	
	@Test
	
	void testgetEmpByIdEmployeeDoesntExist() throws Exception {
		
		when(service.getEmpById(Mockito.any())).thenReturn(null);
		mvc.perform(get("/employees/{id}",1L)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value("employee doen't exist"));
	}
}
