package com.tjrac.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tjrac.bos.dao.base.BaseDao;

/**
 * 持久层通用类的实现
 * @author FengXiang
 * @param <T>
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	//确定这个T的类类型
	private Class<T> entityClass;
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 构造方法  ：  动态的获得操作的实体类
	 */
	public BaseDaoImpl(){
		//运行类  获得  父类 baseDaoImpl 的 类型 (含有泛型)
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "from "+entityClass.getSimpleName();
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void update(String queryName, Object... objects) {
		Session session = this.getSession();
		Query query = session.getNamedQuery(queryName);
		//占位符 赋值
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

}
