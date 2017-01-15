package com.tjrac.tao.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjrac.tao.category.service.CategoryService;
import com.tjrac.tao.category.vo.Category;
import com.tjrac.tao.product.service.ProductService;
import com.tjrac.tao.product.vo.Product;

/**
 * 访问首页的action
 * @author FengXiang
 *
 */
public class IndexAction extends ActionSupport{
	
	
	
	//-------------------service--------------------
	//注入一级分类的service  因为一访问首页 就要对一级分类进行查询
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	//注入商品的service  因为访问首页 要对热门商品 和 最新商品进行查询 和显示
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//----------------------动作-------------------
	
	/**
	 * 进入主页 
	 */
	public String execute(){
		//查询所有一级分类
		List<Category> cList = this.categoryService.findAll();
		//因为每个页面都会有一级分类  所以这里我们将其存放在session里面 就不用每次去数据库查询了
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品（首页最多只能显示10个热门商品）
		List<Product> hList = this.productService.findHot();
		//热门商品是动态变化的 需要从数据库查询 这里我们将其存放在值栈里面
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品 最多只显示10个
		List<Product> nList = this.productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
	
	
	
}
