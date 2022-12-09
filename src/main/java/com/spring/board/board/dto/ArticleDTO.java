package com.spring.board.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("articleDTO")
@Getter@Setter
public class ArticleDTO {
	private int articleNO;
	private int parentNO;
	private int viewCount;
	private String title;
	private String content;
	private String id;
	private Date  writeDate;



}
