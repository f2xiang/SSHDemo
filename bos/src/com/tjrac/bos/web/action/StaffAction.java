package com.tjrac.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Staff;
import com.tjrac.bos.service.StaffService;
import com.tjrac.bos.utils.PageBean;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	
	@Resource
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	
	/**
	 * 添加取派员
	 * @return
	 */
	public String add(){
		this.staffService.add(model);
		return "list";
	}
	
	
	//-----------分页数据----------
	private int rows;
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}
	//------------------------
	
	/**
	 * 分页查询  ajax请求
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		//没有指定查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		this.staffService.pageQuery(pageBean);
		
		//返回json数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage", "pageSize", "detachedCriteria" });//设置不包含的数据
		
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
		return NONE;
	}
	
	
	//接收ids参数
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
	/**
	 * 批量删除
	 * @return
	 */
	public String delete(){
		this.staffService.deleteBatch(ids);
		return "list";
	}
	
	/**
	 * 编辑
	 * @return
	 */
	public String edit(){
		//因为存在外键的引用 我们传递过来的参数并没有引用信息 防止数据丢失 
		//这里 我们先在数据库进行查询
		Staff staff = this.staffService.findById(model.getId());
		
		//再按照页面传过来的参数进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		
		this.staffService.update(staff);
		return "list";
	}
}
