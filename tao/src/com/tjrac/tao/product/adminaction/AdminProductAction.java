package com.tjrac.tao.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.categorysecond.service.CategorySecondService;
import com.tjrac.tao.categorysecond.vo.CategorySecond;
import com.tjrac.tao.product.service.ProductService;
import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	//-----------------模型驱动---------------------
	
	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}

	//-------------------service------------------------
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	//---------分页数据---------------------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//-----------------------------
	/**
	 * 查询所有的商品--带分页
	 * @return
	 */
	public String findAll(){
		PageBean<Product> pageBean = this.productService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	
	
	/**
	 * 添加商品 
	 * @return
	 */
	public String addUI(){
		//添加商品之前要先查询所属的二级分类
		List<CategorySecond> csList = this.categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addUI";
	}
	
	
	//------------文件上传的参数------------------
	private File upload;    //上传的文件
	
	private String uploadFileName;  //接收文件上传的文件名
	
	private String uploadContextType;  //接收文件上传的 文件的类型
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	
	//-----------------------------------
	

	/**
	 * 保存商品的方法 --商品图片上传
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException{
		//数据的补全
		product.setPdate(new Date());
		
		//上传 图片
		if(upload != null){
			//获得文件上传的磁盘的绝对的路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			//文件的上传
			FileUtils.copyFile(upload, diskFile);
			
			//数据库图片路径
			product.setImage("products/"+uploadFileName);
		}
		
		this.productService.add(product);
		return "add";
	}
	
	
	/**
	 * 删除一个商品
	 * @return
	 */
	public String delete(){
		product = this.productService.findByPid(product.getPid());
		//删除上传的图片
		String path = product.getImage();
		File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
		file.delete();
		
		this.productService.delete(product);
		
		return "delete";
	}
	
	
	
	/**
	 * 编辑的ui界面
	 * @return
	 */
	public String editUI(){
		List<CategorySecond> csList = this.categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		product = this.productService.findByPid(product.getPid());
		
		return "editUI";
	}
	
	
	/**
	 * 更新商品的信息
	 * @return
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		//时间修改
		product.setPdate(new Date());
		
		//判断是否重新上传图片--文件上传
		if(upload != null){
			//删除原来的图片
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			//获得文件上传的路径--文件上传
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + "//" +uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/"+uploadFileName);
		}
		
		this.productService.update(product);
		return "edit";
	}
	
	
}
