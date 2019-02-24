package vo;

import java.sql.Date;
public class Board {
	private int BOARD_NUM;	// 영상 번호
	private String BOARD_LINK;	// 영상 링크
	private String BOARD_LINK_VIDEO;	// 영상 재생에 사용할 링크
	private String BOARD_LINK_TITLE;	// 영상 타이틀 정보
	private String BOARD_LINK_IMAGE;	// 영상 이미지 정보
	private String BOARD_LINK_NAME;		// 원본 영상 게시자 이름
	private String BOARD_NAME;	// 글 작성자
	private String BOARD_PASS;	// 글 비밀번호
	private String BOARD_SUBJECT;	// 글 제목
	private String BOARD_CONTENT;	// 글 내용
	private String BOARD_CATEGORY;	// 등록시 선택한 카테고리
	private Date BOARD_DATE;		// 등록 날짜
	private int BOARD_LIKE;			// 글의 추천 수
	private int BOARD_REPORT;		// 글의 신고 수
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_LINK() {
		return BOARD_LINK;
	}
	public void setBOARD_LINK(String bOARD_LINK) {
		BOARD_LINK = bOARD_LINK;
	}
	public String getBOARD_LINK_VIDEO() {
		return BOARD_LINK_VIDEO;
	}
	public void setBOARD_LINK_VIDEO(String bOARD_LINK_VIDEO) {
		BOARD_LINK_VIDEO = bOARD_LINK_VIDEO;
	}
	public String getBOARD_LINK_TITLE() {
		return BOARD_LINK_TITLE;
	}
	public void setBOARD_LINK_TITLE(String bOARD_LINK_TITLE) {
		BOARD_LINK_TITLE = bOARD_LINK_TITLE;
	}
	public String getBOARD_LINK_IMAGE() {
		return BOARD_LINK_IMAGE;
	}
	public void setBOARD_LINK_IMAGE(String bOARD_LINK_IMAGE) {
		BOARD_LINK_IMAGE = bOARD_LINK_IMAGE;
	}
	public String getBOARD_LINK_NAME() {
		return BOARD_LINK_NAME;
	}
	public void setBOARD_LINK_NAME(String bOARD_LINK_NAME) {
		BOARD_LINK_NAME = bOARD_LINK_NAME;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String bOARD_PASS) {
		BOARD_PASS = bOARD_PASS;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public String getBOARD_CATEGORY() {
		return BOARD_CATEGORY;
	}
	public void setBOARD_CATEGORY(String bOARD_CATEGORY) {
		BOARD_CATEGORY = bOARD_CATEGORY;
	}
	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	public int getBOARD_LIKE() {
		return BOARD_LIKE;
	}
	public void setBOARD_LIKE(int bOARD_LIKE) {
		BOARD_LIKE = bOARD_LIKE;
	}
	public int getBOARD_REPORT() {
		return BOARD_REPORT;
	}
	public void setBOARD_REPORT(int bOARD_REPORT) {
		BOARD_REPORT = bOARD_REPORT;
	}
	
	
}
