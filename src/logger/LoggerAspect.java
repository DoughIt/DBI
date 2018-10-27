package logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Around("execution(void dao.ImportServiceDao.readData())")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        Logger.reset();
        long time1 = System.currentTimeMillis();
        Object obj = point.proceed();
        System.out.println("耗时：" + (System.currentTimeMillis() - time1) + "ms");
        System.out.println("共读入" + Logger.getInsertCount() + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据读入失败！");
        return obj;
    }
}
