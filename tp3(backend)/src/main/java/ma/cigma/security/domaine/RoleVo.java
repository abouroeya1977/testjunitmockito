package ma.cigma.security.domaine;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleVo implements Serializable {
	private static final long serialVersionUID = 3672308682249280466L;
	private int id;
	private String role;

	public RoleVo(String role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object obj) {
		return role.equalsIgnoreCase(((RoleVo)obj).getRole());
	}
	
	@Override
	public String toString() {
		return this.role;
	}
}