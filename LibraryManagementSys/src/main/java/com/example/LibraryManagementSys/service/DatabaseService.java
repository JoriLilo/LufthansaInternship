package com.example.LibraryManagementSys.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private static final Logger log = LogManager.getLogger(DatabaseService.class);

    public void connect() {
        log.info("Database connection established.");
    }

    public void runQuery(String sql) {
        log.debug("Executing SQL query... {}", sql);
    }

    public void failQuery(String sql) {
        log.error("Query execution failed — sql={}", sql);
    }
}