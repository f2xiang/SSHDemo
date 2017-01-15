package com.tjrac.tao.categorysecond.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.categorysecond.service.CategorySecondService;
import com.tjrac.tao.categorysecond.vo.CategorySecond;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	//--------------模型驱动---------------
	
	private CategorySecond categorySecond = new CategorySecond();
	
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	//---------------service-----------------
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	
	//---------------动作-----------------
	
	

}
