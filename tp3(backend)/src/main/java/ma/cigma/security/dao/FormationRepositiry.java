package ma.cigma.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.security.service.model.Formation;

public interface FormationRepositiry extends JpaRepository<Formation,Long> {

}
