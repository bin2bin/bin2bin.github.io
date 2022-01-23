package mvc.model.vo.Member;

public class Member {
private String memberId;
private String memberPwd;
private String memeberName;
private String citizenNo; //주민번호
private double height;

public Member() {
	// TODO Auto-generated constructor stub
}

public Member(String memberId, String memberPwd, String memeberName, String citizenNo, double height) {
	super();
	this.memberId = memberId;
	this.memberPwd = memberPwd;
	this.memeberName = memeberName;
	this.citizenNo = citizenNo;
	this.height = height;
}

public String getMemberId() {
	return memberId;
}

public void setMemberId(String memberId) {
	this.memberId = memberId;
}

public String getMemberPwd() {
	return memberPwd;
}

public void setMemberPwd(String memberPwd) {
	this.memberPwd = memberPwd;
}

public String getMemeberName() {
	return memeberName;
}

public void setMemeberName(String memeberName) {
	this.memeberName = memeberName;
}

public String getCitizenNo() {
	return citizenNo;
}

public void setCitizenNo(String citizenNo) {
	this.citizenNo = citizenNo;
}

public double getHeight() {
	return height;
}

public void setHeight(double height) {
	this.height = height;
}

public String information() {
	return memberId + " " + memberPwd + " " + memeberName + " " + citizenNo + " " + height;
}

}
