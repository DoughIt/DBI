package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("preRead")
public class PreRead {
    private static JdbcTemplate template;
    @Value("${createSQL}")
    public String createSQL;

    @Autowired
    public void setDataSource(DataSource mysqlDataSource) {
        template = new JdbcTemplate(mysqlDataSource);
    }

    public void create() {
        template.batchUpdate(createSQL.split(";"));
    }
}
