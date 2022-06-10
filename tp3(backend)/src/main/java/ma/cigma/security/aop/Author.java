package ma.cigma.security.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * les types permis pour les paramétres des annotations : - String - primitifs
 * (byte, short, int, long, double, float, char et boolean) - java.lang.Class -
 * ENUM - les tableaux des types ci-dessus à une seule dimension.
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
	String name();

	String date();
}
