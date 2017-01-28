package com.tjrac.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.bos.utils.PageBean;

/**
 * ͨ��actionʵ��
 * @author FengXiang
 * @param <T>
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
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
		ParameterizedType parameterizedType =	(ParameterizedType) this.getClass().getGenericSuperclass();
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

	
	public void writePageBean2Json(PageBean pageBean, String [] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);//���ò�����������
		
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
