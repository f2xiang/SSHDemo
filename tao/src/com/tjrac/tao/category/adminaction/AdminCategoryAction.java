package com.tjrac.tao.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;

/**
 * 后台一级分类管理的action
 * @author FengXiang
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	//----------------模型驱动--------------
	private Category category = new Category();
	
	@Override
	public Category getModel() {
		return category;
	}
	
	//----------------service--------------
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	
	
	//------------------------------------------
	
	/**
	 * 后台： 查询所有一级分类
	 * @return
	 */
	public String findAll(){
		List<Category> allCategory	= this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		return "findAll";
	}
	
	
	/**
	 * 添加一个一级菜单
	 * @return
	 */
	public String save(){
		this.categoryService.save(category);
		return "save";
	}
	
	
	/**
	 * 删除一个一级菜单
	 * @return
	 */
	public String delete(){
		this.categoryService.deleteByCid(category.getCid());
		return "delete";
	}
	
	
	/**
	 * 编辑一级菜单的ui界面
	 * @return
	 */
	public String editUI(){
		category = this.categoryService.findByCid(category.getCid());
		return "editUI";
	}
	
	
	public String edit(){
		this.categoryService.update(category);
		return "edit";
	}
	
}
