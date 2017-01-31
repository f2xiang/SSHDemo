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
		this.noticebillDao.save(model);   //持久对象
		
		//根据地址尝试自动分单
		String address = model.getPickaddress();
		//crm系统中---根据地址 查询定区的id
		String did = this.customerService.findDecidedzoneByAddress(address);
		if(did != null){
			//查到定区的id, 可以自动分单
			Decidedzone decidedzone = this.decidedzoneDao.findById(did);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff); //业务通知单 关联匹配到的取派员 --自动更新
			model.setOrdertype("自动");
			//为取派员 创建 工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebill(model);
			workbill.setPickstate("未取件");
			workbill.setRemark(model.getRemark());
			workbill.setStaff(staff);
			workbill.setType("新单");
			this.workbilldao.save(workbill);
			
			//调用短信平台   给取派员发送短信
			
		}else{
			//转为人工的分单
			model.setOrdertype("人工");
		}
	}
	
	
	
}
