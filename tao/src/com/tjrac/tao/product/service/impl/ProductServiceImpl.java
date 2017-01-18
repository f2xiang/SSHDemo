package com.tjrac.tao.product.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tjrac.tao.product.dao.ProductDao;
import com.tjrac.tao.product.service.ProductService;
import com.tjrac.tao.product.vo.Product;
import com.tjrac.tao.util.PageBean;

/**
 * ��Ʒservice���ʵ����
 * @author FengXiang
 *
 */
@Transactional
public class ProductServiceImpl implements ProductService{
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> findHot() {
		return this.productDao.findHot();
	}

	@Override
	public List<Product> findNew() {
		return this.productDao.findNew();
	}

	@Override
	public Product findByPid(Integer pid) {
		return this.productDao.findByPid(pid);
	}

	@Override
	public PageBean<Product> findByPageCid(Integer cid, int currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰ��ҳ��
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ�ļ�¼��
		int pageCount = 8;
		pageBean.setPageCount(pageCount);
		//�����ܵļ�¼��
		int totalCount = this.productDao.findTotalCount(cid);
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//������ʼ
		int beginIndex = (currentPage - 1) * pageCount; 
		//ÿҳ��ʾ�����ݼ���
		List<Product> list = this.productDao.findByPageCid(cid, beginIndex, pageCount);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Product> findByPageCsid(Integer csid, int currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰ��ҳ��
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ�ļ�¼��
		int pageCount = 8;
		pageBean.setPageCount(pageCount);
		//�����ܵļ�¼��
		int totalCount = this.productDao.findTotalCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//������ʼ
		int beginIndex = (currentPage - 1) * pageCount; 
		//ÿҳ��ʾ�����ݼ���
		List<Product> list = this.productDao.findByPageCsid(csid, beginIndex, pageCount);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Product> findAll(int currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		
		//���õ�ǰ��ҳ��
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ�ļ�¼��
		int pageCount = 10;
		pageBean.setPageCount(pageCount);
		//�����ܵļ�¼��
		int totalCount = this.productDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//������ʼ
		int beginIndex = (currentPage - 1) * pageCount; 
		//ÿҳ��ʾ�����ݼ���
		List<Product> list = this.productDao.findAllByPage(beginIndex, pageCount);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void add(Product product) {
		this.productDao.add(product);
	}

	@Override
	public void update(Product product) {
		this.productDao.update(product);
	}

	@Override
	public void delete(Product product) {
		this.productDao.delete(this.productDao.findByPid(product.getPid()));
	}
	
	
	
}
