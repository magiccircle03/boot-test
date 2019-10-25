package com.weovercome.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weovercome.dao.MemberDao;
import com.weovercome.dao.MemberDaoImpl;
import com.weovercome.domain.MemberInfo;
import com.weovercome.entity.MemberEntity;
import com.weovercome.mapper.MemberMapper;
import com.weovercome.repository.MemberRepository;

@Controller
public class IndexController {
	
	// 인터페이스는 객체를 만들 수 없는데, 그렇다는 건 얘들이 알아서 구현체를 만들어놓고 bean으로 등록해놓는다! 아래 애들도 마찬가지임
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
	private MemberMapper mapper;
	
	// 20190918 스프링 부트 첫 시작 ============
	
	// 22. 이게 원래 버전이고
//	@RequestMapping("/")
//	@ResponseBody
//	public String index() {
//		return "Spring Boot Start!!";
//	}
	
	// 22. 이렇게바꿨다
	@RequestMapping("/")
	@ResponseBody
	public List<MemberEntity> index() {
		List<MemberEntity> list = repository.findall();
		return list;
	}
	
	
	@RequestMapping("/hello")
	public void hello1() {
	}
	
	@RequestMapping("/memberInfo")
	public void selectByIdx() {
		mapper = template.getMapper(MemberMapper.class);
		MemberInfo info = mapper.selectMemberById("cottonbear310@gmail.com");
		System.out.println(info);
	}
	
	@RequestMapping("/members")
	@ResponseBody
	public List<MemberInfo> selectMembers() {
		mapper = template.getMapper(MemberMapper.class);
		List<MemberInfo> info = mapper.selectAll();
		for (MemberInfo memberInfo : info) {
			System.out.println(memberInfo);
		}
		return info;
	}
	// =====================
	
	// 20190919 + JPA ========
	
	// 조회
	@RequestMapping("/member/list")
	@ResponseBody
	public List<MemberEntity> getMemberList() {
		List<MemberEntity> list = null;
		
		list = repository.findAll();
		
		for (MemberEntity memberEntity : list) {
			System.out.println(memberEntity);
		}
		
		return list;
	}
	
	// 삽입
	@RequestMapping("/member/insert")
	@ResponseBody
	public String insertMember() {
		MemberEntity entity = new MemberEntity();
		// idx는 자동으로 
		entity.setUid("daae@da.com");
		entity.setUname("DAAE");
		entity.setUpw("da1234");
		
		return repository.saveAndFlush(entity).toString();

	}
	
	// 수정
	@RequestMapping("/member/edit/{idx}")
	@ResponseBody
	public String editMember(@PathVariable("idx") int idx) {
		MemberEntity entity = new MemberEntity();
		entity.setIdx(idx); 
		entity.setUid("daae22@daae.com");
		entity.setUname("DAAE22");
		entity.setUpw("dada7897");
		
		return repository.saveAndFlush(entity).toString();
	}
	
	// 삭제는 엔티티 말고 그 상위에? 있어서 좀 복잡
	@RequestMapping("/member/delete/{idx}")
	@ResponseBody
	public String deleteMember(@PathVariable("idx") int idx) {
		
		MemberEntity entity = new MemberEntity();
		entity.setIdx(idx);
		
		repository.delete(entity);
		
		return "삭제 성공!";
	}
	
	//==
	
	//
	@RequestMapping("/member/member/{idx}")
	@ResponseBody
	public MemberEntity getMemberInfo(@PathVariable("idx") long idx) {
		MemberEntity entity = null;
		entity = repository.findByIdx(idx);
		System.out.println(entity);
		return entity;
	}
	
	//
	// 스트링 타입으로 받아서 오버로딩 함
	@RequestMapping("/member/memberbyname/{name}")
	@ResponseBody
	public List<MemberEntity> getMemberInfo(@PathVariable("name") String name) {
		List<MemberEntity> entitys = null;
		entitys = repository.findByUnameLike("%"+name+"%");
		
		for (MemberEntity memberEntity : entitys) {
			System.out.println(memberEntity);
		}
	
		return entitys;
	}
	
	//비트윈
	@RequestMapping("/member/memberbybetween")
	@ResponseBody
	public List<MemberEntity> getMemberInfo() {
		List<MemberEntity> entitys = null;
		entitys = repository.findByIdxBetween(3L, 7L); //롱타입은 L하고 리터럴 들어간거 기억하져
		
		for (MemberEntity memberEntity : entitys) {
			System.out.println(memberEntity);
		}
	
		return entitys;
	}
	
	// =====================
	
	// 2019. 09. 20. + JPA 2 =========================
	// 어제와 달리 직접 쿼리를 해보는 것! 자동~만으로 충분하다면 그걸 쓰면 되고 직접 필요시
	// 시작은 멤버 dao부터..
	
	// 10. 
	@PersistenceContext
	EntityManager entityManager; // 원래 위에 해주면 좋겠지만 공부할때 보기 편하라고 여기에 선언했음
	
	private MemberDaoImpl dao;
	
	// 13. 에러가 나서 이걸 주석하고
	// 11. 오토와이어드 하면 좋지만, 요즘 트렌드는 생성자를 명시적으로 넣어주는 게 유지보수에 좋다는 의견들
	//public IndexController() {
		//this.dao = new MemberDaoImpl(entityManager);
	//}
	
	// 12.
	@RequestMapping("/member/listall")
	@ResponseBody
	public List<MemberEntity> memberListAll() {
		
		// 13. 여기에 써줌 ===
		this.dao = new MemberDaoImpl(entityManager);
		
		List<MemberEntity> list = dao.getAll();
		
		for (MemberEntity memberEntity : list) {
			System.out.println(memberEntity);
		}
		
		return list;
	}
	
	
	// 16. 
	@RequestMapping("/member/listbyidx/{idx}")
	@ResponseBody
	public MemberEntity memberByIdx(@PathVariable("idx") int idx) {
		this.dao = new MemberDaoImpl(entityManager);
		MemberEntity entity = dao.findByIdx(idx);
		System.out.println(entity);
		return entity;
	}
	
	@RequestMapping("/member/listbyname/{name}")
	@ResponseBody
	public List<MemberEntity> memberByName(@PathVariable("name") String name) {
		this.dao = new MemberDaoImpl(entityManager);
		List<MemberEntity> list = dao.findByUname(name);
		for (MemberEntity memberEntity : list) {
			System.out.println(memberEntity);
		}
		return list;
	}
	
	// 19.
	@RequestMapping("/member/listfind/{str}")
	@ResponseBody
	public List<MemberEntity> find(@PathVariable("str") String str) {
		this.dao = new MemberDaoImpl(entityManager);
		List<MemberEntity> list = dao.find(str);
		for (MemberEntity memberEntity : list) {
			System.out.println(memberEntity);
		}
		return list;
	}
}
