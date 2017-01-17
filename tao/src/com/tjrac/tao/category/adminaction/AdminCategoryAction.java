package com.tjrac.tao.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;

/**
 * ��̨һ����������action
 * @author FengXiang
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	//----------------ģ������--------------
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
	 * ��̨�� ��ѯ����һ������
	 * @return
	 */
	public String findAll(){
		List<Category> allCategory	= this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		return "findAll";
	}
	
	
	/**
	 * ���һ��һ���˵�
	 * @return
	 */
	public String save(){
		this.categoryService.save(category);
		return "save";
	}
	
	
	/**
	 * ɾ��һ��һ���˵�
	 * @return
	 */
	public String delete(){
		this.categoryService.deleteByCid(category.getCid());
		return "delete";
	}
	
	
	/**
	 * �༭һ���˵���ui����
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
