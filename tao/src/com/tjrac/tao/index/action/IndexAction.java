package com.tjrac.tao.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.product.service.ProductService;
import com.tjrac.tao.product.vo.Product;

/**
 * ������ҳ��action
 * @author FengXiang
 *
 */
public class IndexAction extends ActionSupport{
	
	
	
	//-------------------service--------------------
	//ע��һ�������service  ��Ϊһ������ҳ ��Ҫ��һ��������в�ѯ
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	//ע����Ʒ��service  ��Ϊ������ҳ Ҫ��������Ʒ �� ������Ʒ���в�ѯ ����ʾ
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//----------------------����-------------------
	
	/**
	 * ������ҳ 
	 */
	public String execute(){
		//��ѯ����һ������
		List<Category> cList = this.categoryService.findAll();
		//��Ϊÿ��ҳ�涼����һ������  �����������ǽ�������session���� �Ͳ���ÿ��ȥ���ݿ��ѯ��
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ����ҳ���ֻ����ʾ10��������Ʒ��
		List<Product> hList = this.productService.findHot();
		//������Ʒ�Ƕ�̬�仯�� ��Ҫ�����ݿ��ѯ �������ǽ�������ֵջ����
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ ���ֻ��ʾ10��
		List<Product> nList = this.productService.findNew();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
	
	
	
}
