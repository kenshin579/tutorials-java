package com.example.dao;

import com.example.dto.MemberVO;

import java.util.List;

public interface MemberDAO {

	public List<MemberVO> selectMember() throws Exception;
}
