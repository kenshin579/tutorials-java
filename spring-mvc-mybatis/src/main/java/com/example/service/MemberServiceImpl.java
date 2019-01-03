package com.example.service;

import com.example.dao.MemberDAO;
import com.example.dto.MemberVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;

	@Override
	public List<MemberVO> selectMember() throws Exception {

		return dao.selectMember();
	}

}
