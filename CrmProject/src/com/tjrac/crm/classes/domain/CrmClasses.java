package com.tjrac.crm.classes.domain;

import java.util.Date;

import com.tjrac.crm.coursetype.domain.CrmCourseType;


/*
 * CREATE TABLE `crm_classes` (
  `classId` varchar(50) NOT NULL PRIMARY KEY,
  `courseTypeId` varchar(255) DEFAULT NULL, 外键
  `name` varchar(50) DEFAULT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `totalCount` int(11) DEFAULT NULL,
  `upgradeCount` int(11) DEFAULT NULL,
  `changeCount` int(11) DEFAULT NULL,
  `runoffCount` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `uploadPath` varchar(200) DEFAULT NULL,
  `uploadFilename` varchar(100) DEFAULT NULL,
  CONSTRAINT FOREIGN KEY (`courseTypeId`) REFERENCES `crm_course_type` (`courseTypeId`)
) ;
 */
public class CrmClasses {
	private String classId;
	private String name;   //课程名称
	private Date beginTime;  //开班 的时间
	private Date endTime;    //毕业时间
	private String status;   //状态
	private Integer totalCount;   //总人数
	private Integer upgradeCount;  //升级数
	private Integer changeCount;	//转班数
	
	private Integer runoffCount;  //流失数
	private String remark;       //描述
	private Date uploadTime;      //上传时间
	private String uploadPath;     //上传课表的路径
	private String uploadFilename;   //上传课表的名称
	
	//班级 可以对  课程类型   如 123班 都是  java   多 对 【一】  	
	private CrmCourseType courseType; 

	
	
	
	public Integer getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(Integer changeCount) {
		this.changeCount = changeCount;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getUpgradeCount() {
		return upgradeCount;
	}

	public void setUpgradeCount(Integer upgradeCount) {
		this.upgradeCount = upgradeCount;
	}

	public Integer getRunoffCount() {
		return runoffCount;
	}

	public void setRunoffCount(Integer runoffCount) {
		this.runoffCount = runoffCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUploadFilename() {
		return uploadFilename;
	}

	public void setUploadFilename(String uploadFilename) {
		this.uploadFilename = uploadFilename;
	}

	public CrmCourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CrmCourseType courseType) {
		this.courseType = courseType;
	}
	
	
	
}
