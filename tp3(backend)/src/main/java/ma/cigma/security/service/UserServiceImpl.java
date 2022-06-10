package ma.cigma.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.cigma.security.aop.LogExecutionTime;
import ma.cigma.security.dao.EmpRepository;
import ma.cigma.security.dao.RoleRepository;
import ma.cigma.security.dao.UserRepository;
import ma.cigma.security.domaine.ChangePasswordVo;
import ma.cigma.security.domaine.RoleConverter;
import ma.cigma.security.domaine.RoleVo;
import ma.cigma.security.domaine.UserConverter;
import ma.cigma.security.domaine.UserVo;
import ma.cigma.security.service.exception.BusinessException;
import ma.cigma.security.service.model.Role;
import ma.cigma.security.service.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmpRepository empRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@LogExecutionTime
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
		for (Role r : roles) {
			springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		return springSecurityAuthorities;
	}

	@Override
	@LogExecutionTime
	public void save(UserVo userVo) {
		User user = UserConverter.toBo(userVo);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<Role> rolesPersist = new ArrayList<>();
		for (Role role : user.getRoles()) {
			Role userRole = roleRepository.findByRole(role.getRole()).get(0);
			rolesPersist.add(userRole);
		}
		user.setRoles(rolesPersist);
		userRepository.save(user);
	}

	@Override
	public void save(RoleVo roleVo) {
		roleRepository.save(RoleConverter.toBo(roleVo));
	}

	@Override
	public List<UserVo> getAllUsers() {
		return UserConverter.toVoList(userRepository.findAll());
	}

	@Override
	public List<RoleVo> getAllRoles() {
		return RoleConverter.toVoList(roleRepository.findAll());
	}

	@Override
	public RoleVo getRoleByName(String role) {
		return RoleConverter.toVo(roleRepository.findByRole(role).get(0));
	}

	@Override
	public void cleanDataBase() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
		empRepository.deleteAll();
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public void changePassword(ChangePasswordVo vo) {
		if (vo.getOldPassword() == null)
			throw new BusinessException("A current password must be set !!");

		if (vo.getNewPassword() == null)
			throw new BusinessException("A new password must be set !!");

		if (vo.getOldPassword().equals(vo.getNewPassword()))
			throw new BusinessException("the passwords are the same !!");

		UserVo userVo = findByUsername(vo.getUsername());
		userVo.setPassword(vo.getNewPassword());
		try {
			save(userVo);
		} catch (Exception e) {
			throw new BusinessException("An error with saving the password. connect your administrator !!");
		}

	}

	@Override
	public UserVo findByUsername(String username) {
		if (username == null || username.trim().equals(""))
			throw new BusinessException("login is empty !!");

		User bo = userRepository.findByUsername(username);

		if (bo == null)
			throw new BusinessException("No user with this login");

		UserVo vo = UserConverter.toVo(bo);
		return vo;
	}

	@Override
	public String sayHello() {
		return "Hello";
	}

}