package student.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBM;
import vo.LectureVO;
import vo.StudentVO;


public class AcdCourseDAO {

	//강의계획서 조회
	public Map<String, Object> selectCurriByOne(int lno){
		Map<String, Object> curriMap = new HashMap<>();
		String sql = "SELECT l.* , p.pname, p.pphone, p.pemail from lecture l "
				+ "INNER JOIN professor p  ON p.pno = l.pno "
				+ "WHERE l.lno = ? ";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setInt(lno).select();

		if(dbm.next()) {
			curriMap.put("lname", dbm.getString("lname"));
			curriMap.put("lyear", dbm.getString("lyear"));
			curriMap.put("lsemester", dbm.getInt("lsemester"));
			curriMap.put("ltime", dbm.getInt("ltime"));
			curriMap.put("lroom", dbm.getString("lroom"));
			curriMap.put("lcredit", dbm.getInt("lcredit"));
			curriMap.put("lintro", dbm.getString("lintro"));
			curriMap.put("lfocus", dbm.getString("lfocus"));
			curriMap.put("lstatus", dbm.getInt("lstatus"));
			curriMap.put("pname", dbm.getString("pname"));
			curriMap.put("pphone", dbm.getString("pphone"));
			curriMap.put("pemail", dbm.getString("pemail"));
		}
		dbm.close();
		
		return curriMap;
	}
	//totalCnt
	public int FindTotalCnt(String lyearType, String lsemesterType,String searchValue){
		String sql = "select count(*) as cnt from course c "
				+ " inner join lecture l on c.lno=l.lno ";
		
		if((lyearType!= null && !lyearType.equals("")) 
				|| (lsemesterType != null && !lsemesterType.equals(""))) {
			if(lyearType != null && !lyearType.equals("")) {
				if(lyearType.equals("2023")) {
					sql += " where l.lyear like 2023 ";
				}else if(lyearType.equals("2024")) {
					sql += " where l.lyear like 2024 ";
				}
			}
			
			if(lsemesterType != null && !lsemesterType.equals("")) {
				if(lsemesterType.equals("1")) {
					sql +=" and l.lsemester like 1 ";
				}else if(lsemesterType.equals("2")) {
					sql +=" and l.lsemester like 2 ";
				}
			}
			sql +=" AND l.lname LIKE CONCAT('%',?,'%')";
		}
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		if((lyearType!= null && !lyearType.equals("")) 
				|| (lsemesterType != null && !lsemesterType.equals(""))) {
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
	//교과목 조회(검색포함)
	public List<LectureVO>  selectCourseAll
		(String sno,String lyearType,String lsemesterType,String searchValue, int start, int perPage){
		List<LectureVO> courseList = new ArrayList<>();
		
		String sql = "SELECT c.cno, l.lname, p.pname, l.ltime, l.lroom, l.lno "
				+"FROM course c "
				+" INNER JOIN lecture l ON c.lno = l.lno "
				+" INNER JOIN professor p ON l.pno = p.pno "
				+" INNER JOIN student s ON c.sno = s.sno "
				+" WHERE s.sno = ? and cdelyn = 0 and l.lstatus=4";
		

		if((lyearType!= null && !lyearType.equals("")) 
				|| (lsemesterType != null && !lsemesterType.equals(""))) {
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
			sql +=" AND l.lname LIKE CONCAT('%',?,'%')";
		}
		
		sql	+=" ORDER BY c.cno limit ?, ? ";
		
		DBM dbm = DBM.getInstance().prepare(sql).setString(sno);
		if((lyearType!= null && !lyearType.equals("")) 
				|| (lsemesterType != null && !lsemesterType.equals(""))) {
			dbm.setString(searchValue);
		}
		
		dbm.setInt(start-1);
		dbm.setInt(perPage);
		
		dbm.select();
		
		while(dbm.next()) {
			LectureVO course= new LectureVO();

			course.setCno(dbm.getInt("cno"));
			course.setLno(dbm.getInt("lno"));
			course.setLname(dbm.getString("lname"));
			course.setLtime( dbm.getInt("ltime"));
			course.setLroom(dbm.getString("lroom"));
			course.setPname(dbm.getString("pname"));

			courseList.add(course);
		}

		dbm.close();
		return courseList;
	}
	
	//학적사항조회
	public StudentVO selectsscheckByOne(String sno){
		StudentVO student = new StudentVO();
		String sql = "SELECT * from student s "
					+ "INNER JOIN studentBridgeFile sb "
					+ "INNER JOIN File f "
					+ "ON s.sno = sb.sno && f.fno = sb.fno "
					+ "WHERE s.sno = ? ";

		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			
			student.setSid(dbm.getString("sid"));
			student.setSname(dbm.getString("sname"));
			student.setSregNo1(dbm.getString("sregNo1"));
			student.setSregNo2(dbm.getString("sregNo2"));
			student.setSbirth(dbm.getString("sbirth"));
			student.setSgender(dbm.getString("sgender"));
			student.setSuniv(dbm.getString("suniv"));
			student.setSfaculty(dbm.getString("sfaculty"));
			student.setSmajor(dbm.getString("smajor"));
			student.setScomeDate(dbm.getString("scomeDate"));
			student.setSoutDate(dbm.getString("soutDate"));
			student.setScompletionDate(dbm.getString("scompletionDate"));
			student.setSgradDate(dbm.getString("sgradDate"));
			student.setSemail(dbm.getString("semail"));
			student.setSphone(dbm.getString("sphone"));
			student.setScall(dbm.getString("scall"));
			student.setSaddr(dbm.getString("saddr"));
			student.setSzipCode(dbm.getString("szipCode"));
			student.setForiginnm(dbm.getString("foriginnm"));
			student.setFrealnm(dbm.getString("frealnm"));

			student.setSrank(dbm.getInt("srank"));
			student.setSstatus(dbm.getInt("sstatus"));
		
		}
		dbm.close();
		return student;
	}
	//-------------------------------------------------------
	//휴복학신청 조회
	public List<Map<String, Object>>  selectAbsenseAll(String sno){

		List<Map<String, Object>> absenseList = new ArrayList<>();
		
		String sql = "select ab.*, s.sstatus from absense ab "
				+ "inner join student s on s.sno = ab.sno "
				+ "where s.sno = ?;";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			Map<String, Object> absenseMap = new HashMap<>();

			absenseMap.put("abseno", dbm.getInt("abseno"));
			absenseMap.put("abseinfo", dbm.getString("abseinfo"));
			absenseMap.put("abserdate", dbm.getString("abserdate"));
			absenseMap.put("absestatus", dbm.getString("absestatus"));
			absenseMap.put("absepdate", dbm.getString("absepdate"));
			
			absenseMap.put("sstatus", dbm.getInt("sstatus"));

			absenseList.add(absenseMap);
		}

		dbm.close();
		return absenseList;
	}
	
	//수강시간표 조회
	public List<Map<String, Object>> selectScheduleAll(String sno){
		List<Map<String, Object>> scheduleList = new ArrayList<>();
		
		String sql = "select l.lname, l.json_data, p.pname, l.lroom from lecture l "
				+" inner join professor p on l.pno = p.pno "
				+" inner join course c on c.lno = l.lno "
				+" inner join student s on c.sno = s.sno "
				+" where s.sno = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(sno).select();
		
		while(dbm.next()) {
			Map<String, Object> scheduleMap = new HashMap<>();
			
			scheduleMap.put("lname", dbm.getString("lname"));
			scheduleMap.put("json_data", dbm.getString("json_data"));
			scheduleMap.put("lroom", dbm.getString("lroom"));
			scheduleMap.put("pname", dbm.getString("pname"));
			
			scheduleList.add(scheduleMap);
		}
		dbm.close();
		return scheduleList;
		
	}

}
