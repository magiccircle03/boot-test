package com.weovercome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weovercome.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	
	public MemberEntity findByIdx(Long idx); // 이렇게 하면 인터페이스 형태로 ~지만 구현체 형태로 제공받는다고 했지요?
	
	public List<MemberEntity> findByUnameLike(String name);
	
	public List<MemberEntity> findByIdxBetween(Long arg1, Long arg2);
	
	// 22. 레포지토리에 이렇게 하는 방법도 있음
	// 모든 데이터 가져오고싶으면 이렇게하면 모든걸 바인딩해옴(select MemberEntity from MemberEntity)
	// 별칭 써서 이렇게 해도 됨
	@Query("select d from MemberEntity d order by d.idx desc")
	public List<MemberEntity> findall();
	
}
