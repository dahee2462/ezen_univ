package vo;

public class CourseVO {
	
	private int cno;
	private int cyn;
	private String cgrade;
	private String cgradeupdater;
	private int cdelyn;
	
	private String sname;
	private String sid;
	private String sphone;
	
	private String attendavg;
	
	private int sno;
	private int lno;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public String getAttendavg() {
		return attendavg;
	}
	public void setAttendavg(String attendavg) {
		this.attendavg = attendavg;
	}
	public String getCgradeupdater() {
		return cgradeupdater;
	}
	public void setCgradeupdater(String cgradeupdater) {
		this.cgradeupdater = cgradeupdater;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public int getCdelyn() {
		return cdelyn;
	}
	public void setCdelyn(int cdelyn) {
		this.cdelyn = cdelyn;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCyn() {
		return cyn;
	}
	public void setCyn(int cyn) {
		this.cyn = cyn;
	}
	public String getCgrade() {
		return cgrade;
	}
	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}
	
}