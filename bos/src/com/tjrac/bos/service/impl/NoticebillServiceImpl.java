package com.tjrac.bos.service.impl;

import java.sql.Timestamp;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjrac.bos.dao.DecidedzoneDao;
import com.tjrac.bos.dao.NoticebillDao;
import com.tjrac.bos.dao.WorkbillDao;
import com.tjrac.bos.domain.Decidedzone;
import com.tjrac.bos.domain.Noticebill;
import com.tjrac.bos.domain.Staff;
import com.tjrac.bos.domain.Workbill;
import com.tjrac.bos.service.NoticebillService;
import com.tjrac.crm.service.CustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService{
	
	@Resource
	private NoticebillDao noticebillDao;
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private DecidedzoneDao decidedzoneDao;
	
	@Resource
	private WorkbillDao workbilldao;

	@Override
	public void add(Noticebill model) {
		this.noticebillDao.save(model);   //�־ö���
		
		//���ݵ�ַ�����Զ��ֵ�
		String address = model.getPickaddress();
		//crmϵͳ��---���ݵ�ַ ��ѯ������id
		String did = this.customerService.findDecidedzoneByAddress(address);
		if(did != null){
			//�鵽������id, �����Զ��ֵ�
			Decidedzone decidedzone = this.decidedzoneDao.findById(did);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff); //ҵ��֪ͨ�� ����ƥ�䵽��ȡ��Ա --�Զ�����
			model.setOrdertype("�Զ�");
			//Ϊȡ��Ա ���� ����
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebill(model);
			workbill.setPickstate("δȡ��");
			workbill.setRemark(model.getRemark());
			workbill.setStaff(staff);
			workbill.setType("�µ�");
			this.workbilldao.save(workbill);
			
			//���ö���ƽ̨   ��ȡ��Ա���Ͷ���
			
		}else{
			//תΪ�˹��ķֵ�
			model.setOrdertype("�˹�");
		}
	}
	
	
	
}
