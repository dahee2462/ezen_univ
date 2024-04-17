package professor.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.LectureVO;

public class CourseDAO {
	public List<LectureVO> pnoFindLecture(String pno, String lyear, int lsemester) {
		
		String sql = "select * from lecture where pno = ? && lyear = ? && lsemester = ? ";
		
		List<LectureVO> LectureList = new ArrayList<>();
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(pno).setString(lyear).setInt(lsemester).select();
		
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
	
	
	public LectureVO lnoFindLecture(String lno){
		
		String sql = "select * from lecture l inner join professor p on l.pno = p.pno where lno = ? ";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(lno).select();
		
		LectureVO lectureVO = null;
		while(dbm.next()) {
			lectureVO = new LectureVO();
			lectureVO.setLno(dbm.getInt("lno"));
			lectureVO.setLname(dbm.getString("lname"));
			lectureVO.setLyear(dbm.getString("lyear").substring(0,4));
			lectureVO.setLsemester(dbm.getInt("lsemester"));
//			lectureVO.setLschedule(dbm.getInt("lschedule"));
			lectureVO.setLcredit(dbm.getInt("lcredit"));
			lectureVO.setLtime(dbm.getInt("ltime"));
			lectureVO.setLintro(dbm.getString("lintro"));
			lectureVO.setLfocus(dbm.getString("lfocus"));
			lectureVO.setLroom(dbm.getString("lroom"));
			lectureVO.setPname(dbm.getString("pname"));
			lectureVO.setPphone(dbm.getString("pphone"));
			lectureVO.setPemail(dbm.getString("pemail"));
		}
		
		dbm.close();
		
		
		
		return lectureVO;
	}
	
}
