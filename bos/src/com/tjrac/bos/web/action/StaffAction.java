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
	 * ���ȡ��Ա
	 * @return
	 */
	public String add(){
		this.staffService.add(model);
		return "list";
	}
	
	
	//-----------��ҳ����----------
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
	 * ��ҳ��ѯ  ajax����
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		//û��ָ����ѯ����
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		this.staffService.pageQuery(pageBean);
		
		//����json����
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage", "pageSize", "detachedCriteria" });//���ò�����������
		
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
		return NONE;
	}
	
	
	//����ids����
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
	/**
	 * ����ɾ��
	 * @return
	 */
	public String delete(){
		this.staffService.deleteBatch(ids);
		return "list";
	}
	
	/**
	 * �༭
	 * @return
	 */
	public String edit(){
		//��Ϊ������������� ���Ǵ��ݹ����Ĳ�����û��������Ϣ ��ֹ���ݶ�ʧ 
		//���� �����������ݿ���в�ѯ
		Staff staff = this.staffService.findById(model.getId());
		
		//�ٰ���ҳ�洫�����Ĳ������и���
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		
		this.staffService.update(staff);
		return "list";
	}
}
