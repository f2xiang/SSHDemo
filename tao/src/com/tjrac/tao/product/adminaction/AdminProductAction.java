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

	//-----------------ģ������---------------------
	
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
	
	//---------��ҳ����---------------------------
	private int currentPage;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//-----------------------------
	/**
	 * ��ѯ���е���Ʒ--����ҳ
	 * @return
	 */
	public String findAll(){
		PageBean<Product> pageBean = this.productService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	
	
	/**
	 * �����Ʒ 
	 * @return
	 */
	public String addUI(){
		//�����Ʒ֮ǰҪ�Ȳ�ѯ�����Ķ�������
		List<CategorySecond> csList = this.categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addUI";
	}
	
	
	//------------�ļ��ϴ��Ĳ���------------------
	private File upload;    //�ϴ����ļ�
	
	private String uploadFileName;  //�����ļ��ϴ����ļ���
	
	private String uploadContextType;  //�����ļ��ϴ��� �ļ�������
	
	
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
	 * ������Ʒ�ķ��� --��ƷͼƬ�ϴ�
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException{
		//���ݵĲ�ȫ
		product.setPdate(new Date());
		
		//�ϴ� ͼƬ
		if(upload != null){
			//����ļ��ϴ��Ĵ��̵ľ��Ե�·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile = new File(realPath+"//"+uploadFileName);
			//�ļ����ϴ�
			FileUtils.copyFile(upload, diskFile);
			
			//���ݿ�ͼƬ·��
			product.setImage("products/"+uploadFileName);
		}
		
		this.productService.add(product);
		return "add";
	}
	
	
	/**
	 * ɾ��һ����Ʒ
	 * @return
	 */
	public String delete(){
		product = this.productService.findByPid(product.getPid());
		//ɾ���ϴ���ͼƬ
		String path = product.getImage();
		File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
		file.delete();
		
		this.productService.delete(product);
		
		return "delete";
	}
	
	
	
	/**
	 * �༭��ui����
	 * @return
	 */
	public String editUI(){
		List<CategorySecond> csList = this.categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		product = this.productService.findByPid(product.getPid());
		
		return "editUI";
	}
	
	
	/**
	 * ������Ʒ����Ϣ
	 * @return
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		//ʱ���޸�
		product.setPdate(new Date());
		
		//�ж��Ƿ������ϴ�ͼƬ--�ļ��ϴ�
		if(upload != null){
			//ɾ��ԭ����ͼƬ
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			//����ļ��ϴ���·��--�ļ��ϴ�
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + "//" +uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/"+uploadFileName);
		}
		
		this.productService.update(product);
		return "edit";
	}
	
	
}
