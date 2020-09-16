package kr.co.jsp.board.model;
/*
 CREATE TABLE my_board(
	board_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(50),
    title VARCHAR(100),
    content VARCHAR(300),
    date TIMESTAMP DEFAULT NOW(),
    hit INT(4) DEFAULT 0
);
 */

import java.sql.Timestamp;

public class BoardVO {
	
	private long boardId;
	private String writer;
	private String title;
	private String content;
	private Timestamp date;
	private int hit;
	
	public BoardVO() {}

	public BoardVO(long boardId, String writer, String title, String content, Timestamp date, int hit) {
		super();
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
		this.hit = hit;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	

}



























