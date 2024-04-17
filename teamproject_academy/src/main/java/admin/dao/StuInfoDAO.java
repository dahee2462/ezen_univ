package admin.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.AttendmentVO;
import vo.CourseVO;
import vo.LectureVO;

public class StuInfoDAO {
	public String anoFindPname(String ano) {
		String sql = "select * from administer where ano = ?";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(ano).select();
		
		String aid = "";
		
		while(dbm.next()) {
			aid = dbm.getString("aid");
		}
		
		dbm.close();
		
		return aid;
	}
	
	public int anoCnoCgradeInsert(String aid, String cno, String cgrade) {
		String sql ="update course set cgradeupdater = ?, cgrade = ? where cno = ? ";
		
		DBM dbm = DBM.getInstance();
		
		int result = dbm.prepare(sql).setString(aid).setString(cgrade).setString(cno).update();
		
		dbm.close();
		
		return result;
	}
	
	
	
	public List<CourseVO> lnoFindCourse(String lno){
		String sql = "select * from lecture l inner join course c inner join student s on c.sno = s.sno && l.lno = c.lno where l.lno = ?";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(lno).select();
		
		List<CourseVO> courseList = new ArrayList<>();
		CourseVO courseVO = null;
		while(dbm.next()) {
			courseVO = new CourseVO();
			courseVO.setSname(dbm.getString("sname"));
			courseVO.setSid(dbm.getString("sid"));
			courseVO.setSphone(dbm.getString("sphone"));
			courseVO.setCgrade(dbm.getString("cgrade"));
			courseVO.setCgradeupdater(dbm.getString("cgradeupdater"));
			courseVO.setCno(dbm.getInt("cno"));
			courseList.add(courseVO);
		}
		
		dbm.close();
		
		
		return courseList;
	}
	
	
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
	
	
	
	public List<LectureVO> lstatusFindLecture(String lstatus) {
		
		String sql = "select * from lecture";
		if(lstatus.equals("4") || lstatus.equals("5") || lstatus.equals("6")) {
			sql += " where lstatus = ? || lstatus = ? ";
		}
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql);
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
