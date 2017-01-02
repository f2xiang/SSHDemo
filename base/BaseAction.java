
import java.lang.reflect.ParameterizedType;

import com.tjrac.crm.classes.service.ClassesService;
import com.tjrac.crm.coursetype.service.CourseTypeService;
import com.tjrac.crm.department.service.DepartmentService;
import com.tjrac.crm.post.service.PostService;
import com.tjrac.crm.staff.service.StaffService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
*简化action的操作，子类action只要继承它，并用getXxx获取相应的方法
*/
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	//1 封装数据
	private T t;
	@Override
	public T getModel() {
		return t;
	}
	// 1.1 实例化t
	public BaseAction() {
		try {
			//1 获得T运行时Class
			ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) paramType.getActualTypeArguments()[0];
			//2 反射创建实例
			t = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//2 spring注入service，多个
	// * 提供setter方法，让spring进行注入的
	// * 提供getter方法，让子类可以获得spring注入的对象的。
	//员工
	private StaffService staffService;
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	//职务
	private PostService postService;
	public PostService getPostService() {
		return postService;
	}
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	//部门
	private DepartmentService departmentService;
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//课程类别
	private CourseTypeService courseTypeService;
	public void setCourseTypeService(CourseTypeService courseTypeService) {
		this.courseTypeService = courseTypeService;
	}
	public CourseTypeService getCourseTypeService() {
		return courseTypeService;
	}
	//班级
	private ClassesService classesService;
	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}
	public ClassesService getClassesService() {
		return classesService;
	}
	
	//3 分页数据
	private int pageNum = 1;
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	private int pageSize = 2;  //固定值
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	//4 简化值栈操作
	public void push(Object o){
		ActionContext.getContext().getValueStack().push(o);
	}
	
	public void set(String key ,Object o){
		ActionContext.getContext().getValueStack().set(key, o);
	}
	
	public void put(String key, Object value){
		ActionContext.getContext().put(key, value);
	}
	
	public void putSession(String key, Object value){
		ActionContext.getContext().getSession().put(key, value);
	}
	
	public void putApplication(String key, Object value){
		ActionContext.getContext().getApplication().put(key, value);
	}
	
	

}
