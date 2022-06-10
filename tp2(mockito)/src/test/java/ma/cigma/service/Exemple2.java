package ma.cigma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ma.cigma.dao.DaoImp;

@ExtendWith(MockitoExtension.class)
public class Exemple2 {

	@Mock
	DaoImp dao;

	@Test
	public void ensureMockitoReturnsTheConfiguredValue() {

		// define return value for method getUniqueId()
		when(dao.getUniqueId()).thenReturn(42);

		ServiceImpl service = new ServiceImpl(dao);
		// use mock in test....
		assertEquals(service.toString(), "Using database with id: 42");
	}

}
