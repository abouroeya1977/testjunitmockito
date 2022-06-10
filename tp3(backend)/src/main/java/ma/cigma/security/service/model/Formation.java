package ma.cigma.security.service.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Formation implements Serializable {
	private static final long serialVersionUID = -6513147759788560011L;

	@Id
	@GeneratedValue
	private Long id;
	private String intitule;
	private String contenu;

	public Formation(String intitule, String contenu) {
		super();
		this.intitule = intitule;
		this.contenu = contenu;
	}

}
