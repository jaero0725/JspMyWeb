package kr.co.jsp.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;
import kr.co.jsp.board.service.ContentServiceImpl;
import kr.co.jsp.board.service.GetListServiceImpl;
import kr.co.jsp.board.service.IBoardService;
import kr.co.jsp.board.service.ModifyServiceImpl;
import kr.co.jsp.board.service.RegistServiceImpl;
import kr.co.jsp.board.service.UpdateServiceImpl;
import kr.co.jsp.board.service.deleteServiceImpl;
import kr.co.jsp.board.service.searchServiceImpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dp;
	IBoardService sv;
	
	
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1, uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
		
		case "board/write":
			System.out.println("글 작성 페이지 이동 요청!");
			response.sendRedirect("/MyWeb/board/board_write.jsp");
			break;
			
		case "board/regist":
			System.out.println("글 등록 요청이 들어옴!!");
			
			sv = new RegistServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/board/list.board");
			break;
			
		case "board/list":
			System.out.println("글 목록 요청이 들어옴!");
			sv = new GetListServiceImpl();
			sv.execute(request, response);
			
			//request객체를 다음 화면까지 운반하기 위한 forward 이동을 지원하는 객체.
			//-> RequestDispatcher
			dp = request.getRequestDispatcher("/board/board_list.jsp");
			dp.forward(request, response);
			break;
			
		case "board/content":
			System.out.println("글 상세보기 요청이 들어옴!");
			sv = new ContentServiceImpl();
			sv.execute(request, response);
			
			dp = request.getRequestDispatcher("/board/board_content.jsp");
			dp.forward(request, response);
			break;
		
		case "board/modify":
			System.out.println("글 수정 페이지로 이동 요청");
			sv = new ModifyServiceImpl();
			sv.execute(request, response);
			
			dp = request.getRequestDispatcher("/board/board_modify.jsp");
			dp.forward(request, response);
			break;
			
		case "board/update":
			System.out.println("글 수정 요청이 들어옴!");
			sv = new UpdateServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/board/list.board");
			break;
			
		case "board/delete":
			System.out.println("글 삭제 요청이 들어옴!");
			sv = new deleteServiceImpl();
			sv.execute(request, response);
			
			response.sendRedirect("/MyWeb/board/list.board");
			break;
			
		case "board/search":
			System.out.println("글 검색 요청이 들어옴!");
			sv = new searchServiceImpl();
			sv.execute(request, response);
			
			dp = request.getRequestDispatcher("/board/board_list.jsp");
			dp.forward(request, response);
			break;
		} //end switch
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
