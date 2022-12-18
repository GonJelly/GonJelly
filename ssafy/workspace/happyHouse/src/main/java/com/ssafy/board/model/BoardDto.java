package com.ssafy.board.model;

import java.util.List;

public abstract class BoardDto {
	
	protected String idx;		// 게시글 번호
	protected String subject;	// 게시글 제목
	protected String content;	// 게시글 내용
	protected String hit;		// 게시글 조회수
	protected String author;	// 게시글 작성자
	protected String date;		// 게시글 날짜
	protected List<FileInfo> fileList; // 게시글 첨부파일

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<FileInfo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileInfo> fileList) {
		this.fileList = fileList;
	}
}
