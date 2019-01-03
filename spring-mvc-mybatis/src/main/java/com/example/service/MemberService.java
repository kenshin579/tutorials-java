package com.example.service;

import com.example.dto.MemberVO;

import java.util.List;

public interface MemberService {

	public List<MemberVO> selectMember() throws Exception;
}
