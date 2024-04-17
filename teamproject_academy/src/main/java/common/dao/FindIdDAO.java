package common.dao;

import util.DBM;

public class FindIdDAO {
	
	public String searchStudentId(String name, String birth, String phone) {
		
		String sql = "select sid from student where sname = ? && sbirth = ? && sphone = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(name).setString(birth).setString(phone).select();
		
		String id = null;
		
		while(dbm.next()) {
			id = dbm.getString("sid");
		}
		
		dbm.close();
		
		return id;
	}
	
	public String searchProfessorId(String name, String birth, String phone) {
		
		String sql = "select pid from professor where pname = ? && pbirth = ? && pphone = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setString(name).setString(birth).setString(phone).select();
		
		String id = null;
		
		while(dbm.next()) {
			id = dbm.getString("pid");
		}
		
		dbm.close();
		
		return id;
	}
	
}
