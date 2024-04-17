package professor.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.CourseVO;
import vo.LectureVO;

public class GradeDAO {
	
	public String pnoFindPname(String pno) {
		String sql = "select * from professor where pno = ?";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(pno).select();
		
		String pname = "";
		
		while(dbm.next()) {
			pname = dbm.getString("pname");
		}
		
		dbm.close();
		
		return pname;
	}
	
	public int pnoCnoCgradeInsert(String pname, String cno, String cgrade) {
		String sql ="update course set cgradeupdater = ?, cgrade = ? where cno = ? ";
		
		DBM dbm = DBM.getInstance();
		
		int result = dbm.prepare(sql).setString(pname).setString(cgrade).setString(cno).update();
		
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
