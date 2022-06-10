package ma.cigma.security.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.cigma.security.domaine.FormationVo;
import ma.cigma.security.service.exception.BusinessException;
import ma.cigma.security.service.formation.IFormationService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FormationController {

	@Autowired
	private IFormationService formationService;

	@GetMapping(path = "/formations")
	public List<FormationVo> getAllFormations() {
		return formationService.getAll();
	}

	@GetMapping(path = "/formations/{id}")
	public FormationVo getFormationById(@PathVariable Long id) {
		return formationService.getById(id);
	}

	@PutMapping(path = "/formations/{id}")
	public ResponseEntity<String> save(@PathVariable Long id, @RequestBody FormationVo vo) {

		if (id == null)
			throw new BusinessException("The formation If is null !!");

		FormationVo voFound = formationService.getById(id);

		if (voFound == null)
			throw new BusinessException("No formation with id= " + id + " Id !!");

		vo.setId(id);
		try {
			formationService.save(vo);
			return new ResponseEntity<String>("Formation is saved with success", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException("Erreur technique.Veuillez consulter votre admin !!");
		}

	}

	@PostMapping(path = "/formations/create")
	public ResponseEntity<String> save(@RequestBody FormationVo vo) {
		try {
			formationService.save(vo);
			return new ResponseEntity<String>("Formation is created with success", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BusinessException("Erreur technique.Veuillez consulter votre admin !!");
		}
	}
}
