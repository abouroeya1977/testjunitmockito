package demojunit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample5 {
	@Test
	void verifierLinesMatch() {
		List<String> expectedLines = Arrays.asList("A1", "A2", "A3", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	@Test
	void verifierLinesMatch2() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/*
	 * Il est aussi possible d'ignorer un ou plusieurs �l�ments durant la
	 * comparaison gr�ce � un marqueur d'avance rapide : ils peuvent par exemple
	 * permettre d'ignorer des �l�ments dont la valeur change � chaque ex�cution.
	 * 
	 * Un marqueur d'avance rapide commence et termine par �>>� et doit poss�der au
	 * moins quatre caract�res.
	 */
	@Test
	void verifierLinesMatch3() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", ">>>>", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "test", "email", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Il est possible de mettre une description entre les doubles chevrons : cette
	 * description sera ignor�e.
	 */

	@Test
	void verifierLinesMatch4() {
		List<String> expectedLines = Arrays.asList("(.*)@(.*)", ">> aller au dernier >>", "(.*)@(.*)");
		List<String> emails = Arrays.asList("test@gmail.com", "test", "email", "jm@test.fr");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Il est possible de pr�ciser un nombre exact d'�l�ments � ignorer.
	 * 
	 * 
	 */

	@Test
	void verifierLinesMatch5() {
		List<String> expectedLines = Arrays.asList("A1", ">> 2 >>", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}

	/**
	 * Si le nombre d'�l�ments � ignorer ne peut �tre atteint ou est insuffisant
	 * alors la m�thode l�ve une exception.
	 */

	@Test
	void verifierLinesMatch6() {

		List<String> expectedLines = Arrays.asList("A1", ">> 1 >>", "A4");
		List<String> emails = Arrays.asList("A1", "A2", "A3", "A4");
		Assertions.assertLinesMatch(expectedLines, emails);
	}
}
