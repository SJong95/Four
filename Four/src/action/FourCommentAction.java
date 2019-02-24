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
		//view.jsp에서부터 request값을 불러와 변수에 넣는다
		int board_num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		String id=request.getParameter("comname");
		String pass=request.getParameter("compass");
		String content=request.getParameter("comcontent");
		
		//reboard객체를 선언해서 그 매개변수로 위에서 초기화한 변수값을 넣어준다
		ReBoard reboard = new ReBoard();
		reboard.setBOARD_NUM(board_num);
		reboard.setREBOARD_NAME(id);
		reboard.setREBOARD_PASS(pass);
		reboard.setREBOARD_CONTENT(content);
		
		//fourcommentService객체를 선언해 ReboardWrite라는 메소드에 reboard객체 초기화
		FourCommentService fourcommentService = new FourCommentService();
		boolean result = fourcommentService.ReboardWrite(reboard);
		ActionForward forward=null;
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//fourcommentService의 ReboardWrite메소드의 결과값이 false면 댓글달기실패
		if(result==false) {
			out.println("<script>");
			out.println("alert('댓글달기 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			//아니면 session에 request에있는 getSession메소드로 초기화 forward객체에 null되어있는거
			//new ActionForward()로 초기화해서
			//setRedirect매개값을 true로 setPate매개값을 fourDetailAction.tv로 넣어주고 
			//session에 board_num값을 BOARD_NUM이라는 이름으로 저장
			HttpSession session = request.getSession();
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("fourDetailAction.tv");
			session.setAttribute("BOARD_NUM", board_num);
		}
		return forward;
	}
	
}
