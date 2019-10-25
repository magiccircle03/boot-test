package com.weovercome.domain;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
	2019.09.18 멤버매니저에서 복붙해옴
*/

// usebean Class
public class MemberInfo {

	// 각 변수의 저근 제어지시자는 private
	private int idx;
	private String uId;
	private String uPW;
	private String uName;
	private String uPhoto;
	private Date regDate;

	// 2019.08.21 추가한 변수
	private char verify;
	@JsonIgnore
	private String code;

	// default 생성자 필수
	public MemberInfo() {
		this.regDate = new Date();
		getRandomString();
	}

	// 사용자가 입력한거만 받아오는 거
	public MemberInfo(String uId, String uPW, String uName, String uPhoto) {
		super();
		this.uId = uId;
		this.uPW = uPW;
		this.uName = uName;
		this.uPhoto = uPhoto;
		this.regDate = new Date();
		getRandomString();
	}

	// 전체 받아오는거
	public MemberInfo(int idx, String uId, String uPW, String uName, String uPhoto, Date regDate) {
		super();
		this.idx = idx;
		this.uId = uId;
		this.uPW = uPW;
		this.uName = uName;
		this.uPhoto = uPhoto;
		this.regDate = regDate;
		getRandomString();
	}

	// 변수들의 Getter/Setter 시작

	public String getuId() {
		return uId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPW() {
		return uPW;
	}

	public void setuPW(String uPW) {
		this.uPW = uPW;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPhoto() {
		return uPhoto;
	}

	public void setuPhoto(String uPhoto) {
		this.uPhoto = uPhoto;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	// 2019.08.21 추가
	public char getVerify() {
		return verify;
	}

	// 2019.08.21 추가
	public void setVerify(char verify) {
		this.verify = verify;
	}

	// 2019.08.21 추가
	public String getCode() {
		return code;
	}

	// 2019.08.21 추가
	public void setCode(String code) {
		this.code = code;
	}

	// 데이터 확인을 위한 toString 오버라이딩
	@Override
	public String toString() {
		return "MemberInfo [idx=" + idx + ", uId=" + uId + ", uPW=" + uPW + ", uName=" + uName + ", uPhoto=" + uPhoto
				+ ", regDate=" + regDate + "]";
	}

	// 화면 결과 출력을 위한 HTML 코드 반환
	public String makeHtmlDiv() {
		String str = "";

		str += "<div class=\"mInfor\"> \n";
		str += "	<h3> \n";
		str += "		" + uId + "(" + uName + ")\n";
		str += "	</h3> \n";
		str += "	<p> \n";
		str += "		" + uPW;
		str += "	</p> \n";
		str += "</div> \n";

		return str;
	}

	// MemberInfo 객체 -> LoginInfo 객체 반환
//	public LoginInfo toLoginInfo() {
//
//		return new LoginInfo(uId, uName, uPhoto, regDate);
//
//	}

	// 비밀번호 체크 확인
	// 2019.07.25 메서드 추가
	// 실제론 uPW.equals(pw)만 해도 되는데 발생할 수 있는 여러가지 예외를 생각해서 이렇게 처리해줌
	public boolean pwChk(String pw) {
		return uPW != null && uPW.trim().length() > 0 && uPW.equals(pw);
	}

	// 2019.08.21 추가
	// 영문 + 숫자 난수 발생
	private void getRandomString() {

		Random r = new Random(System.nanoTime());
		StringBuffer sb = new StringBuffer(); // 스트링은 그때그때 객체생성. 얘는 메모리 아낌

		for (int i = 0; i < 20; i++) {
			if (r.nextBoolean()) {
				sb.append(r.nextInt(10));
			} else {
				sb.append((char) (r.nextInt(26) + 97));
			}
		}

		System.out.println("난수 코드 생성 : " + sb);

		setCode(sb.toString());

		// return sb.toString();
	}

}
