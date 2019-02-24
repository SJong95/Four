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
	public int listCount() { // 전체 글 갯수
		String sql = "select count(*) from board"; // 페이징 처리 시 사용
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int categoryCount(String category) { // 카테고리 글 갯수
		String sql = "select count(*) from board where board_category=?"; // 페이징 처리 시 사용
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int searchCount(String search) { // 검색 리스트 카운트
		String sql = "select count(*) from board where board_subject like '%"+search+"%'"; // 페이징 처리 시 사용
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Board> boardList(int page, int limit) { // 영상 목록 가져오기
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		String sql ="select * from (select rownum r1, t1.* from (select * from board where board_report<10 order by board_num desc) t1) where r1 between ? and ?";
			// 신고횟수 10회미만, 영상번호 내림차순으로 영상 리스트 select
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
	public ArrayList<Board> getSearch(String search, int page, int limit) { // 영상 검색
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		String sql ="select * from (select rownum r1, t1.* from (select * from board where board_report<10 and board_subject like '%"+search+"%' order by board_num desc) t1) where r1 between ? and ?";
			// 신고횟수 10회 미만, 검색한 단어가 글 제목에 포함된경우만 select
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
		String sql = "select count(*) from reboard where board_num=?"; // reboard 테이블에서 해당 글 번호로 검색
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				// 결과 값이 있으면 result 변수에 저장
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
	private int boardCount() { // 영상 등록 시 글번호 정하기
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
	private void updateNum(int board_num) { // 영상 삭제 시 글 번호 댕기기
		String sql = "update board set board_num=board_num-1 where board_num>?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	}
	public int deleteVideo(int board_num) { // 영상 삭제
		PreparedStatement pstmt = null;
		replyDel(board_num); // 영상 삭제 시 댓글이 있으면 댓글 먼저 삭제
		String sql = "delete from board where BOARD_NUM=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			close(pstmt);
		}
		updateNum(board_num); // 영상 삭제 후 영상 번호 수정 메소드
		return deleteCount;
	}
	private void replyDel(int board_num) { // 영상 삭제 시 댓글 있으면 삭제
		String sql="delete from reboard where board_num=?";
		// reboard테이블의 삭제할려는 영상 번호의 값을 삭제
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
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
	public ArrayList<Board> selectCategory(String category, int page, int limit) { // 카테고리별 영상 목록 가져오기
		ArrayList<Board> boardList = new ArrayList<Board>();
		Board board = null;
		System.out.println(category);
		System.out.println(page);
		System.out.println(limit);
		String sql ="select * from (select rownum r1, t1.* from (select * from board where board_category=? and board_report<10 order by board_num desc) t1) where r1 between ? and ?";
			// 신고횟수 10회미만, 카테고리 적용하여 영상 리스트 select
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	public ArrayList<ReBoard> reboardList(int board_num) { // 댓글 목록 가져오기
		String sql = "select * from reboard where board_num=? order by reboard_re_ref asc, reboard_re_seq asc";
		// 영상 번호에 맞는 댓글 목록 가져오기 쿼리문 (댓글의 답글까지 가져오기 위해 reboard_re_ref,reboard_re_seq로 order by 적용)
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
			// 댓글 작성 쿼리문
		String sql2="select max(reboard_num) from reboard";
			// 댓글 작성시 댓글 번호 지정 쿼리문
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
			// 삭제할려는 댓글에 입력한 비밀번호가 등록된 비밀번호와 일치여부 확인
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
		String sql="delete from reboard where reboard_num=? or reboard_re_ref=?";
			// 댓글 삭제 쿼리문으로 해당 댓글 번호와 댓글삭제시 답글까지 모두 삭제하기 위함
		int deleteResult=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboardNum);
			pstmt.setInt(2, reboardNum);
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
		// 답글 등록시 reboard_num에 저장할 값을 지정하기 위한 쿼리문
		String sql_reSeq = "select max(reboard_re_seq) from reboard where reboard_re_ref=?";
		// 답글 등록시 reboard_re_seq에 저장할 값을 지정하기 위한 쿼리문
		String sql_insert = "insert into reboard values(?,?,?,?,?,?,?,sysdate)";
		// 답글 등록을 위한 쿼리문
		
		int num=0;
		int recommentCount=0;
		int re_seq=0;
		
		try {
			pstmt=con.prepareStatement(sql_reSeq);
			pstmt.setInt(1, reboard.getREBOARD_RE_REF());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				re_seq = rs.getInt(1)+1;
			}// 최댓값에 +1한 값을 re_seq변수에 저장
			pstmt=con.prepareStatement(sql_reboardNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}// 최댓값에 +1한 값을 num변수에 저장
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
	public int boardGood(String board_num) { // 좋아요
		System.out.println("dao 진입");
		System.out.println(board_num);
		String sql = "update board set board_like=board_like+1 where board_num=?";
		// board 테이블의 board_like 칼럼 수를 증가
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board_num);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int boardBad(String board_num) { // 싫어요
		String sql = "update board set board_like=board_like-1 where board_num=?";
		// board 테이블의 board_like 칼럼 수를 감소
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board_num);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int boardReport(String board_num) { // 신고
		System.out.println(board_num);
		String sql = "update board set board_report=board_report+1 where board_num=?";
			// board의 신고 칼럼인 board_report의 수를 증가
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board_num);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Board> replyAlign(int page, int limit){ // 덧글 순 영상 목록
		String sql = "select * from (select rownum ro,t2.* from (select * from (select b.*,nvl(re.reboard_count,0) reboard_count from board b left OUTER JOIN (select board_num,count(*) reboard_count from reboard group by board_num) re on b.board_num = re.board_num where board_report<10) t1 order by t1.reboard_count desc, t1.board_num desc) t2) where ro BETWEEN ? and ?";
			// board와 reboard를 join해 해당 영상의 댓글수를 select해 댓글 내림차순으로 리스트 select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
	public ArrayList<Board> cateReplyAlign(String category,int page, int limit){ // 카테고리 덧글 순
		String sql = "select * from (select rownum ro,t2.* from (select * from (select b.*,nvl(re.reboard_count,0) reboard_count from board b left OUTER JOIN (select board_num,count(*) reboard_count from reboard group by board_num) re on b.board_num = re.board_num where b.board_category=? and board_report<10) t1 order by t1.reboard_count desc, t1.board_num desc) t2) where ro BETWEEN ? and ?";
			// board와 reboard를 join해 카테고리 결과와 해당 영상의 댓글수를 select해 댓글 내림차순으로 리스트 select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
	public ArrayList<Board> searchReplyAlign(String search, int page, int limit){ // 검색 덧글 순
		String sql = "select * from (select rownum ro,t2.* from (select * from (select b.*,nvl(re.reboard_count,0) reboard_count from board b left OUTER JOIN (select board_num,count(*) reboard_count from reboard group by board_num) re on b.board_num = re.board_num where b.board_subject like '%"+search+"%' and board_report<10) t1 order by t1.reboard_count desc, t1.board_num desc) t2) where ro BETWEEN ? and ?";
			// board와 reboard를 join해 검색한 결과와 해당 영상의 댓글수를 select해 댓글 내림차순으로 리스트 select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
	public ArrayList<Board> likeAlign(int page, int limit){ // 좋아요 순
		String sql = "select * from (select rownum ro, t1.* from (select * from board where board_report<10 order by board_like desc,board_num desc) t1) where ro BETWEEN ? and ?";
		// 좋아요 내림차순, 신고 10회미만으로 select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				// 결과 값 board 객체에 저장 후 boardList에 추가
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
	public ArrayList<Board> cateLikeAlign(String category, int page, int limit) { // 카테고리 좋아요 순
		String sql = "select * from (select rownum ro, t1.* from (select * from board where board_category=? and board_report<10 order by board_like desc,board_num desc) t1) where ro BETWEEN ? and ?";
			// 좋아요 내림차순,신고 10회미만, 카테고리에 맞게 리스트 select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
	public ArrayList<Board> searchLikeAlign(String search, int page, int limit){ // 검색 좋아요 순
		String sql = "select * from (select rownum ro, t1.* from (select * from board where board_subject like '%"+search+"%' and board_report<10 order by board_like desc,board_num desc) t1) where ro BETWEEN ? and ?";
			// 검색 값과 좋아요 내림차순, 신고 10회미만으로  select
		ArrayList<Board> boardList = new ArrayList<>();
		Board board = null;
		int start = (page-1)*limit+1;
		int end = start-1+limit;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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
	
}
