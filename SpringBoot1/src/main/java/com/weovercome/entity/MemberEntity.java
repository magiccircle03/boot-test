package com.weovercome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// 둘 중 javax.persistence 임포트
@Entity
// 20. 이렇게 할 수 있다. impl에 이름을 쓸 수 있음 (그런데 불편함이 있으니 레포지토리에 직접 처리해 사용하는 경우도 있다)
@NamedQuery(
		name = "findWithParam",
		query = "from MemberEntity where idx=:fidx or uname like :fname or uid like :fid"
		)

@Table(name="member")
public class MemberEntity {
	@Id
	@Column
	private long idx;
	
	@Column(length = 45, nullable = false) //네임속성 안 쓰면 그냥 이 이름으로 
	private String uid;
	
	@Column(length = 45, nullable = false)
	private String upw;
	
	@Column(length = 45, nullable = false)
	private String uname;
	
	@Column(length = 45, nullable = false)
	private String uphoto;

	// 생성자는 기본생성자를 사용해볼 것
	
	// 게터세터 하고
	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpw() {
		return upw;
	}

	public void setUpw(String upw) {
		this.upw = upw;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUphoto() {
		return uphoto;
	}

	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}

	@Override
	public String toString() {
		return "MemberEntity [idx=" + idx + ", uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", uphoto=" + uphoto
				+ "]";
	}
	
}
