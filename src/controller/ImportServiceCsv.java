package controller;

import dao.ImportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("serviceCsv")
public class ImportServiceCsv extends ImportServiceDao {
    @Value("${csvDataBase}")
    private String databasePath;

    @Autowired
    @Override
    public void setTemplate(DataSource csvDataSource, DataSource mysqlDataSource) {
        super.setTemplate(csvDataSource, mysqlDataSource);
    }

    @Override
    protected List<String> getTables() {
        List<String> tables = new ArrayList<>();
        File file = new File(databasePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null)
                return tables;
            for (File f : files) {
                tables.add(f.getName().substring(0, f.getName().indexOf(".csv")));
            }
        }
        return tables;
    }
}
