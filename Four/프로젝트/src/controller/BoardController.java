package controller;

import java.io.IOException;

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
    	System.out.println(RequestURI);
    	String contextPath = request.getContextPath();
    	System.out.println(contextPath);
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println(command);
    	ActionForward forward = null;
    	Action action = null;
    	if(command.equals("/four.tv")) {
    		System.out.println(request.getParameter("category"));
    		HttpSession session = request.getSession();
    		if(request.getParameter("category")!=null) {
    			action = new FourCategoryAction();
        		try {
        			forward = action.execute(request,response);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}
    		}else {
        		action = new FourListAction();
        		try {
        			forward = action.execute(request,response);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}	
    		}
    		session.invalidate();
    	}else if(command.equals("/fourWrite.tv")) {
    		forward = new ActionForward();
    		forward.setPath("/tv/write.jsp");
    	}else if(command.equals("/fourWriteAction.tv")) {
    		action = new FourWriteAction();
    		try {
    			forward = action.execute(request,response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/fourDeleteAction.tv")) {
			action = new FourDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/fourDetailAction.tv")) {
			System.out.println("디테일 넘기기 성공");
			System.out.println(request.getParameter("BOARD_NUM"));
			action = new FourDetailAction();
    		try {
    			forward = action.execute(request,response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		}else if(command.equals("/fourComment.tv")) {
    		action = new FourCommentAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourCommentDelete.tv")) {
    		System.out.println(request.getParameter("BOARD_NUM"));
    		System.out.println(request.getParameter("REBOARD_NUM"));
    		System.out.println(request.getParameter("REBOARD_PASS"));
    		action = new FourCommentDeleteAction();
    		try {
    			forward=action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/fourReComment.tv")) {
    		System.out.println("리코멘트 컨트롤러");
    		action = new FourReCommentAction();
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
