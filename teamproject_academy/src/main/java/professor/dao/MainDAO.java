package professor.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.BoardVO;
import vo.LectureVO;

public class MainDAO {
	public List<LectureVO> pnoFindLecture(String pno) {
		
		String sql = "select * from lecture where pno = ? ";
		
		List<LectureVO> LectureList = new ArrayList<>();
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(pno).select();
		
		LectureVO lectureVO = null;
		while(dbm.next()) {
			lectureVO = new LectureVO();
			lectureVO.setLno(dbm.getInt("lno"));
			lectureVO.setLname(dbm.getString("lname"));
			lectureVO.setLyear(dbm.getString("lyear").substring(0,4));
			lectureVO.setLsemester(dbm.getInt("lsemester"));
			lectureVO.setLroom(dbm.getString("lroom"));
			LectureList.add(lectureVO);
		}
		
		dbm.close();
		
		
		
		return LectureList;
	}
	
	public List<BoardVO> FindBoard(){
		
		String sql = "select * from board";
		
		List<BoardVO> BoardList = new ArrayList<>();
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).select();
		
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


