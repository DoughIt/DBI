package controller;

import dao.ImportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("serviceSqlite")
public class ImportServiceSqlite extends ImportServiceDao {

    @Autowired
    @Override
    public void setTemplate(DataSource sqliteDataSource, DataSource mysqlDataSource) {
        super.setTemplate(sqliteDataSource, mysqlDataSource);
    }

    @Override
    protected Map<String, String> getTablesEncode() {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name";
        SqlRowSet srs = template.queryForRowSet(sql);
        Map<String, String> map = new HashMap<>();
        while (srs.next()) {
            map.put(srs.getString("name"), "UTF-8");
        }
        return map;
    }
}
