package student.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBM;
import vo.BoardVO;
import vo.StudentVO;

public class MainDAO {
	//수강과목
	public List<Map<String, Object>>  selectCourseAll(String sno){

		List<Map<String, Object>> courseList = new ArrayList<>();
		
		String sql = "SELECT c.cno, l.lname, p.pname, l.ltime, l.lroom, l.lno "
				+"FROM course c "
				+" INNER JOIN lecture l ON c.lno = l.lno "
				+" INNER JOIN professor p ON l.pno = p.pno "
				+" INNER JOIN student s ON c.sno = s.sno "
				+" WHERE s.sno = ? and c.cdelyn=0"
				+" ORDER BY c.cno " 
				+" LIMIT 5";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			Map<String, Object> courseMap = new HashMap<>();

			courseMap.put("cno", dbm.getInt("cno"));
			courseMap.put("lno", dbm.getInt("lno"));
			courseMap.put("lname", dbm.getString("lname"));
			courseMap.put("ltime", dbm.getString("ltime"));
			courseMap.put("lroom", dbm.getString("lroom"));
			courseMap.put("pname", dbm.getString("pname"));

			courseList.add(courseMap);
			
		}

		dbm.close();
		return courseList;
	}
	//공지사항
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
