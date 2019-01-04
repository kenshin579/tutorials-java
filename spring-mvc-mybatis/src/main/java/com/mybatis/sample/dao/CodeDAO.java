package com.mybatis.sample.dao;

import com.mybatis.sample.vo.CodeVO;

import java.util.List;
import java.util.Map;

public interface CodeDAO {

	public List<Map<String, Object>> selectCodeList(CodeVO vo);

	public int insertCode(CodeVO vo);

	public int deleteCode(CodeVO vo);

}
