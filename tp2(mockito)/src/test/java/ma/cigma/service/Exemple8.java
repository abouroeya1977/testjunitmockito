package ma.cigma.service;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ma.cigma.dao.DaoImp;

@ExtendWith(MockitoExtension.class)
public class Exemple8 {

	@Test
	public void testVerify(@Mock DaoImp dao) {
		// create and configure mock
		when(dao.getUniqueId()).thenReturn(43);

		// call method testing on the mock with parameter 12
		dao.setUniqueId(12);
		dao.getUniqueId();
		dao.getUniqueId();

		// now check if method testing was called with the parameter 12
		verify(dao).setUniqueId(ArgumentMatchers.eq(12));

		// was the method called twice?
		verify(dao, times(2)).getUniqueId();

		// other alternatives for verifiying the number of method calls for a method
		verify(dao, never()).isAvailable();
		verify(dao, never()).setUniqueId(13);
		verify(dao, atLeastOnce()).setUniqueId(12);
		verify(dao, atLeast(2)).getUniqueId();

		// more options are
		// times(numberOfTimes)
		// atMost(numberOfTimes)
		// This let's you check that no other methods where called on this object.
		// You call it after you have verified the expected method calls.
		verifyNoMoreInteractions(dao);
	}
}
