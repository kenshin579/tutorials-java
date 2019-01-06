package com.example.dao;

import java.util.List;

import com.example.dto.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> selectMember() throws Exception;
}
