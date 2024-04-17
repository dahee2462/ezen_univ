package professor.dao;


import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.AttendmentVO;
import vo.LectureVO;

public class AttendDAO {
	
	public int sidYnInsert(String attendno, String attendyn) {
		
		String sql ="update attendment set attendyn = ? where attendno = ? ";
		
		DBM dbm = DBM.getInstance();
		
		int result = dbm.prepare(sql).setString(attendyn).setString(attendno).update();
		
		dbm.close();
		
		return result;
	}
	
	
	public List<AttendmentVO> dayLnoFindAttend(String day, String lno){
		
		String sql = "select * from lecture l inner join course c inner join attendment a inner join student s on c.cno = a.cno && c.sno = s.sno && l.lno = c.lno where l.lno = ? && attendrdate = ?";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(lno).setString(day).select();
		
		List<AttendmentVO> attendList = new ArrayList<>();
		AttendmentVO attendmentVO = null;
		while(dbm.next()) {
			attendmentVO = new AttendmentVO();
			attendmentVO.setSname(dbm.getString("sname"));
			attendmentVO.setSid(dbm.getString("sid"));
			attendmentVO.setSphone(dbm.getString("sphone"));
			attendmentVO.setAttendyn(dbm.getInt("attendyn"));
			attendmentVO.setAttendno(dbm.getInt("attendno"));
			attendList.add(attendmentVO);
		}
		
		dbm.close();
		
		return attendList;
	}
	
	
	public List<LectureVO> pnoLstatusFindLecture(String pno, String lstatus) {
		
		String sql = "select * from lecture where pno = ? ";
		if(lstatus.equals("4") || lstatus.equals("5") || lstatus.equals("6")) {
			sql += " && (lstatus = ? || lstatus = ? )";
		}
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql);
		dbm.setString(pno);
		if(lstatus.equals("4") || lstatus.equals("5")) {
			dbm.setString(lstatus);
			dbm.setString(lstatus);
		}
		if(lstatus.equals("6")) {
			dbm.setString("4");
			dbm.setString("5");
		}
		dbm.select();
		
		List<LectureVO> lectureList = new ArrayList<>();
		LectureVO lectureVO = null;
		while(dbm.next()) {
			lectureVO = new LectureVO();
			lectureVO.setLno(dbm.getInt("lno"));
			lectureVO.setLname(dbm.getString("lname"));
			lectureVO.setLyear(dbm.getString("lyear").substring(0,4));
			lectureVO.setLsemester(dbm.getInt("lsemester"));
			lectureVO.setLroom(dbm.getString("lroom"));
			lectureList.add(lectureVO);
		}
		
		dbm.close();
		return lectureList;
	}
}
