package ma.cigma.security.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.security.service.model.Formation;

public class FormationConverter {

	public static FormationVo toVo(Formation bo) {
		FormationVo vo = new FormationVo();
		vo.setId(bo.getId());
		vo.setIntitule(bo.getIntitule());
		vo.setContenu(bo.getContenu());
		return vo;
	}

	public static Formation toBo(FormationVo vo) {
		Formation bo = new Formation();
		bo.setId(vo.getId());
		bo.setIntitule(vo.getIntitule());
		bo.setContenu(vo.getContenu());

		return bo;
	}

	public static List<FormationVo> toVos(List<Formation> bos) {
		List<FormationVo> vos = new ArrayList<>();
		for (Formation bo : bos) {
			vos.add(toVo(bo));
		}
		return vos;
	}
	
	public static List<Formation> toBos(List<FormationVo> vos) {
		List<Formation> bos = new ArrayList<>();
		for (FormationVo vo : vos) {
			bos.add(toBo(vo));
		}
		return bos;
	}
}
