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
 * ��̨������������action
 * @author FengXiang
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	//-------------------------ģ������-----------------
	
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
	
	//------------------��ҳ����---------------------------
	private Integer currentPage;
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	//----------------------------------------
	
	/**
	 * ��ѯ���еĶ�������
	 * @return
	 */
	public String findAll(){
		PageBean<CategorySecond> pageBean =	this.categorySecondService.findAllByPage(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	
	
	/**
	 * ��Ӷ�������Ľ���
	 * @return
	 */
	public String addUI(){
		//��Ӷ�������֮ǰ Ҫ�Ȳ�ѯһ������ --�ٸ�������   ����Ҫ����һ�������service
		List<Category> allCategory = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		
		
		return "addUI";
	}
	
	
	
	/**
	 * ���һ����������
	 * @return
	 */
	public String add(){
		this.categorySecondService.add(categorySecond);
		return "add";
	}
	
	
	/**
	 * ɾ��ָ��id�Ķ�������
	 * @return
	 */
	public String delete(){
		this.categorySecondService.delete(categorySecond);
		return "delete";
	}
	
	
	/**
	 * �༭���������ui����
	 * @return
	 */
	public String editUI(){
		List<Category> allCategory = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("allCategory", allCategory);
		
		categorySecond = this.categorySecondService.findByCsid(categorySecond.getCsid());
		return "editUI";
	}
	
	
	/**
	 * ��������ı༭
	 * @return
	 */
	public String edit(){
		this.categorySecondService.update(categorySecond);
		return "edit";
	}
}
