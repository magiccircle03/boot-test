package com.weovercome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.weovercome.domain.MemberInfo;

// 우리가 예전에 했던 dao 인터페이스 생각하면 됨

public interface MemberMapper {
	@Select("select * from member order by idx")
	public List<MemberInfo> selectAll();
	
	public MemberInfo selectMemberById(String uid);
	
}
