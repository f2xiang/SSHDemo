package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.User;
import com.tjrac.bos.service.UserService;
import com.tjrac.bos.utils.MyMd5Util;
import com.tjrac.bos.web.action.base.BaseAction;
import com.tjrac.crm.domain.Customer;
import com.tjrac.crm.service.CustomerService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	
	//������֤��
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
	/**
	 * ʹ��shiro�ṩ�ķ�����������֤
	 * @return
	 */
	public String login(){
		//�ж���֤���Ƿ���ȷ
		String checkcode2 = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equalsIgnoreCase(checkcode2)){
			//��֤����ȷ
			//��õ�ǰ���û�����
			Subject subject = SecurityUtils.getSubject();  //δ��֤״̬
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MyMd5Util.getMD5Value(model.getPassword()));
			try {
				subject.login(token );
			} catch (UnknownAccountException e) {
				//���ô�����Ϣ
				this.addFieldError("", "�û���������");
				return "login";
			} catch (Exception e) {
				this.addFieldError("", "�û��������벻��ȷ");
				return "login";
			}
			//��֤ͨ��  ��ȡ��֤��Ϣ������ ��user
			User user =	(User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "loginsuccess";
		}else{
			this.addFieldError("", "��֤�벻��ȷ");
			return "login";
		}
		
	}
	
	
	/**
	 * ��¼ģ��--����shiro�ķ���������
	 * @return
	 */
	public String login2(){
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
