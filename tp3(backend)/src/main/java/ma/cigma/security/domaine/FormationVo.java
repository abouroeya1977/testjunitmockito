package ma.cigma.security.domaine;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormationVo implements Serializable {

	private static final long serialVersionUID = 1305165194514072547L;
	private Long id;
	private String intitule;
	private String contenu;

	public FormationVo(String intitule, String contenu) {
		super();
		this.intitule = intitule;
		this.contenu = contenu;
	}

}