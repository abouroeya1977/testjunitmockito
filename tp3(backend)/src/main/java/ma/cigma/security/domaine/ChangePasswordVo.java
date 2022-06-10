package ma.cigma.security.domaine;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordVo implements Serializable{
	private String username;
	private String oldPassword;
	private String newPassword;
}
