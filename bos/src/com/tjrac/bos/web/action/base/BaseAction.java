package com.tjrac.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.bos.domain.Region;
import com.tjrac.bos.service.DecidedzoneService;
import com.tjrac.bos.service.FunctionService;
import com.tjrac.bos.service.NoticebillService;
import com.tjrac.bos.service.RegionService;
import com.tjrac.bos.service.RoleService;
import com.tjrac.bos.service.StaffService;
import com.tjrac.bos.service.SubareaService;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.service.WorkordermanageService;
import com.tjrac.bos.utils.PageBean;
import com.tjrac.crm.service.CustomerService;

/**
 * 通用action实现
 * @author FengXiang
 * @param <T>
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	//----------service-----------
	@Resource
	protected UserService userService;
	
	@Resource
	protected SubareaService subareaService;
	
	@Resource
	protected StaffService staffService;
	
	@Resource
	protected RegionService regionService;
	
	@Resource
	protected DecidedzoneService decidedzoneService;
	
	@Resource
	protected CustomerService customerService;
	
	@Resource
	protected NoticebillService noticebillService;
	
	@Resource
	protected WorkordermanageService workordermanageService;
	
	@Resource
	protected FunctionService functionService;
	
	@Resource
	protected RoleService roleService;
	
	//-----------分页数据----------
	protected PageBean pageBean = new PageBean();
	
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	
	DetachedCriteria detachedCriteria = null;
	//------------------------

	protected T model;
	
	@Override
	public T getModel() {
		return model;
	}
	
	public BaseAction(){
		ParameterizedType parameterizedType =null;	
		
		if(this.getClass().getGenericSuperclass() instanceof ParameterizedType){
			parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		}else{//action创建了代理的时候--多往上面找一层
			parameterizedType = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		
		
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		//获得实体的类型
		Class<T> entity = (Class<T>) actualTypeArguments[0];
		
		//分页信息--抽取
		detachedCriteria = DetachedCriteria.forClass(entity);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		try {
			model = entity.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把获得的分页数据写成json数据返回
	 * @param pageBean
	 * @param excludes
	 * @throws IOException
	 */
	public void writePageBean2Json(PageBean pageBean, String [] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);//设置不包含的数据
		
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	
	/**
	 * 把获得的list写成json返回去
	 * @param rList
	 * @param strings
	 * @throws IOException 
	 */
	public void writeList2Json(List rList, String[] excludes) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		
		String json = JSONArray.fromObject(rList, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		
	}
	
	
	
	/**
	 * 把一个对象转成json
	 * @param Object
	 * @param excludes
	 * @throws IOException
	 */
	public void writeObj2Json(Object obj, String [] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);//设置不包含的数据
		
		
		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
