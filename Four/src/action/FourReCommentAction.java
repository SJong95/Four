package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.ActionForward;
import vo.ReBoard;

public class FourReCommentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		//reboard객체선언
		ReBoard reboard = new ReBoard();
		//view.jsp에서부터 request값을 불러와 변수에 넣는다
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		int num=Integer.parseInt(request.getParameter("REBOARD_NUM"));
		String id=request.getParameter("comname");
		String pass=request.getParameter("compass");
		String content=request.getParameter("comcontent");
		
		//reboard 객체에 set메소드들에 위 변수들을 매개변수로 넣어줌
		reboard.setBOARD_NUM(board_num);
		reboard.setREBOARD_NAME(id);
		reboard.setREBOARD_PASS(pass);
		reboard.setREBOARD_CONTENT(content);
		reboard.setREBOARD_RE_REF(num);
		
		//fourRecommentService에 reboardRecomment메소드에 reboard를 매개변수로 넘겨준다
		//reboardRecomment의 리턴값을 recommentResult에 대입
		FourReCommentService fourRecommentService = new FourReCommentService();
		boolean recommentResult = fourRecommentService.reboardRecomment(reboard);
		
		
		if(recommentResult) {
			HttpSession session = request.getSession();
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("fourDetailAction.tv");
			session.setAttribute("BOARD_NUM", board_num);
			//recommentResult가 true면 위 문장들 실행
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 실패')");
			out.println("history.back");
			out.println("</script>");
			//recommentResult가 false면 위 문장들 실행
		}
		return forward; //위의 실행결과를 리턴
		
	}

}
