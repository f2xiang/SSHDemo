package com.tjrac.tao.category.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;

/**
 * һ�������action��
 * @author FengXiang
 *
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{

	//----------------��װģ������---------------------
	private Category category = new Category();
	
	@Override
	public Category getModel() {
		return category;
	}
	
	//-----------------service--------------------------
	
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	//-------------------����-------------------------------
	
	
	

}
