package common.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

import util.DBM;

public class FindPwDAO {
	public String searchStudentNo(String id, String name, String birth, String phone, String email) {
		
		String sql = "select sno from student where sid = ? && sname = ? && sbirth = ? && sphone = ? && semail = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(id).setString(name).setString(birth).setString(phone).setString(email).select();
		
		String sno = null;
		
		while(dbm.next()) {
			sno = dbm.getString("sno");
		}
		
		dbm.close();
		
		return sno;
	}
	
	public String searchProfessorNo(String id, String name, String birth, String phone, String email) {
		
		String sql = "select pno from professor where pid = ? && pname = ? && pbirth = ? && pphone = ? && pemail = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(id).setString(name).setString(birth).setString(phone).setString(email).select();
		
		String pno = null;
		
		while(dbm.next()) {
			pno = dbm.getString("pno");
		}
		
		dbm.close();
		
		return pno;
	}
	
	public String insertNewPw(String type, String memberNo) {
		String randomPw = RandomStringUtils.random(10, true, true);
		String inputRandomPw = BCrypt.hashpw(randomPw, BCrypt.gensalt());
		String sql = "";
		if(type.equals("학생")) {
			sql ="update student set spw = ? where sno = ? ";
		}else if(type.equals("교수")) {
			sql ="update professor set ppw = ? where pno = ? ";
		}
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(inputRandomPw).setString(memberNo).update();
		
		dbm.close();
		
		return randomPw;
	}
}
