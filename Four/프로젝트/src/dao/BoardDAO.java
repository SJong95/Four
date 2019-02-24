package dao;
import java.sql.*;
import java.util.ArrayList;
import vo.*;
import static db.JdbcUtil.*;

public class BoardDAO {
	private static BoardDAO boardDAO;
	private Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if(boardDAO==null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public ArrayList<Board> boardList() { // 영상 목록 가져오기
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		String sql = "select * from board order by board_num desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				board = new Board();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_LINK(rs.getString("BOARD_LINK"));
				board.setBOARD_LINK_VIDEO(rs.getString("BOARD_LINK_VIDEO"));
				board.setBOARD_LINK_TITLE(rs.getString("BOARD_LINK_TITLE"));
				board.setBOARD_LINK_IMAGE(rs.getString("BOARD_LINK_IMAGE"));
				board.setBOARD_LINK_NAME(rs.getString("BOARD_LINK_NAME"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_CATEGORY(rs.getString("BOARD_CATEGORY"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				board.setBOARD_LIKE(rs.getInt("BOARD_LIKE"));
				board.setBOARD_REPORT(rs.getInt("BOARD_REPORT"));
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	public int commentCount(int board_num) { // 영상 목록 가져올때 댓글 갯수 가져오기
		String sql = "select count(*) from reboard where board_num=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);	
			}else {
				result=0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int boardCount() { // 영상 등록 시 글번호 정하기
		String sql = "select count(*) from board";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);	
			}else {
				result=0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result+1;
	}
	public int boardWrite(Board board) { // 영상 등록
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
		int result=0;
		int num = boardCount();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setString(2, board.getBOARD_LINK());
			pstmt.setString(3, board.getBOARD_LINK_VIDEO());
			pstmt.setString(4, board.getBOARD_LINK_TITLE());
			pstmt.setString(5, board.getBOARD_LINK_IMAGE());
			pstmt.setString(6, board.getBOARD_LINK_NAME());
			pstmt.setString(7, board.getBOARD_NAME());
			pstmt.setString(8, board.getBOARD_PASS());
			pstmt.setString(9, board.getBOARD_SUBJECT());
			pstmt.setString(10, board.getBOARD_CONTENT());
			pstmt.setString(11, board.getBOARD_CATEGORY());
			pstmt.setInt(12, board.getBOARD_LIKE());
			pstmt.setInt(13, board.getBOARD_REPORT());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	public boolean isVideoBoardWriter(int board_num, String parameter) { // 영상 삭제 시 패스워드 확인
		String board_sql = "select * from board where BOARD_NUM=?";
		boolean isWriter = false;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			rs.next();

			if (parameter.equals(rs.getString("BOARD_PASS"))) {
				isWriter = true;
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return isWriter;
	}
	public void updateNum(int board_num) { // 영상 삭제 시 글 번호 댕기기
		String sql = "update board set board_num=board_num-1 where board_num>?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			
		}finally {
			close(pstmt);
		}
	}
	public int deleteVideo(int board_num) { // 영상 삭제
		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from board where BOARD_NUM=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		updateNum(board_num);
		return deleteCount;
	}
	public Board selectArticle(int board_num) { // 영상 상세정보
		
		Board board = null;

		try {
			pstmt = con.prepareStatement("select * from board where BOARD_NUM = ?");
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_LINK(rs.getString("BOARD_LINK"));
				board.setBOARD_LINK_VIDEO(rs.getString("BOARD_LINK_VIDEO"));
				board.setBOARD_LINK_TITLE(rs.getString("BOARD_LINK_TITLE"));
				board.setBOARD_LINK_IMAGE(rs.getString("BOARD_LINK_IMAGE"));
				board.setBOARD_LINK_NAME(rs.getString("BOARD_LINK_NAME"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_CATEGORY(rs.getString("BOARD_CATEGORY"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				board.setBOARD_LIKE(rs.getInt("BOARD_LIKE"));
				board.setBOARD_REPORT(rs.getInt("BOARD_REPORT"));
			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return board;
	}
	public ArrayList<Board> selectCategory(String category) { // 카테고리별 영상 목록 가져오기
		ArrayList<Board> boardList = new ArrayList<Board>();
		Board board = null;
		try {
			pstmt = con.prepareStatement("select * from board where BOARD_CATEGORY = ? order by board_num desc");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_LINK(rs.getString("BOARD_LINK"));
				board.setBOARD_LINK_VIDEO(rs.getString("BOARD_LINK_VIDEO"));
				board.setBOARD_LINK_TITLE(rs.getString("BOARD_LINK_TITLE"));
				board.setBOARD_LINK_IMAGE(rs.getString("BOARD_LINK_IMAGE"));
				board.setBOARD_LINK_NAME(rs.getString("BOARD_LINK_NAME"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_CATEGORY(rs.getString("BOARD_CATEGORY"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				board.setBOARD_LIKE(rs.getInt("BOARD_LIKE"));
				board.setBOARD_REPORT(rs.getInt("BOARD_REPORT"));
				boardList.add(board);
			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	public ArrayList<ReBoard> reboardList(int board_num) { // 댓글 목록 가져오기
		String sql = "select * from reboard where board_num=? order by reboard_re_ref asc, reboard_re_seq asc";
		ArrayList<ReBoard> commentList = new ArrayList<ReBoard>();
		ReBoard reBoard = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				reBoard = new ReBoard();
				reBoard.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				reBoard.setREBOARD_NUM(rs.getInt("REBOARD_NUM"));
				reBoard.setREBOARD_NAME(rs.getString("REBOARD_NAME"));
				reBoard.setREBOARD_PASS(rs.getString("REBOARD_PASS"));
				reBoard.setREBOARD_CONTENT(rs.getString("REBOARD_CONTENT"));
				reBoard.setREBOARD_RE_REF(rs.getInt("REBOARD_RE_REF"));
				reBoard.setREBOARD_RE_SEQ(rs.getInt("REBOARD_RE_SEQ"));
				reBoard.setREBOARD_DATE(rs.getDate("REBOARD_DATE"));
				commentList.add(reBoard);
			}
		}catch(Exception e) {
			System.out.println("오류" + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return commentList;
	}
	public int commentWrite(ReBoard reboard) { // 댓글 작성
		int writeCount=0;
		int num=0;
		String sql="insert into reboard values(?,?,?,?,?,?,?,sysdate)";
		String sql2="select max(reboard_num) from reboard";
		try {
			pstmt=con.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}else {
				num=1;
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboard.getBOARD_NUM());
			pstmt.setInt(2, num);
			pstmt.setString(3,reboard.getREBOARD_NAME());
			pstmt.setString(4,reboard.getREBOARD_PASS());
			pstmt.setString(5,reboard.getREBOARD_CONTENT());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			System.out.println(reboard.getREBOARD_NAME());
			System.out.println(reboard.getREBOARD_PASS());
			System.out.println(reboard.getREBOARD_CONTENT());
			writeCount=pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return writeCount;
	}
	public boolean isReboardPass(int reboardNum, String reboardPass) { // 댓글 삭제 시 비밀번호 확인
		String sql ="select * from reboard where reboard_num=? and reboard_pass=?";
		boolean isReboardPass=false;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboardNum);
			pstmt.setString(2, reboardPass);
			rs=pstmt.executeQuery();
			rs.next();
			if(reboardPass.equals(rs.getString("REBOARD_PASS"))) {
				isReboardPass=true;
			}
		}catch(Exception e) {
			System.out.println("비밀번호 확인 오류" + e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return isReboardPass;
	}
	public int reboardDelete(int reboardNum) { // 댓글 삭제
		String sql="delete from reboard where reboard_num=?";
		int deleteResult=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboardNum);
			deleteResult=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("댓글삭제오류"+ e);
		}finally {
			close(pstmt);
		}
		return deleteResult;
	}
	public int reboardRecomment(ReBoard reboard) { // 답글 등록
		String sql_reboardNum="select max(reboard_num) from reboard";
		String sql_reSeq = "select max(reboard_re_seq) from reboard where reboard_re_ref=?";
		String sql_insert = "insert into reboard values(?,?,?,?,?,?,?,sysdate)";
		
		int num=0;
		int recommentCount=0;
		int re_seq=0;
		
		try {
			pstmt=con.prepareStatement(sql_reSeq);
			pstmt.setInt(1, reboard.getREBOARD_RE_REF());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				re_seq = rs.getInt(1)+1;
			}
			pstmt=con.prepareStatement(sql_reboardNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}
			pstmt=con.prepareStatement(sql_insert);
			pstmt.setInt(1, reboard.getBOARD_NUM());
			pstmt.setInt(2, num);
			pstmt.setString(3, reboard.getREBOARD_NAME());
			pstmt.setString(4, reboard.getREBOARD_PASS());
			pstmt.setString(5, reboard.getREBOARD_CONTENT());
			pstmt.setInt(6,reboard.getREBOARD_RE_REF());
			pstmt.setInt(7, re_seq);
			recommentCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return recommentCount;
	}
}
