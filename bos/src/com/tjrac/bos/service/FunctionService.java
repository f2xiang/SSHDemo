package com.tjrac.bos.service;

import java.util.List;

import com.tjrac.bos.domain.Function;
import com.tjrac.bos.utils.PageBean;

public interface FunctionService {

	public void pageQuery(PageBean pageBean);

	public List<Function> findAll();

	public void save(Function model);

}
