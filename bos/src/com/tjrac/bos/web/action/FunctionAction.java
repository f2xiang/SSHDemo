package com.tjrac.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tjrac.bos.domain.Function;
import com.tjrac.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	
	/**
	 * 分页查询方法	
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException{
		//这里有一个问题  。。。。function这个类的page字段 和 分页的数据冲突了。。。
		//struts默认把数据放到model中  没办法 这里我们只能手工取出来  放到pageBean里面
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));

		this.functionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"detachedCriteria", "currentPage", "functions", "function", "roles" });
		return NONE;
	}
	
	
	
	
	/**
	 * 查询所有权限
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Function> fList = this.functionService.findAll();
		this.writeList2Json(fList, new String[]{"function", "description", "functions", "roles", "generatemenu"});
		return NONE;	
	}
	
	
	
	
	/**
	 * 添加权限
	 * @return
	 */
	public String add(){
		this.functionService.save(model);
		return "list";
	}
}
