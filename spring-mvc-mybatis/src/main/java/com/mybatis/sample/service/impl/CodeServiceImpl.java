package com.mybatis.sample.service.impl;

import com.mybatis.sample.dao.CodeDAO;
import com.mybatis.sample.service.CodeService;
import com.mybatis.sample.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDAO codeDAO;

	@Override
	public List<Map<String, Object>> getCodeList(CodeVO vo) {
		return codeDAO.selectCodeList(vo);
	}

	@Override
	public int saveCode(CodeVO vo) {
		int result = codeDAO.insertCode(vo);
		System.out.println("[ save result ] " + result);
		return result;
	}

	@Override
	public int removeCode(CodeVO vo) {
		int result = codeDAO.deleteCode(vo);
		System.out.println("[ remove result ] " + result);
		return result;
	}
}
