package ma.cigma.dao;

public class DaoImp {
	private Integer uniqueId;

	public boolean isAvailable() {
		System.out.println("isAvailable est exécutée");
		return false;
	}

	public int getUniqueId() {
		System.out.println("getUniqueId est exécutée");
		return 42;
	}

	public void setUniqueId(Integer uniqueId) {
		System.out.println("setUniqueId est exécutée");
		this.uniqueId = uniqueId;
	}

}
