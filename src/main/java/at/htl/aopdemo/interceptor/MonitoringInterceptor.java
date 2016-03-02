package at.htl.aopdemo.interceptor;

import at.htl.aopdemo.annotation.Monitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * User: simonammer
 * Date: 29.02.16
 */
@Interceptor
@Monitoring
@Priority(Interceptor.Priority.APPLICATION + 200)
public class MonitoringInterceptor implements Serializable {

  private static Logger LOGGER = LoggerFactory.getLogger(MonitoringInterceptor.class);

  @AroundInvoke
  public Object intercept(InvocationContext ctx) throws Exception {
    long startTime = System.currentTimeMillis();
    Object result = ctx.proceed();
    long totalTime = System.currentTimeMillis() - startTime;
    if (totalTime > 0) {
      LOGGER.info("{}|Invocation time of {} {}ms ", ctx.getTarget().getClass().getSimpleName(), ctx.getMethod().getName(), totalTime);
    }

    return result;

  }
}
