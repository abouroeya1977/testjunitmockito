package ma.cigma.security.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ma.cigma.security.service.exception.BusinessException;

@Aspect
@Component
public class AuthenticationAspect {
	@Around("@annotation(admin1Profile)")
	public Object test(ProceedingJoinPoint joinPoint,Admin1Profile admin1Profile) throws Throwable {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		if (!userName.equals(admin1Profile.nom())) {
			System.out.println("Only user admin who is authorized to call this method");
			throw new BusinessException("Only user admin1 who is authorized to call this method");
		}
		Object proceed = joinPoint.proceed();
		return proceed;
	}

}
