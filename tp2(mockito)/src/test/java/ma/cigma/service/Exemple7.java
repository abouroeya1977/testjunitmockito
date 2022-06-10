package ma.cigma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Exemple7 {
	
	@Spy
	List<String> list =Arrays.asList("spy","mock");
	
	@Test
	public void testLinkedListSpyCorrect() {
		
		//when(list.get(0)).thenReturn("spy");
		assertEquals("spyll",list.get(0));
		doReturn("foo").when(list).get(0);
		assertEquals("foo", list.get(0));
		
		
	}

}
