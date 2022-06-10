package ma.cigma.service;

import ma.cigma.dao.DaoImp;

public class ServiceImpl {
	
	private DaoImp dao;

    public ServiceImpl(DaoImp dao) {
        this.dao = dao;
    }

    public boolean query(String query) {
        return dao.isAvailable();
    }


    @Override
    public String toString() {
        return "Using database with id: " + String.valueOf(dao.getUniqueId());
    }

}
