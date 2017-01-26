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
 * �־ò�ͨ�����ʵ��
 * @author FengXiang
 * @param <T>
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	//ȷ�����T��������
	private Class<T> entityClass;
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * ���췽��  ��  ��̬�Ļ�ò�����ʵ����
	 */
	public BaseDaoImpl(){
		//������  ���  ���� baseDaoImpl �� ���� (���з���)
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø����ϵķ�������
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
		//ռλ�� ��ֵ
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

}
