package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;

public class deleteServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		long bId = Long.parseLong(request.getParameter("bId"));
		BoardDAO.getInstance().deleteBoard(bId);
		
		
		
	}

}
