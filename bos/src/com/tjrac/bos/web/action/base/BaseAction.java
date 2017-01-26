package com.tjrac.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ͨ��actionʵ��
 * @author FengXiang
 * @param <T>
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

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
		try {
			model = entity.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
