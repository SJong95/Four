package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.*;
import vo.ActionForward;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.tv")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String RequestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println(command); // 넘어오는 값 확인
    	Date date = new Date();
    	System.out.println(date.toString()); // 접근 시간 확인
    	ActionForward forward = null;
    	Action action = null;
    	if(command.equals("/four.tv")) { // 메인 밑 영상 리스트 보여줄때 이동하는 곳 (get방식을 사용하지 않고 모두 같은 주소 값을 사용)
    		HttpSession session = request.getSession(); // 상세 정보에서 사용한 세션 값을 삭제하기 위해 선언
    		if((request.getParameter("search")!=null && request.getParameter("category")!=null) && request.getParameter("align")!=null) {
    			// 검색, 카테고리, 정렬의 값이 null이 아닌 경우
    			if(!request.getParameter("align").equals("null") && !request.getParameter("search").equals("null") && request.getParameter("category").equals("null")) {
    				// category만 문자열 null이고 나머지는 텍스트도 null이 아닌 경우
    				System.out.println("검색 후 정렬 컨트롤러");
    				action = new FourSearchAlignAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}
    			}else if(!request.getParameter("category").equals("null") && request.getParameter("align").equals("null") && request.getParameter("search").equals("null")) {
    				// 카테고리만 문자열 null이 아닌 경우
    				System.out.println("카테고리 컨트롤");
    				action = new FourCategoryAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}
    			}else if(!request.getParameter("align").equals("null") && request.getParameter("category").equals("null") && request.getParameter("search").equals("null")) {
    				// 정렬만 문자열 null이 아닌 경우
    				System.out.println("align 컨트롤러");
    				action = new FourAlignAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}	
    			}else if(!request.getParameter("search").equals("null") && request.getParameter("category").equals("null") && request.getParameter("align").equals("null")) {
    				// 검색만 문자열이 null이 아닌 경우
    				System.out.println("검색 컨트롤러");
    				action = new FourSearchAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}	
    			}else if(!request.getParameter("category").equals("null") && !request.getParameter("align").equals("null") && request.getParameter("search").equals("null")) {
    				// 카테고리 선택 후 정렬 할때 (카테고리, 정렬만 문자열 null 아닌 경우)
    				System.out.println("카테고리 + 정렬 컨트롤");
					action = new FourCateAlignAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}
    			}else {
    				// 검색, 카테고리, 정렬의 값이 null이 아니지만 모두 문자열 null인 경우
    				System.out.println("카테고리 안에 리스트");
            		action = new FourListAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}	
        		}
    		}else if(request.getParameter("search")!=null) {
    			// 검색 만 했을 때
    			if(!request.getParameter("search").equals("null")) {
    				System.out.println("검색 컨트롤러");
    				action = new FourSearchAction();
            		try {
            			forward = action.execute(request,response);
            		}catch(Exception e) {
            			e.printStackTrace();
            		}	
    			}
    		}else {
    			// 메인 처음을 보여줄때
    			System.out.println("그냥 리스트");
        		action = new FourListAction();
        		try {
        			forward = action.execute(request,response);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}	
    		}
    		session.setAttribute("LocalIp", request.getRemoteAddr());
    		session.removeAttribute("BOARD_NUM");
    	}else if(command.equals("/fourWrite.tv")) { // 영상 등록 폼으로 이동
    		forward = new ActionForward();
    		forward.setPath("/tv/write.jsp");
    	}else if(command.equals("/fourWriteAction.tv")) { // 영상 등록 처리 부분
    		action = new FourWriteAction();
    		try {
    			forward = action.execute(request,response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/fourDeleteAction.tv")) { // 영상 삭제 처리 부분
			action = new FourDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/fourDetailAction.tv")) { // 영상 상세 정보 처리 부분 
			System.out.println("디테일 넘기기 성공");
			System.out.println(request.getParameter("BOARD_NUM"));
			action = new FourDetailAction();
    		try {
    			forward = action.execute(request,response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		}else if(command.equals("/fourComment.tv")) { // 댓글 등록 처리 부분
    		action = new FourCommentAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourCommentDelete.tv")) { // 댓글 삭제 처리 부분
    		action = new FourCommentDeleteAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourReComment.tv")) { // 댓글의 답글 등록 처리 부분
    		System.out.println("리코멘트 컨트롤러");
    		action = new FourReCommentAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourGoodAction.tv")) { // 좋아요 처리 부분
    		System.out.println(request.getRemoteAddr());
    		System.out.println("좋아요 컨트롤러");
    		action = new FourGoodAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourBadAction.tv")) { // 싫어요 처리 부분
    		System.out.println("싫어요 컨트롤러");
    		action = new FourBadAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourReportAction.tv")) { // 신고 처리 부분
    		System.out.println("신고 컨트롤러");
    		action = new FourReportAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
