package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class ContentServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		/*
		 - contentBoard 완성시켜서 호출 후에
		  얻어온 객체를 request객체에 담아서 forwarding하시면 됩니다.
		  (/board/board_content.jsp)
		 */
		long bId = Long.parseLong(request.getParameter("boardId"));
		BoardVO vo = BoardDAO.getInstance().contentBoard(bId);
		request.setAttribute("content_board", vo);
	}

}
