package com.tjrac.crm.staff.service.impl;

import java.util.List;

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

}
