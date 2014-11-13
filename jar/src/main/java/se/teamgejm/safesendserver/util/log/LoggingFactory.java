package se.teamgejm.safesendserver.util.log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@ApplicationScoped
public class LoggingFactory {

	@Produces
	Logger createLogger(InjectionPoint injectionPoint) {

		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());

	}
}
