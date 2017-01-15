package com.tjrac.tao.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tjrac.tao.user.service.UserService;
import com.tjrac.tao.user.vo.User;

/**
 * �û�ģ��action��
 * @author FengXiang
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//--------------ģ������-------------------
	User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	
	//----------------service------------------
	
	 private UserService userService;
	 
	 public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	//-------------------------------------------
	/**
	 * ע��ҳ��UI
	 * @return
	 */
	public String registerUI(){
		
		return "registerUI";
	}
	
	/**
	 * ajax�첽У��   У���û����Ƿ��Ѵ���
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		User findUser = this.userService.findByUsername(user.getUsername());
		
		//���response����  ��ҳ��������-->������� ���ñ���
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		if(findUser != null){
			//�û����Ѿ�����
			response.getWriter().write("<font color='red'>�û����Ѿ�����</font>");
		}else{
			//�û�������ʹ��
			response.getWriter().write("<font color='green'>�û�������ʹ��</font>");
		}
		
		//ajax����Ҫҳ�����ת
		return NONE;
	}

	/**
	 * �û�ע��
	 * @return
	 */
	public String register(){
		
		//��֤���У��
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		
		if(checkcode.equalsIgnoreCase(user.getCaptcha())){
			//��֤����ȷ
			
			//ע�����ݵĺ�̨У�� -- > UserAction-user_register-validation.xml
			
			//���ݴ浽���ݿ�
			this.userService.add(user);
			this.addActionMessage("ע��ɹ������뵽�����м���~");
			return "msg";
		}else{
			this.addActionMessage("��֤�����");
			return "input";
		}
		
		
		
	}
	
	/**
	 * ���伤��
	 * @return
	 */
	public String active(){
		//�û���������� �������ļ����� ���ݼ������ѯ�û�  ������  �޸�״̬ Ϊ1 
		
		//���ռ����룬��ѯ�û�
		User findUser = this.userService.findByCode(user.getCode());
		if(findUser == null){
			//˵��û�в鵽
			this.addActionMessage("��������󣬼���ʧ��");
		}else{
			//����ɹ�  
			//1�ı伤��״̬
			findUser.setState(1);
			//2��ռ�����
			findUser.setCode(null);
			//3�������ݿ���Ϣ
			this.userService.updateUser(findUser);
			//4������Ϣ
			this.addActionMessage("����ɹ�! ���¼");
		}
		
		return "msg";
		
	}
	
	/**
	 * ��ת����¼ҳ��
	 * @return
	 */
	public String loginUI(){
		return "loginUI";
	}
	
	/**
	 *�û���¼  �ɹ��ص�������
	 * @return
	 */
	public String login(){
		User findUser = this.userService.login(user);
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		
		if(checkcode.equalsIgnoreCase(user.getCaptcha())){
			//��֤����ȷ
			if(findUser == null){
				//��¼ʧ��
				this.addFieldError("", "�û������������");
				return LOGIN;
			}else{
				//�ɹ� ��¼��־
				ServletActionContext.getRequest().getSession().setAttribute("user", findUser);
				return "loginSuccess";
			}
		}else{
			//��֤�����
			this.addFieldError("", "��֤�����");
			return LOGIN;
		}
		
		
	}
	
	
	/**
	 * ע����¼
	 * @return
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "logout";
	}
}
