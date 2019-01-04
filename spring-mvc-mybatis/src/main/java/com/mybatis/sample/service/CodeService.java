package com.mybatis.sample.service;

import com.mybatis.sample.vo.CodeVO;

import java.util.List;
import java.util.Map;

public interface CodeService {

	List<Map<String, Object>> getCodeList(CodeVO vo);

	int saveCode(CodeVO vo);

	int removeCode(CodeVO vo);

}
