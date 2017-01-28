package com.tjrac.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	//������֤��
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
	
	
	/**
	 * ��¼ģ��
	 * @return
	 */
	public String login(){
		//�ж���֤���Ƿ���ȷ
		String checkcode2 = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(checkcode2)){
			//��֤����ȷ
			User finduser =  this.userService.login(model);
			if(finduser != null){
				//��½�ɹ� ���óɹ���־
				ServletActionContext.getRequest().getSession().setAttribute("user", finduser);
				return "loginsuccess";
			}else{
				this.addFieldError("", "�˺Ż����벻��ȷ");
				return "login";
			}
		}else{
			this.addFieldError("", "��֤�벻��ȷ");
			return "login";
		}
		
	}
	
	/**
	 * �û��˳���¼
	 * @return
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	
	/**
	 * �޸�����
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException{
		User user =	 (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String password = MyMd5Util.getMD5Value(model.getPassword());
		String flag = "1";
		try {
			this.userService.updatepwd(password, user.getId());
		} catch (Exception e) {
			//�޸�����ʧ��
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
}
