package com.tjrac.tao.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.categorysecond.service.CategorySecondService;
import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.util.PageBean;

/**
 * 后台二级分类管理的action
 * @author FengXiang
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	//-------------------------模型驱动-----------------
	
	private CategorySecond categorySecond = new CategorySecond();
	
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	
	//-----------------------service--------------------
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	public CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//------------------分页数据---------------------------
	private Integer currentPage;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	//----------------------------------------
	
	/**
	 * 查询所有的二级分类
	 * @return
	 */
	public String findAll(){
		PageBean<CategorySecond> pageBean =	this.categorySecondService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	
	
	/**
	 * 添加二级分类的界面
	 * @return
	 */
	public String addUI(){
		//添加二级分类之前 要先查询一级分类 --再给予显现   这里要调用一级分类的service
		List<Category> allCategory = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		
		
		return "addUI";
	}
	
	
	
	/**
	 * 添加一个二级分类
	 * @return
	 */
	public String add(){
		this.categorySecondService.add(categorySecond);
		return "add";
	}
	
	
	/**
	 * 删除指定id的二级分类
	 * @return
	 */
	public String delete(){
		this.categorySecondService.delete(categorySecond);
		return "delete";
	}
	
	
	/**
	 * 编辑二级分类的ui界面
	 * @return
	 */
	public String editUI(){
		List<Category> allCategory = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		
		categorySecond = this.categorySecondService.findByCsid(categorySecond.getCsid());
		return "editUI";
	}
	
	
	/**
	 * 二级分类的编辑
	 * @return
	 */
	public String edit(){
		this.categorySecondService.update(categorySecond);
		return "edit";
	}
}
