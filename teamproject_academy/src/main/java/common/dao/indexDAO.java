package common.dao;

import util.DBM;

public class indexDAO {
	
	public String[] selectPw(String id) {
	String sql = "select * from member where id = ?";
	
	DBM dbm = DBM.getInstance();
	
	
	
	dbm.prepare(sql).setString(id).select();
	
	String pw=null, type =null, no=null, name=null;
	while(dbm.next()) {
		pw = dbm.getString("pw");
		type = dbm.getString("type");
		no = dbm.getString("no");
		name = dbm.getString("name");
	}
	String[] result = {pw,type,no,name};
	
	dbm.close();
	
	return result; 
	}
}
