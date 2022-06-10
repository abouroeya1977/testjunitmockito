package ma.cigma.security.domaine;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmpVo implements Serializable {
	private static final long serialVersionUID = 1041173473008025285L;
	private Long id;
	private String name;
	private Double salary;
	private String fonction;
	public EmpVo() {
		super();
	}
	public EmpVo(Long id, String name, Double salary, String fonction) {
		this(name,salary,fonction);
		this.id = id;
	}
	
	public EmpVo(String name, Double salary, String fonction) {
		super();
		this.name = name;
		this.salary = salary;
		this.fonction = fonction;
	}
}