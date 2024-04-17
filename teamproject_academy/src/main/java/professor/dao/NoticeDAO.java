package professor.dao;

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
			boardVO.setBtitle(dbm.getString("btitle"));
			boardVO.setBcontent(dbm.getString("bcontent"));
			boardVO.setBrdate(dbm.getString("brdate"));
			boardVO.setBhit(dbm.getInt("bhit"));
		}
		
		dbm.close();
		
		return boardVO;
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
	

}


