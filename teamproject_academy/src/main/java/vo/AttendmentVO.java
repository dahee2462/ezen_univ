package vo;

public class AttendmentVO {
	
	private int attendno;
	private String attendrdate;
	private int attendyn;
	
	private String sname;
	private String sid;
	private String sphone;
	
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
	public int getAttendno() {
		return attendno;
	}
	public void setAttendno(int attendno) {
		this.attendno = attendno;
	}
	public String getAttendrdate() {
		return attendrdate;
	}
	public void setAttendrdate(String attendrdate) {
		this.attendrdate = attendrdate;
	}
	public int getAttendyn() {
		return attendyn;
	}
	public void setAttendyn(int attendyn) {
		this.attendyn = attendyn;
	}
}
