package com.spring.board.member.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component("memberVo")
@Data
public class MemberVO {
	private String memberId;
	private String password;
	private String nickname;
	private Timestamp join_date;

}
