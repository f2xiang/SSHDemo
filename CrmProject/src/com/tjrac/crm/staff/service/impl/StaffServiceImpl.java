package com.tjrac.crm.staff.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tjrac.crm.page.PageBean;
import com.tjrac.crm.staff.dao.StaffDao;
import com.tjrac.crm.staff.domain.CrmStaff;
import com.tjrac.crm.staff.service.StaffService;
import com.tjrac.crm.utils.MyMd5Util;

public class StaffServiceImpl implements StaffService{
    //ע��  Ҫ��spring����ע��
	private StaffDao staffDao;
	
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
	@Override
	public CrmStaff login(CrmStaff crmStaff) {
		String staffMd5Pwd =  MyMd5Util.getMD5Value(crmStaff.getLoginPwd());
		return staffDao.find(crmStaff.getLoginName(), staffMd5Pwd);
	}

	@Override
	public List<CrmStaff> findAllStaff() {
		return staffDao.findAll();
	}

	@Override
	public CrmStaff findById(String staffId) {
		return staffDao.findById(staffId);
	}

	@Override
	public void addStaff(CrmStaff crmStaff) {
		staffDao.add(crmStaff);
	}

	@Override
	public void updateStaff(CrmStaff crmStaff) {
		//������ ������ôһ������   �������ǲ�֪���û���û���޸�����������(Ĭ��32λ)
		//���� �������ǲ�ȡ��ôһ��������
		//�Ȳ�ѯ  ͨ��id ��ѯ���û������� �� ҳ���ϵĽ��бȽ�  ��� һ��  ˵��û���޸�����
		//�����һ��  ˵�� �޸��������� Ҫ���½���MD5�ļ���
		//�Ѹ�id��Ա��������  ȫ������
		
		//1������id�ѳ���ԭʼ���ݵ�Ա��ʵ���ҵ�
		CrmStaff findStaff = staffDao.findById(crmStaff.getStaffId());
		//2���ж������Ƿ�һ��  ��һ�� ��ҳ�洫������Ա�������� �Ƚ���md5���� �ٴ��� �ҵ����Ǹ�Ա��ʵ��
		if(! findStaff.getLoginPwd().equals(crmStaff.getLoginPwd())){
			findStaff.setLoginPwd(MyMd5Util.getMD5Value(crmStaff.getLoginPwd()));
		}
		//3���������������εĴ����Ǹ�Ա��ʵ��
		findStaff.setLoginName(crmStaff.getLoginName());
		findStaff.setStaffName(crmStaff.getStaffName());
		findStaff.setGender(crmStaff.getGender());
		findStaff.setOnDutyDate(crmStaff.getOnDutyDate());
		findStaff.setPost(crmStaff.getPost());
	}

	@Override
	public boolean updatePwdOld(String oldPassword, String staffId) {
		boolean flag = false;
		
		//�û� �����������ܳ�md5
		String md5pwd = MyMd5Util.getMD5Value(oldPassword);
		
		//�����ݿ����ҳ���������
		String psw = this.staffDao.findById(staffId).getLoginPwd();
		
		if(psw.equals(md5pwd)){
			flag = true;
		}
		
		return flag;
	}

	@Override
	public boolean updatePwdNew(String newPassword, String reNewPassword, String staffId) {
		boolean flag = false;
		//���� ����������������Ƿ�һ��
		if(newPassword.equals(reNewPassword)){
			flag = true;
		}
		
		
		//���������¼��ܣ� �������ݿ��е�����
		CrmStaff findStaff =  this.staffDao.findById(staffId);
		String md5pwd = MyMd5Util.getMD5Value(newPassword);
		findStaff.setLoginPwd(md5pwd);
		
		return flag;
	}

	@Override
	public PageBean<CrmStaff> findAllStaff(CrmStaff crmStaff, int pageNum,
			int pageSize) {
		
		//1 ������ѯ
		StringBuilder sb = new StringBuilder();
		List<Object> paramslist = new ArrayList<Object>();
		
		//��������
		//����
//		if(StringUtils.isNotBlank(crmStaff.getPost().getDepartment().getDepId())){
//			sb.append(" and CrmStaff.post.")
//		}
		//TODO ���� ְ�� �� ��������ѯ
		//ְ��
		
		//����
		if(StringUtils.isNotBlank(crmStaff.getStaffName())){
			sb.append(" and staffName like ?");
			paramslist.add("%"+crmStaff.getStaffName()+"%");
		}
		
		String condition = sb.toString();
		
		Object [] params = paramslist.toArray();
		
		
		//��ҳ
		int totalRecord = this.staffDao.getTotalRecord(condition, params);
		
		PageBean<CrmStaff> pageBean = new PageBean<CrmStaff>(pageNum, pageSize, totalRecord);
		
		List<CrmStaff> data =
				 this.staffDao.findAll(condition, params, pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		
		return pageBean;
	}

}
