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
	 * 添加取派员
	 * @return
	 */
	public String add(){
		this.staffService.add(model);
		return "list";
	}
	
	
	
	
	/**
	 * 分页查询  ajax请求
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
		//返回json数据
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria" });
		
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
	 * 还原已经删除的员工
	 * @return
	 */
	public String restore(){
		this.staffService.restoreBatch(ids);
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
	
	
	
	/**
	 * 查询所有有用的取派员--删除位为1的不算(ajax)
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Staff> sList = this.staffService.findAll();
		//在下拉框中显示  除了id 和 name  其他的都不需要
		this.writeList2Json(sList, new String[]{"decidedzones", "telephone", "haspda", "deltag", "station", "standard"});
		return NONE;
	}
}
