package professor.dao;


import util.DBM;
import vo.ProfessorVO;

public class ProfInfoDAO {
	public ProfessorVO pnoFindProfessor(String pno) {
		
		String sql = "select * from professor p inner join profBridgeFile b inner join File f on p.pno = b.pno && f.fno = b.fno where p.pno = ? ";
		
		DBM dbm = DBM.getInstance();
		
		dbm.prepare(sql).setString(pno).select();
		
		ProfessorVO professorVO = null;
		while(dbm.next()) {
			professorVO = new ProfessorVO();
			professorVO.setPid(dbm.getString("pid"));
			professorVO.setPpw(dbm.getString("ppw"));
			professorVO.setPname(dbm.getString("pname"));
			professorVO.setPregNo1(dbm.getString("pregNo1"));
			professorVO.setPregNo2(dbm.getString("pregNo2"));
			professorVO.setPbirth(dbm.getString("pbirth"));
			professorVO.setPgender(dbm.getString("pgender"));
			professorVO.setPemail(dbm.getString("pemail"));
			professorVO.setPphone(dbm.getString("pphone"));
			professorVO.setPcall(dbm.getString("pcall"));
			professorVO.setPaddr(dbm.getString("paddr"));
			professorVO.setPzipCode(dbm.getString("pzipCode"));
			professorVO.setPrdate(dbm.getString("prdate"));
			professorVO.setPposition(dbm.getString("pposition"));
			professorVO.setPuniv(dbm.getString("puniv"));
			professorVO.setPfaculty(dbm.getString("pfaculty"));
			professorVO.setPmajor(dbm.getString("pmajor"));
			professorVO.setPdegree(dbm.getString("pdegree"));
			professorVO.setPlab(dbm.getString("plab"));
			professorVO.setPappointDate(dbm.getString("pappointDate"));
			professorVO.setPdelyn(dbm.getInt("pdelyn"));
			professorVO.setFrealnm(dbm.getString("frealnm"));
			professorVO.setForiginnm(dbm.getString("foriginnm"));
			
		}
		
		dbm.close();
		
		return professorVO;
	}
}
