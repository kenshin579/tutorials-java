package com.example.dao;

import com.example.dto.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "com.example.mapper.memberMapper";

	@Override
	public List<MemberVO> selectMember() throws Exception {

		return sqlSession.selectList(Namespace + ".selectMember");
	}

}
