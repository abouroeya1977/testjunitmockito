package ma.cigma.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.security.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);
	boolean existsByUsername(String username);
}
