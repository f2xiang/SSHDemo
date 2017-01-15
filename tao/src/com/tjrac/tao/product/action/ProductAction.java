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

	//-------------------模型驱动------------
	
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
	
	//-------------------动作-----------------
	
	/**
	 * 根据商品id查看商品详细信息
	 * @return
	 */
	public String findByPid(){
		product = this.productService.findByPid(this.product.getPid());
		return "findByPid";
	}
	
	
	
	//-----------------一级分类的id-----------------------
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
	//---------------------分页数据---------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	//----------------------------------
	/**
	 * 根据一级分类查询商品 --模型驱动里面没有一级分类的id  这里 我们自己提供
	 * @return
	 */
	public String findByCid(){
		//点击一级分类的同时  要显示下面所有的一级分类  以及相应的二级分类  但是 我们在访问首页的时候已经把一级分类放到session中了
		//所以 在商品列表页中  直接在session里面拿数据就好了
		
		//根据一级分类 带分页查询
		PageBean<Product> pageBean =  this.productService.findByPageCid(cid, currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
	}
	
}
