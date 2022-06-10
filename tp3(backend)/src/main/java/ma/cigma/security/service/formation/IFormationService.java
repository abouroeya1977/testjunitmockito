package ma.cigma.security.service.formation;

import java.util.List;

import ma.cigma.security.domaine.FormationVo;

public interface IFormationService {
	List<FormationVo> getAll();
	void save(FormationVo vo);
	void saveAll(List<FormationVo> vos);
	FormationVo getById(Long id);
}
