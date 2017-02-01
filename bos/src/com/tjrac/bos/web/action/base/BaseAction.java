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
 * ͨ��actionʵ��
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
	
	//-----------��ҳ����----------
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
		}else{//action�����˴����ʱ��--����������һ��
			parameterizedType = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		
		
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		//���ʵ�������
		Class<T> entity = (Class<T>) actualTypeArguments[0];
		
		//��ҳ��Ϣ--��ȡ
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
	 * �ѻ�õķ�ҳ����д��json���ݷ���
	 * @param pageBean
	 * @param excludes
	 * @throws IOException
	 */
	public void writePageBean2Json(PageBean pageBean, String [] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);//���ò�����������
		
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	
	/**
	 * �ѻ�õ�listд��json����ȥ
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
	 * ��һ������ת��json
	 * @param Object
	 * @param excludes
	 * @throws IOException
	 */
	public void writeObj2Json(Object obj, String [] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);//���ò�����������
		
		
		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
