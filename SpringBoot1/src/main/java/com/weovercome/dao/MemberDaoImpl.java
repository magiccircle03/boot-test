package com.weovercome.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.weovercome.entity.MemberEntity;

// 1. 생성해서 T를 MemberEntity로 바꿨구
public class MemberDaoImpl implements MemberDao<MemberEntity>{

	//3.
	private EntityManager entityManager;

	
	//4. 생성자 생성
	public MemberDaoImpl() {}

	public MemberDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	// 2. MemberDaoImpl 빨간줄 눌러서(구현 할래? 앱스트릭 할래?) 구현선택
	@Override
	public List<MemberEntity> getAll() {
		// 5. 쿼리객체 만들고(persistence 임폴트) 크리에이트 쿼리
		// 7. from MemberEntity
		Query query = entityManager.createQuery("from MemberEntity");
		// 6.
		List<MemberEntity> list = query.getResultList();
		// 8. 엔티티 사용했으면 엔티티매니저 닫아줘야
		entityManager.close();
		
		// 9. 예제라 서비스 생략하지만 실제로할땐 해주는게 좋겠죠? 지금은 컨트롤러로 감
		
		return list;
	}

	// 15. 
	@Override
	public MemberEntity findByIdx(long idx) {
		Query query = entityManager.createQuery("from MemberEntity where idx="+idx);
		MemberEntity entity = (MemberEntity)query.getSingleResult();
		return entity;
	}

	@Override
	public List<MemberEntity> findByUname(String name) {
		Query query = entityManager.createQuery("from MemberEntity where uname='"+name+"'");
		List<MemberEntity> list = (List<MemberEntity>) query.getResultList();
		return list;
	}
	
	// 18.
	@Override
	public List<MemberEntity> find(String fstr) {
		List<MemberEntity> list = null;
		
		// 예전에 프리페어드 물음표? 써서 했듯이 할 수 있다는것 셋파라미터라는 메서드로 추가해주고
		String qSql = "from MemberEntity where idx=:fidx "
				+ "or uname like :fname "
				+ "or uid like :fid";
		
		Long fIdx = 0L;
		
		// 어떤 타입인진 몰라도 숫자타입이면 이걸로 처리될 것임! 그리고 0인 사람은 없으니까 ㄱㅊ! 걱정되면 -1도 좋다
		try {
			fIdx = Long.parseLong(fstr); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 21. 주석하고
		// 보니까 쿼리를 반환하네! 체이닝 형식으로 되어있는 메소드구나. 예전에 스트링어쩌구 햇을때 이런거 봤죠 어펜드 어펜드 
//		Query query = entityManager.createQuery(qSql)
//				.setParameter("fidx", fIdx)
//				.setParameter("fname", "%"+fstr+"%")
//				.setParameter("fid", "%"+fstr+"%");
		// 21. 이렇게 사용할 수 있다
		Query query = entityManager.createNamedQuery("findWithParam")
				.setParameter("fidx", fIdx)
				.setParameter("fname", "%"+fstr+"%")
				.setParameter("fid", "%"+fstr+"%");
		
		list = (List<MemberEntity>) query.getResultList();
		
		return list;
	}
	
	

}
