package ma.cigma.dao;

public class DaoImp {
	private Integer uniqueId;

	public boolean isAvailable() {
		System.out.println("isAvailable est ex�cut�e");
		return false;
	}

	public int getUniqueId() {
		System.out.println("getUniqueId est ex�cut�e");
		return 42;
	}

	public void setUniqueId(Integer uniqueId) {
		System.out.println("setUniqueId est ex�cut�e");
		this.uniqueId = uniqueId;
	}

}
