package ma.cigma.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ma.cigma.dao.DaoImp;

@ExtendWith(MockitoExtension.class)
public class Exemple1 {

	@Mock
	DaoImp dao;

	@Test
	public void testQuery() {
		assertNotNull(dao);
		when(dao.isAvailable()).thenReturn(true);
		ServiceImpl service = new ServiceImpl(dao);
		boolean check = service.query("* from t");
		assertTrue(check);
	}

}
