import controller.PreRead;
import dao.ImportServiceDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Scanner scanner = new Scanner(System.in);

        //创建数据库
        PreRead preRead = (PreRead) context.getBean("preRead");
        preRead.create();

        //是否删除已有数据
        System.out.print("是否删除已有数据表？\n输入‘yes’（确认删除），或者其他任意键（不删除，可能导致编码或字段格式不一致问题）:");
        String numStr = scanner.next();
        boolean dropTable = numStr.toLowerCase().equals("yes");

        System.out.print("\n1：csv\n2：sqlite\n3: csv & sqlite\n选择数据源（输入数字）：");
        numStr = scanner.next();
        switch (numStr.trim().toLowerCase()) {
            case "1":
            case "csv": //从csv数据源导入数据
                ((ImportServiceDao) context.getBean("serviceCsv")).readData(dropTable);
                break;
            case "2":
            case "sqlite": //从sqlite数据源导入数据
                ((ImportServiceDao) context.getBean("serviceSqlite")).readData(dropTable);
                break;
            case "3":
            case "csv & sqlite":
                ((ImportServiceDao) context.getBean("serviceCsv")).readData(dropTable);
                ((ImportServiceDao) context.getBean("serviceSqlite")).readData(dropTable);
                break;
            default:
                break;
        }
    }
}
