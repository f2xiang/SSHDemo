package com.tjrac.tao.categorysecond.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.categorysecond.dao.CategorySecondDao;
import com.tjrac.tao.categorysecond.service.CategorySecondService;
import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.util.PageBean;

@Transactional
public class CategorySecondServiceImpl implements CategorySecondService{
	private  CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	@Override
	public PageBean<CategorySecond> findAllByPage(Integer currentPage) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前的页数
		pageBean.setCurrentPage(currentPage);
		//设置每页显示的记录数
		int pageCount = 10;
		pageBean.setPageCount(pageCount);
		//设置总的记录数
		int totalCount = this.categorySecondDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置起始索引
		int beginIndex = (currentPage - 1) * pageCount;
		//设置数据
		List<CategorySecond> list = this.categorySecondDao.findByPage(beginIndex, pageCount);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void add(CategorySecond categorySecond) {
		this.categorySecondDao.add(categorySecond);
	}

	@Override
	public void delete(CategorySecond categorySecond) {
		this.categorySecondDao.delete(this.categorySecondDao.findByCsid(categorySecond.getCsid()));
	}

	@Override
	public CategorySecond findByCsid(Integer csid) {
		return this.categorySecondDao.findByCsid(csid);
	}

	@Override
	public void update(CategorySecond categorySecond) {
		this.categorySecondDao.update(categorySecond);
	}

	@Override
	public List<CategorySecond> findAll() {
		return this.categorySecondDao.findAll();
	}

	
	
}
