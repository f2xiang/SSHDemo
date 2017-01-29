package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Staff;
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
		
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		
		String name = model.getName();
		if(StringUtils.isNotBlank(name)){
			detachedCriteria2.add(Restrictions.like("name", "%"+name+"%"));
		}
		
		String standard = model.getStandard();
		if(StringUtils.isNotBlank(standard)){
			detachedCriteria2.add(Restrictions.like("standard", "%"+standard+"%"));
		}
		
		String station = model.getStation();
		if(StringUtils.isNotBlank(station)){
			detachedCriteria2.add(Restrictions.like("station", "%"+station+"%"));
		}
		
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
	 * ��ԭ�Ѿ�ɾ����Ա��
	 * @return
	 */
	public String restore(){
		this.staffService.restoreBatch(ids);
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
	
	
	
	/**
	 * ��ѯ�������õ�ȡ��Ա--ɾ��λΪ1�Ĳ���(ajax)
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Staff> sList = this.staffService.findAll();
		//������������ʾ  ����id �� name  �����Ķ�����Ҫ
		this.writeList2Json(sList, new String[]{"decidedzones", "telephone", "haspda", "deltag", "station", "standard"});
		return NONE;
	}
}
