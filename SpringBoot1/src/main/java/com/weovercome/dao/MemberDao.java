package com.weovercome.dao;

import java.io.Serializable;
import java.util.List;

// 20190920 시작
// 0. dao 만들고

// 시리얼라이저블 이거 자주는 못봤어두 io할때 잠깐 봤던거져? able로 끝나는 애들은 마킹의 효과가 있댔음. 실제 들어가보면 내용없음. 걍 직렬화대상이야 알려주는
// <T> 제너릭 엔티티가 여러개 들어갈수도 있져 지금은 회원만 하고잇지만 보드가 들어갈수도 있고 하면 좀 더 유연하게 하고싶은것 
public interface MemberDao<T> extends Serializable{
	// 0.
	public List<T> getAll();
	
	// 14.
	public T findByIdx(long idx);
	public List<T> findByUname(String name);
	
	// 17.
	public List<T> find(String fstr);
	
}
