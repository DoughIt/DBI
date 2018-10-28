package controller;

import dao.ImportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component("serviceCsv")
public class ImportServiceCsv extends ImportServiceDao {
    @Value("${csvdatabase}")
    private String databasePath;

    @Autowired
    @Override
    public void setTemplate(DataSource csvDataSource, DataSource mysqlDataSource) {
        super.setTemplate(csvDataSource, mysqlDataSource);
    }

    @Override
    protected Map<String, String> getTablesEncode() {
        Map<String, String> map = new HashMap<>();
        File file = new File(databasePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null)
                return map;
            for (File f : files) {
                map.put(f.getName().substring(0, f.getName().indexOf(".csv")), Util.getFileEncode(f));
            }
        }
        return map;
    }
}
