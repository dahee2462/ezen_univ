package professor.dao;

import org.mindrot.jbcrypt.BCrypt;

import util.DBM;

public class MypageDAO {
	public int pwUpdate(String pno, String oldpw, String newpw, String checknewpw) {
		
		if(!newpw.equals(checknewpw)) {
			return 0;
		}
		
		String sql ="select * from professor where pno = ?";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(pno).select();
		
		String pwdata = "";
		
		while(dbm.next()) {
			pwdata = dbm.getString("ppw");
		}
		
		dbm.close();
		
		Boolean isValidPassword = BCrypt.checkpw(oldpw, pwdata);
		
		if(!isValidPassword) {
			return 0;
		}
		
		
		
		sql = "update professor set ppw = ? where pno = ?";
		
		String hashNewpw = BCrypt.hashpw(newpw, BCrypt.gensalt());
		
		int result = dbm.prepare(sql).setString(hashNewpw).setString(pno).update();
		
		return result;
	}
}
