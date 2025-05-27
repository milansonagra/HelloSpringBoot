package com.example.hello;

import com.example.crud.LearnCrud;
import com.example.model.TestTable;
import jakarta.websocket.server.PathParam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test-table")
public class TestController {

    private final LearnCrud dbInteraction;

    public TestController(JdbcTemplate jdbc) {
        dbInteraction = new LearnCrud(jdbc);
    }

    @GetMapping("/getAll")
    public List<TestTable> getAll() {
        return dbInteraction.readTestTable();
    }

    @GetMapping("/getOne")
    public TestTable getSpecific(@PathParam(value = "id") int id) {
        return dbInteraction.readSpecificTestTableEntry(id);
    }
}
