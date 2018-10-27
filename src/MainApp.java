import controller.ImportServiceCsv;
import controller.ImportServiceSqlite;
import controller.PreRead;
import logger.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //创建数据库
        PreRead preRead = (PreRead) context.getBean("preRead");
        preRead.create();


        //从csv数据源导入数据
        ImportServiceCsv serviceCsv = (ImportServiceCsv) context.getBean("serviceCsv");
        long time1 = System.currentTimeMillis();
        serviceCsv.readData();
        System.out.println("耗时：" + (System.currentTimeMillis() - time1) + "ms");
        System.out.println("共读入" + Logger.getInsertCount() + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据读入失败！");
        Logger.reset();

        //从sqlite数据源导入数据
        ImportServiceSqlite serviceSqlite = (ImportServiceSqlite) context.getBean("serviceSqlite");
        long time2 = System.currentTimeMillis();
        serviceSqlite.readData();
        System.out.println("耗时：" + (System.currentTimeMillis() - time2) + "ms");
        System.out.println("共读入" + Logger.getInsertCount() + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据读入失败！");
    }
}
