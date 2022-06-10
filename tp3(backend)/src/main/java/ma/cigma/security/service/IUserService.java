package ma.cigma.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.cigma.security.domaine.ChangePasswordVo;
import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.UserVo;

public interface IUserService extends UserDetailsService{
	void save(UserVo user);
	void save(RoleVo role);
	List<UserVo> getAllUsers();
	List<RoleVo> getAllRoles();
	RoleVo getRoleByName(String role);
	void cleanDataBase();
	boolean existsByUsername(String username);
	void changePassword(ChangePasswordVo vo);
	UserVo findByUsername(String username);
	String sayHello();
}
