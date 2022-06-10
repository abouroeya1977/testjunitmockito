package ma.cigma.security.service.formation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.cigma.security.dao.FormationRepositiry;
import ma.cigma.security.domaine.FormationConverter;
import ma.cigma.security.domaine.FormationVo;

@Service
@Transactional
public class FormationServiceImpl implements IFormationService {
	@Autowired
	private FormationRepositiry formationRepositiry;

	@Override
	public List<FormationVo> getAll() {
		return FormationConverter.toVos(formationRepositiry.findAll());
	}
	
	@Override
	public void save(FormationVo vo) {
		formationRepositiry.save(FormationConverter.toBo(vo));
	}

	@Override
	public void saveAll(List<FormationVo> vos) {
		formationRepositiry.saveAll(FormationConverter.toBos(vos));
		
	}

	@Override
	public FormationVo getById(Long id) {
		return FormationConverter.toVo(formationRepositiry.getOne(id));
	}

}
