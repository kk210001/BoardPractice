package com.spring.board.board.vo;

import com.spring.board.paging.Pagination;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("articleVO")
@Getter@Setter
public class ArticleVO {
	private int articleNO;
	private int parentNO;
	private int viewCount;
	private String title;
	private String content;
	private String id;
	private Date  writeDate;



}
