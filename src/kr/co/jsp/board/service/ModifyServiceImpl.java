package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class ModifyServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		long bId = Long.parseLong(request.getParameter("bId"));
		BoardVO vo = BoardDAO.getInstance().contentBoard(bId);
		
		request.setAttribute("modify_board", vo);

		
		
	}

}
