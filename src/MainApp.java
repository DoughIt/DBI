import controller.PreRead;
import dao.ImportServiceDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //创建数据库
        PreRead preRead = (PreRead) context.getBean("preRead");
        preRead.create();

        System.out.print("\n1：csv\n2：sqlite\n3: csv & sqlite\n选择数据源（输入数字）：");

        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.next();
        switch (numStr.trim()) {
            case "1":
            case "csv": //从csv数据源导入数据
                ((ImportServiceDao) context.getBean("serviceCsv")).readData();
                break;
            case "2":
            case "sqlite": //从sqlite数据源导入数据
                ((ImportServiceDao) context.getBean("serviceSqlite")).readData();
                break;
            case "3":
            case "csv & sqlite":
                ((ImportServiceDao) context.getBean("serviceCsv")).readData();
                ((ImportServiceDao) context.getBean("serviceSqlite")).readData();
                break;
            default:
                break;
        }
    }
}
