package student.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBM;
import vo.AttendmentVO;
import vo.LectureVO;
import vo.StudentVO;

public class AtdGradeDAO {
	//성적->학생정보 조회
	public StudentVO selectStuInfo(String sno){
		StudentVO student = new StudentVO();
		String sql = "select * from student where sno = ?";
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		while(dbm.next()) {
			student.setSid(dbm.getString("sid"));
			student.setSmajor(dbm.getString("smajor"));
			student.setSname(dbm.getString("sname"));
			student.setSgrade( dbm.getInt("sgrade"));
			student.setSstatus( dbm.getInt("sstatus"));
		}
		dbm.close();
		return student;
	}
	//성적조회 목록
	public List<Map<String, Object>>  selectgradeAll(String sno,String lyearType,String lsemesterType){
		List<Map<String, Object>> gradeList = new ArrayList<>();
		
		String sql = "select c.cno, c.cgrade, l.lname, l.lcredit from course c "
				+ "inner join lecture l on l.lno = c.lno "
				+ "where sno = ?";
		
		if(lyearType != null && !lyearType.equals("")) {
			if(lyearType.equals("2023")) {
				sql += " AND l.lyear like 2023 ";
			}else if(lyearType.equals("2024")) {
				sql += " AND l.lyear like 2024 ";
			}
		}
		
		if(lsemesterType != null && !lsemesterType.equals("")) {
			if(lsemesterType.equals("1")) {
				sql +=" and l.lsemester like 1 ";
			}else if(lsemesterType.equals("2")) {
				sql +=" and l.lsemester like 2 ";
			}
		}
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			Map<String, Object> gradeMap = new HashMap<>();
			

			gradeMap.put("cno", dbm.getInt("cno"));
			gradeMap.put("lname", dbm.getString("lname"));
			gradeMap.put("lcredit", dbm.getInt("lcredit"));
			gradeMap.put("cgrade", dbm.getString("cgrade"));

			gradeList.add(gradeMap);
		}
		
		dbm.close();
		return gradeList;
	}
	//----------------------------------------------------------------
	//course목록 조회 -> 출석list
	public List<LectureVO>  selectCourseAll(String sno){
		List<LectureVO> courseList = new ArrayList<>();
		
		String sql = "SELECT c.cno, l.lname, p.pname, l.ltime, l.lroom, l.lno "
				+"FROM course c "
				+" INNER JOIN lecture l ON c.lno = l.lno "
				+" INNER JOIN professor p ON l.pno = p.pno "
				+" INNER JOIN student s ON c.sno = s.sno "
				+" WHERE s.sno = ? "
				+" ORDER BY c.cno ";
				
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			LectureVO course = new LectureVO();
			course.setLno(dbm.getInt("lno"));
			course.setCno(dbm.getInt("cno"));
			course.setLtime(dbm.getInt("ltime"));
			course.setLname(dbm.getString("lname"));
			course.setLroom(dbm.getString("lroom"));
			course.setPname(dbm.getString("pname"));

			courseList.add(course);
		}
		dbm.close();

		return courseList;
	}
	//-------------------------------------------------------------------
	//출석view -> 강의 정보 조회
	public LectureVO  selectCourseInfo(int cno){
		LectureVO courseinfo = new LectureVO();
		
		String sql = " select l.*,c.cno from lecture l "
				+ " inner join course c on l.lno = c.lno  "
				+ "	where c.cno = ?";
				
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setInt(cno).select();

		while(dbm.next()) {
			
			courseinfo.setLname(dbm.getString("lname"));
			courseinfo.setLyear(dbm.getString("lyear").substring(0,4));
			courseinfo.setLsemester(dbm.getInt("lsemester"));
			courseinfo.setLtime(dbm.getInt("ltime"));
			courseinfo.setLcredit(dbm.getInt("lcredit"));
			courseinfo.setLmaxpeople(dbm.getString("lmaxpeople"));
			courseinfo.setLroom(dbm.getString("lroom"));
			
		}
		
		dbm.close();
		return courseinfo;
	}
	//출석view -> 출석 정보 조회
	 public List<AttendmentVO> selectAttendAll(int cno, String sno){
		 List<AttendmentVO> attendList = new ArrayList<>();
		 String sql = "select a.* from course c inner join attendment a inner join student s "
				 +" on c.cno = a.cno && c.sno = s.sno "
				 +" where c.cno = ? and s.sno = ?";
		
		 DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setInt(cno).setString(sno).select();

		while(dbm.next()) {
			AttendmentVO attend = new AttendmentVO();
			attend.setAttendyn(dbm.getInt("attendyn"));
			attend.setAttendrdate(dbm.getString("attendrdate"));
			attendList.add(attend);
		}
		dbm.close();
		
		return attendList;
	 }
	
}
