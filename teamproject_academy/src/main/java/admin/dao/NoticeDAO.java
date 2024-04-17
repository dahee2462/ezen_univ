package admin.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.BoardVO;

public class NoticeDAO {
	
	public BoardVO bnoFindBoard(int bno) {
		String sql = "select * from board where bno = ? ";
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setInt(bno).select();
		
		BoardVO boardVO = null;
		while(dbm.next()) {
			boardVO = new BoardVO();
			boardVO.setBno(bno);
			boardVO.setBtitle(dbm.getString("btitle"));
			boardVO.setBcontent(dbm.getString("bcontent"));
			boardVO.setBrdate(dbm.getString("brdate"));
			boardVO.setBhit(dbm.getInt("bhit"));
		}
		
		dbm.close();
		
		return boardVO;
	}

	public List<BoardVO> selectAll() {
		
		List<BoardVO> noticeList = new ArrayList<>();
		
		String sql = "SELECT bno, btitle, brdate, bhit "
				   + "  FROM board b" ;
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).select();
		
		while(dbm.next()) {
			BoardVO board = new BoardVO();
			board.setBno(dbm.getInt("bno"));
			board.setBtitle(dbm.getString("btitle"));
			board.setBrdate(dbm.getString("brdate"));
			board.setBhit(dbm.getInt("bhit"));
			
			// noticeList에 꼭 추가
			noticeList.add(board);
			
		}
		
		dbm.close();
		
		return noticeList;
	}

	public int insertNotice(String titleParam, String contentParam, String ano) {
		
		String sql = "INSERT INTO board"
					+ " (btitle, bcontent, brdate, bhit, ano)"
					+ " VALUES(?, ?, now(), 0, ?)";
		
		BoardVO boardVO = new BoardVO();
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		dbm.setString(titleParam);
		dbm.setString(contentParam);
		dbm.setString(ano);
		
		int result = dbm.update();
		
		dbm.close();
		
		
		
		return result;
	}
	
	public int FindTotalCnt(String searchAlign, String searchType, String searchValue){
		String sql = "select count(*) as cnt from board b ";
		if(searchType.equals("title")) {
			sql += " where btitle like concat('%',?,'%') ";
		}else if(searchType.equals("content")) {
			sql += " where bcontent like concat('%',?,'%') ";
		}
		
		if(searchAlign.equals("late")) {
			sql += " order by bno desc ";
		}else if(searchAlign.equals("hit")) {
			sql += " order by bhit desc ";
		}
		
		
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		if(searchType.equals("title") || searchType.equals("content")) {
			dbm.setString(searchValue);
		}
		
		dbm.select();
		int totalCnt = 0;
		
		if(dbm.next()) {			
			totalCnt = dbm.getInt("cnt");
		}
		
		dbm.close();
		
		return totalCnt;
	}
	
	public List<BoardVO> FindBoard(String searchAlign, String searchType, String searchValue, int start, int perPage){
		String sql = "select b.* from board b ";
		if(searchType.equals("title")) {
			sql += " where btitle like concat('%',?,'%') ";
		}else if(searchType.equals("content")) {
			sql += " where bcontent like concat('%',?,'%') ";
		}
		
		if(searchAlign.equals("late")) {
			sql += " order by bno desc ";
		}else if(searchAlign.equals("hit")) {
			sql += " order by bhit desc ";
		}
		sql += " limit ?, ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		if(searchType.equals("title") || searchType.equals("content")) {
			dbm.setString(searchValue);
		}
		dbm.setInt(start-1);
		dbm.setInt(perPage);
		
		dbm.select();
		
		List<BoardVO> BoardList = new ArrayList<>();
		BoardVO boardVO = null;
		while(dbm.next()) {
			boardVO = new BoardVO();
			boardVO.setBno(dbm.getInt("bno"));
			boardVO.setBtitle(dbm.getString("btitle"));
			boardVO.setBrdate(dbm.getString("brdate"));
			boardVO.setBhit(dbm.getInt("bhit"));
			BoardList.add(boardVO);
			
		}
		dbm.close();
		
		return BoardList;
	}

	public BoardVO updateBoard(int bno, String titleParam, String contentParam) {
		String sql = "UPDATE board "
	               + "SET btitle = ?, bcontent = ? "
	               + "WHERE bno = ?";

	    BoardVO boardVO = new BoardVO();
	    boardVO.setBtitle(titleParam);
	    boardVO.setBcontent(contentParam);

	    DBM dbm = DBM.getInstance();
	    dbm.prepare(sql)
	        .setString(boardVO.getBtitle())
	        .setString(boardVO.getBcontent())
	        .setInt(bno);
	    
	    dbm.update();
	    dbm.close();
	    
	    return boardVO;
	}
}
