package controller;

import dao.ImportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component("serviceSqlite")
public class ImportServiceSqlite extends ImportServiceDao {

    @Autowired
    @Override
    public void setTemplate(DataSource sqliteDataSource, DataSource mysqlDataSource) {
        super.setTemplate(sqliteDataSource, mysqlDataSource);
    }

    @Override
    protected List<String> getTables() {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name";
        SqlRowSet srs = template.queryForRowSet(sql);
        List<String> tables = new ArrayList<>();
        while (srs.next()) {
            tables.add(srs.getString("name"));
        }
        return tables;
    }
}
