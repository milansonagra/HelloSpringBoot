package com.example.crud;

import com.example.model.TestTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class LearnCrud {
    private final static String DB_NAME = "public";
    private JdbcTemplate template;

    public LearnCrud(JdbcTemplate template) {
        this.template = template;
    }

    public List<TestTable> readTestTable() {
        String sqlTestQuery = "SELECT * FROM " + DB_NAME + ".test_table";

        List<TestTable> result = template.query(sqlTestQuery, rowMapperTestTable());

        return result;
    }

    public TestTable readSpecificTestTableEntry(int id) {
        String sqlTestQuery = "SELECT * FROM " + DB_NAME + ".test_table WHERE id=?";

        List<TestTable> result = template.query(sqlTestQuery,  new Object[]{id}, rowMapperTestTable());

        return result.isEmpty() ? null : result.get(0);
    }

    private static RowMapper<TestTable> rowMapperTestTable() {
        return (ResultSet rs, int rowNum) -> {
            TestTable tt = new TestTable();
            tt.setId(rs.getInt("id"));
            tt.setText(rs.getString("text"));
            return tt;
        };
    }
}
