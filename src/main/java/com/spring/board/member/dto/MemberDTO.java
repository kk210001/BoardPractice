package com.spring.board.member.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Component("memberDTO")
@Data
public class MemberDTO {
	@NotEmpty
	private String memberId;
	@NotEmpty
	private String password;
	@NotEmpty
	private String nickname;

	private Timestamp join_date;

}
