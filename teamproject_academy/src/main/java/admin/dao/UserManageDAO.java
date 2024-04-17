package admin.dao;

import java.util.ArrayList;
import java.util.List;

import util.DBM;
import vo.ProfessorVO;

public class UserManageDAO {
	
//	교수 사용자 리스트 메서드
	public List<ProfessorVO> selectProf() {
		
		List<ProfessorVO> professorList = new ArrayList<>();
		
		String sql = "SELECT pno, pid, pname, pposition, plab, pphone, pemail "
				   + "  FROM professor p " ;
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).select();
		
		while(dbm.next()) {
			ProfessorVO profUser = new ProfessorVO();
			profUser.setPno(dbm.getInt("pno"));
			profUser.setPid(dbm.getString("pid"));
			profUser.setPname(dbm.getString("pname"));
			profUser.setPposition(dbm.getString("pposition"));
			profUser.setPlab(dbm.getString("plab"));
			profUser.setPphone(dbm.getString("pphone"));
			profUser.setPemail(dbm.getString("pemail"));
			
			professorList.add(profUser);
		}
		
		dbm.close();
		
		return professorList;
	}

//	교수 사용자 검색 메서드
	public List<ProfessorVO> searchProf(String searchValue) {
		
		List<ProfessorVO> searchResults = new ArrayList<>();
		
		String searchSql = "SELECT pno, pid, pname, pposition, plab, pphone, pemail "
						 + "  FROM professor p " ;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			searchSql += " WHERE pname LIKE CONCAT('%', ?, '%')";
		}
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(searchSql);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			dbm.setString(searchValue);
		}
		
		dbm.select();
		
		while(dbm.next()) {
			ProfessorVO searchProf = new ProfessorVO();
			searchProf.setPno(dbm.getInt("pno"));
			searchProf.setPid(dbm.getString("pid"));
			searchProf.setPname(dbm.getString("pname"));
			searchProf.setPposition(dbm.getString("pposition"));
			searchProf.setPlab(dbm.getString("plab"));
			searchProf.setPphone(dbm.getString("pphone"));
			searchProf.setPemail(dbm.getString("pemail"));
			
			searchResults.add(searchProf);
		}
		
		dbm.close();
		
		return searchResults;
	}
	
//	교수 사용자 리스트 페이징 메서드
	public List<ProfessorVO> selectProfPaging(int start, int perPage) {
		 List<ProfessorVO> professorList = new ArrayList<>();

		    String pagingSql = "SELECT * FROM professor p LIMIT ?, ?";

		    DBM dbm = DBM.getInstance();
		    dbm.prepare(pagingSql);

		    dbm.setInt(start);
		    dbm.setInt(perPage);

		    dbm.select();

		    while (dbm.next()) {
		        ProfessorVO professor = new ProfessorVO();
		        professor.setPno(dbm.getInt("pno"));
		        // 필요한 다른 필드들도 추가
		        professorList.add(professor);
		    }

		    dbm.close();

		    return professorList;
	}

//	교수 사용자 추가 DAO 메서드
	public List<ProfessorVO> insertProf(ProfessorVO professorVO) {
		List<ProfessorVO> profAdd = new ArrayList<>();
		
		String sql = " INSERT INTO professor "
				   + " (pid, ppw, pname, pregNo1, pregNo2, pbirth, pgender, pposition, "
				   + "	puniv, pfaculty, pmajor, pdegree, plab, pappointDate, pemail, "
				   + "  pphone, pcall, paddr, pzipCode, prdate)"
				   + "  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		dbm.setString(professorVO.getPid());
		dbm.setString(professorVO.getPpw());
		dbm.setString(professorVO.getPname());
		dbm.setString(professorVO.getPregNo1());
		dbm.setString(professorVO.getPregNo2());
		dbm.setString(professorVO.getPbirth()); 
		dbm.setString(professorVO.getPgender());
		dbm.setString(professorVO.getPposition());
		dbm.setString(professorVO.getPuniv());
		dbm.setString(professorVO.getPfaculty());
		dbm.setString(professorVO.getPmajor());
		dbm.setString(professorVO.getPdegree());
		dbm.setString(professorVO.getPlab());
		dbm.setString(professorVO.getPappointDate());
		dbm.setString(professorVO.getPemail());
		dbm.setString(professorVO.getPphone());
		dbm.setString(professorVO.getPcall());
		dbm.setString(professorVO.getPaddr());
		dbm.setString(professorVO.getPzipCode());
		
		dbm.update();
		
		dbm.close();
		
		return profAdd;
	}
	
	public ProfessorVO insertProfPhoto(ProfessorVO professorVO) {
		ProfessorVO profPhoto = null;
		String sql = " INSERT INTO file "
					+ " (frealnm, foriginnm, frdate)"
					+ " VALUES(?, ?, now())";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
		
		dbm.setString(professorVO.getFrealnm());
		dbm.setString(professorVO.getForiginnm());
		dbm.update();
		dbm.close();
		
		return profPhoto;
	}

	public ProfessorVO viewProfPhoto() {
		ProfessorVO professorVO = new ProfessorVO();
		
		String profSql = " SELECT max(pno) as pno FROM professor";
		DBM dbm = DBM.getInstance();
		dbm.prepare(profSql).select();
		int pno = 0;
		if(dbm.next()) {
			pno = dbm.getInt("pno");
		}
		dbm.close();
		
		String fileSql = " SELECT max(fno) as fno FROM file";
		dbm.prepare(fileSql).select();
		int fno = 0;
		if(dbm.next()) {
			fno = dbm.getInt("fno");
		}
		dbm.close();
		
		String totalSql = " INSERT INTO profbridgefile(pno, fno)"
						+ " VALUES(?, ?)";
		dbm.prepare(totalSql);
		dbm.setInt(pno);
		dbm.setInt(fno);
		dbm.update();
		dbm.close();
		
		return professorVO;
	}

	public ProfessorVO viewProf(int pno) {
		String sql = " SELECT professor.*, file.* FROM professor  "
				   + " 	INNER JOIN profbridgefile INNER JOIN file ON professor.pno = profbridgefile.pno  "
				   + " 	&& profbridgefile.fno = file.fno  "
				   + "  WHERE professor.pno = ?";
		
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql).setInt(pno).select();
		
		ProfessorVO professorVO = null;
		if(dbm.next()) {
			professorVO =  new ProfessorVO();
			professorVO.setPno(pno);
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

	public ProfessorVO updateprof(int pno, String ppositionParam, String plabParam, String pemailParam, String pphoneParam, String pcallParam, String paddrParam, String pzipCodeParam) {
		
		String sql = " UPDATE professor "
				+ "    SET pposition = ?, plab=?, pemail = ?, "
				+ "		   pphone = ?, pcall = ?, paddr = ?, pzipCode = ?"
				+ "  WHERE pno = ?";
		
		ProfessorVO professorVO = new ProfessorVO();
		
			
		DBM dbm = DBM.getInstance();
		dbm.prepare(sql);
	    dbm.setString(ppositionParam);
		dbm.setString(plabParam);
	    dbm.setString(pemailParam);
	    dbm.setString(pphoneParam);
	    dbm.setString(pcallParam);
	    dbm.setString(paddrParam);
	    dbm.setString(pzipCodeParam);
	    dbm.setInt(pno).update();
			
		dbm.close();
		
		return professorVO;
	}

}
