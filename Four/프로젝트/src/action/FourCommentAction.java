package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.ActionForward;
import vo.ReBoard;

public class FourCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		String id=request.getParameter("comname");
		String pass=request.getParameter("compass");
		String content=request.getParameter("comcontent");
		
		ReBoard reboard = new ReBoard();
		reboard.setBOARD_NUM(board_num);
		reboard.setREBOARD_NAME(id);
		reboard.setREBOARD_PASS(pass);
		reboard.setREBOARD_CONTENT(content);
		
		FourCommentService fourcommentService = new FourCommentService();
		boolean result = fourcommentService.ReboardWrite(reboard);
		ActionForward forward=null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result==false) {
			out.println("<script>");
			out.println("alert('댓글달기 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			HttpSession session = request.getSession();
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("fourDetailAction.tv");
			session.setAttribute("BOARD_NUM", board_num);
		}
		return forward;
	}
	
}
