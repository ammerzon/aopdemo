package at.htl.aopdemo.interceptor;

import at.htl.aopdemo.annotation.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Logging
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 100)
public class LoggingInterceptor implements Serializable {

  private static Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

  @AroundInvoke
  public Object manageTransaction(InvocationContext ctx) throws Exception {
    LOGGER.info("Entering method: {}", ctx.getMethod().getName());
    return ctx.proceed();
  }
}
