package com.spring.board;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@MapperScan(value = {"com.spring.board.board.dao.BoardDAOImpl"})
@ComponentScan(
        basePackages = "com.spring.board"//defualt
        )
public class AutoAppConfig {
}
