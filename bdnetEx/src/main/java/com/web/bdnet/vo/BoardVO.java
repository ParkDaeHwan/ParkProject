package com.web.bdnet.vo;

// 게시글 정보를 저장하는 클래스
public class BoardVO {
	private int brd_no = 0; // 게시글번호
	private String brd_title = null; // 제목
	private String brd_content = null; // 내용
	private String brd_writer = null; // 작성자
	private int brd_hit = 0; // 조회수
	private String brd_date = null; // 날짜
	
	private int fi_no = 0;
	private String fi_name1 = null;
	private String fi_name2 = null;
	private String fi_name3 = null;
	
	public int getBrd_no() {
		return brd_no;
	}
	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_content() {
		return brd_content;
	}
	public void setBrd_content(String brd_content) {
		this.brd_content = brd_content;
	}
	public String getBrd_writer() {
		return brd_writer;
	}
	public void setBrd_writer(String brd_writer) {
		this.brd_writer = brd_writer;
	}
	public int getBrd_hit() {
		return brd_hit;
	}
	public void setBrd_hit(int brd_hit) {
		this.brd_hit = brd_hit;
	}
	public String getBrd_date() {
		return brd_date;
	}
	public void setBrd_date(String brd_date) {
		this.brd_date = brd_date;
	}
	public int getFi_no() {
		return fi_no;
	}
	public void setFi_no(int fi_no) {
		this.fi_no = fi_no;
	}
	public String getFi_name1() {
		return fi_name1;
	}
	public void setFi_name1(String fi_name1) {
		this.fi_name1 = fi_name1;
	}
	public String getFi_name2() {
		return fi_name2;
	}
	public void setFi_name2(String fi_name2) {
		this.fi_name2 = fi_name2;
	}
	public String getFi_name3() {
		return fi_name3;
	}
	public void setFi_name3(String fi_name3) {
		this.fi_name3 = fi_name3;
	}
}
