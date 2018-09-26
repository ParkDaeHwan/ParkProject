package com.web.bdnet.vo;

// 게시글의 파일정보를 저장하는 클래스
public class FileVO {
	private int fi_no = 0; // 파일번호
	private int brd_no = 0; // 게시글번호
	private String fi_name1 = null; // 이름
	private byte[] fi_content1 = null; // 내용
	private String fi_name2 = null; // 이름
	private byte[] fi_content2 = null; // 내용
	private String fi_name3 = null; // 이름
	private byte[] fi_content3 = null; // 내용
	private String fi_date = null; // 날짜

	public FileVO() {
		super();
	}

	public FileVO(int brd_no) {
		super();
		this.brd_no = brd_no;
	}

	public int getFi_no() {
		return fi_no;
	}

	public void setFi_no(int fi_no) {
		this.fi_no = fi_no;
	}

	public int getBrd_no() {
		return brd_no;
	}

	public void setBrd_no(int brd_no) {
		this.brd_no = brd_no;
	}

	public String getFi_name1() {
		return fi_name1;
	}

	public void setFi_name1(String fi_name1) {
		this.fi_name1 = fi_name1;
	}

	public byte[] getFi_content1() {
		return fi_content1;
	}

	public void setFi_content1(byte[] fi_content1) {
		this.fi_content1 = fi_content1;
	}

	public String getFi_name2() {
		return fi_name2;
	}

	public void setFi_name2(String fi_name2) {
		this.fi_name2 = fi_name2;
	}

	public byte[] getFi_content2() {
		return fi_content2;
	}

	public void setFi_content2(byte[] fi_content2) {
		this.fi_content2 = fi_content2;
	}

	public String getFi_name3() {
		return fi_name3;
	}

	public void setFi_name3(String fi_name3) {
		this.fi_name3 = fi_name3;
	}

	public byte[] getFi_content3() {
		return fi_content3;
	}

	public void setFi_content3(byte[] fi_content3) {
		this.fi_content3 = fi_content3;
	}

	public String getFi_date() {
		return fi_date;
	}

	public void setFi_date(String fi_date) {
		this.fi_date = fi_date;
	}
}
