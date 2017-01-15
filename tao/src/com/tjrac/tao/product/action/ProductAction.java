package com.tjrac.tao.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.product.service.ProductService;
import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//-------------------ģ������------------
	
	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}
	
	//------------------service-----------------
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//-------------------����-----------------
	
	/**
	 * ������Ʒid�鿴��Ʒ��ϸ��Ϣ
	 * @return
	 */
	public String findByPid(){
		product = this.productService.findByPid(this.product.getPid());
		return "findByPid";
	}
	
	
	
	//-----------------һ�������id-----------------------
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
	//---------------------��ҳ����---------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	//----------------------------------
	/**
	 * ����һ�������ѯ��Ʒ --ģ����������û��һ�������id  ���� �����Լ��ṩ
	 * @return
	 */
	public String findByCid(){
		//���һ�������ͬʱ  Ҫ��ʾ�������е�һ������  �Լ���Ӧ�Ķ�������  ���� �����ڷ�����ҳ��ʱ���Ѿ���һ������ŵ�session����
		//���� ����Ʒ�б�ҳ��  ֱ����session���������ݾͺ���
		
		//����һ������ ����ҳ��ѯ
		PageBean<Product> pageBean =  this.productService.findByPageCid(cid, currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
	}
	
}
