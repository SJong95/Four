package vo;

import java.sql.Date;

public class ReBoard {
	private int BOARD_NUM;	// 어느 글의 댓글인지 구분하기 위한 글 번호
	private int REBOARD_NUM;	// 댓글의 번호
	private String REBOARD_NAME;	// 댓글 작성자
	private String REBOARD_PASS;	// 댓글 비밀번호
	private String REBOARD_CONTENT;	// 댓글 내용
	private int REBOARD_RE_REF;	 // 답글 작성 시 어느 댓글의 답글인지 구분하기 위함
	private int REBOARD_RE_SEQ;	 // 답글의 순서
	private Date REBOARD_DATE;	 // 댓글 작성한 날짜
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public int getREBOARD_NUM() {
		return REBOARD_NUM;
	}
	public void setREBOARD_NUM(int rEBOARD_NUM) {
		REBOARD_NUM = rEBOARD_NUM;
	}
	public String getREBOARD_NAME() {
		return REBOARD_NAME;
	}
	public void setREBOARD_NAME(String rEBOARD_NAME) {
		REBOARD_NAME = rEBOARD_NAME;
	}
	public String getREBOARD_PASS() {
		return REBOARD_PASS;
	}
	public void setREBOARD_PASS(String rEBOARD_PASS) {
		REBOARD_PASS = rEBOARD_PASS;
	}
	public String getREBOARD_CONTENT() {
		return REBOARD_CONTENT;
	}
	public void setREBOARD_CONTENT(String rEBOARD_CONTENT) {
		REBOARD_CONTENT = rEBOARD_CONTENT;
	}
	public int getREBOARD_RE_REF() {
		return REBOARD_RE_REF;
	}
	public void setREBOARD_RE_REF(int rEBOARD_RE_REF) {
		REBOARD_RE_REF = rEBOARD_RE_REF;
	}
	public int getREBOARD_RE_SEQ() {
		return REBOARD_RE_SEQ;
	}
	public void setREBOARD_RE_SEQ(int rEBOARD_RE_SEQ) {
		REBOARD_RE_SEQ = rEBOARD_RE_SEQ;
	}
	public Date getREBOARD_DATE() {
		return REBOARD_DATE;
	}
	public void setREBOARD_DATE(Date bOARD_DATE) {
		REBOARD_DATE = bOARD_DATE;
	}
	
	
	
}
