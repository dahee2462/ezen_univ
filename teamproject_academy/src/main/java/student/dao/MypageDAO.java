package student.dao;

import org.mindrot.jbcrypt.BCrypt;

import util.DBM;

public class MypageDAO {
	public int pwUpdate(String sno, String oldpw, String newpw, String checknewpw) {
			
		if(!newpw.equals(checknewpw)) {
			return 0;
		}
			
		String sql ="select * from student where sno = ?";
			
		DBM dbm = DBM.getInstance();
			
		dbm.prepare(sql).setString(sno).select();
			
		String pwdata = "";
			
		while(dbm.next()) {
			pwdata = dbm.getString("spw");
		}
			
		dbm.close();
			
		Boolean isValidPassword = BCrypt.checkpw(oldpw, pwdata);
			
		if(!isValidPassword) {
			return 0;
		}
			
		sql = "update student set spw = ? where sno = ?";
			
		String hashNewpw = BCrypt.hashpw(newpw, BCrypt.gensalt());
			
		int result = dbm.prepare(sql).setString(hashNewpw).setString(sno).update();
			
		return result;
	}

}
