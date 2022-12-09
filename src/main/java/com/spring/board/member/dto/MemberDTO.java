package com.spring.board.member.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component("memberDTO")
@Data
public class MemberDTO {
	private String memberId;
	private String password;
	private String nickname;
	private Timestamp join_date;

}
