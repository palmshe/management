package com.genius.shiro.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
