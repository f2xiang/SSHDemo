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
	
	
	/**
	 * ���ȡ��Ա
	 * @return
	 */
	public String add(){
		this.staffService.add(model);
		return "list";
	}
	
	
	
	
	/**
	 * ��ҳ��ѯ  ajax����
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		this.staffService.pageQuery(pageBean);
		//����json����
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria" });
		
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
