package com.xinyiglass.springSample.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EmpVO implements FactoryBean,RowMapper<EmpVO>, Cloneable {
	   private String RowId;
	   private Long EmpId;
	   private String EmpNumber;
	   private String FirstName;
	   private String LastName;
	   private String FullName;
	   private String Sex;
	   private String SexDesc;
	   private String Email;
	   private String PhoneNumber;
	   private Date HireDate;
	   private Long JobId;
	   private String JobName;
	   private Double Salary;
	   private Long ManagerId;
	   private String ManagerFullName;
	   private Long DepartmentId;
	   private String DeptName;
	   private String DeptType;
	   private String DeptTypeDesc;
	   private String LocationName;
	   private Long UserId;
	   private Timestamp EnableDate;
	   private Timestamp DisabledDate;
	   private String Remark;
	   private Long CreatedBy;
	   private Timestamp CreationDate;
	   private Long LastUpdatedBy;
	   private Timestamp LastUpdateDate;
	   private Long LastUpdateLogin;
	   //GET&SET Method
	   public String getRowId() {
	      return RowId;
	   }
	   public void setRowId(String RowId) {
	      this.RowId = RowId;
	   }
	   public Long getEmpId() {
	      return EmpId;
	   }
	   public void setEmpId(Long EmpId) {
	      this.EmpId = EmpId;
	   }
	   public String getEmpNumber() {
	      return EmpNumber;
	   }
	   public void setEmpNumber(String EmpNumber) {
	      this.EmpNumber = EmpNumber;
	   }
	   public String getFirstName() {
	      return FirstName;
	   }
	   public void setFirstName(String FirstName) {
	      this.FirstName = FirstName;
	   }
	   public String getLastName() {
	      return LastName;
	   }
	   public void setLastName(String LastName) {
	      this.LastName = LastName;
	   }
	   public String getFullName() {
	      return FullName;
	   }
	   public void setFullName(String FullName) {
	      this.FullName = FullName;
	   }
	   public String getSex() {
	      return Sex;
	   }
	   public void setSex(String Sex) {
	      this.Sex = Sex;
	   }
	   public String getSexDesc() {
	      return SexDesc;
	   }
	   public void setSexDesc(String SexDesc) {
	      this.SexDesc = SexDesc;
	   }
	   public String getEmail() {
	      return Email;
	   }
	   public void setEmail(String Email) {
	      this.Email = Email;
	   }
	   public String getPhoneNumber() {
	      return PhoneNumber;
	   }
	   public void setPhoneNumber(String PhoneNumber) {
	      this.PhoneNumber = PhoneNumber;
	   }
	   public Date getHireDate() {
	      return HireDate;
	   }
	   public void setHireDate(Date HireDate) {
	      this.HireDate = HireDate;
	   }
	   public Long getJobId() {
	      return JobId;
	   }
	   public void setJobId(Long JobId) {
	      this.JobId = JobId;
	   }
	   public String getJobName() {
	      return JobName;
	   }
	   public void setJobName(String JobName) {
	      this.JobName = JobName;
	   }
	   public Double getSalary() {
	      return Salary;
	   }
	   public void setSalary(Double Salary) {
	      this.Salary = Salary;
	   }
	   public Long getManagerId() {
	      return ManagerId;
	   }
	   public void setManagerId(Long ManagerId) {
	      this.ManagerId = ManagerId;
	   }
	   public String getManagerFullName() {
	      return ManagerFullName;
	   }
	   public void setManagerFullName(String ManagerFullName) {
	      this.ManagerFullName = ManagerFullName;
	   }
	   public Long getDepartmentId() {
	      return DepartmentId;
	   }
	   public void setDepartmentId(Long DepartmentId) {
	      this.DepartmentId = DepartmentId;
	   }
	   public String getDeptName() {
	      return DeptName;
	   }
	   public void setDeptName(String DeptName) {
	      this.DeptName = DeptName;
	   }
	   public String getDeptType() {
	      return DeptType;
	   }
	   public void setDeptType(String DeptType) {
	      this.DeptType = DeptType;
	   }
	   public String getDeptTypeDesc() {
	      return DeptTypeDesc;
	   }
	   public void setDeptTypeDesc(String DeptTypeDesc) {
	      this.DeptTypeDesc = DeptTypeDesc;
	   }
	   public String getLocationName() {
	      return LocationName;
	   }
	   public void setLocationName(String LocationName) {
	      this.LocationName = LocationName;
	   }
	   public Long getUserId() {
	      return UserId;
	   }
	   public void setUserId(Long UserId) {
	      this.UserId = UserId;
	   }
	   public Timestamp getEnableDate() {
	      return EnableDate;
	   }
	   public void setEnableDate(Timestamp EnableDate) {
	      this.EnableDate = EnableDate;
	   }
	   public Timestamp getDisabledDate() {
	      return DisabledDate;
	   }
	   public void setDisabledDate(Timestamp DisabledDate) {
	      this.DisabledDate = DisabledDate;
	   }
	   public String getRemark() {
	      return Remark;
	   }
	   public void setRemark(String Remark) {
	      this.Remark = Remark;
	   }
	   public Long getCreatedBy() {
	      return CreatedBy;
	   }
	   public void setCreatedBy(Long CreatedBy) {
	      this.CreatedBy = CreatedBy;
	   }
	   public Timestamp getCreationDate() {
	      return CreationDate;
	   }
	   public void setCreationDate(Timestamp CreationDate) {
	      this.CreationDate = CreationDate;
	   }
	   public Long getLastUpdatedBy() {
	      return LastUpdatedBy;
	   }
	   public void setLastUpdatedBy(Long LastUpdatedBy) {
	      this.LastUpdatedBy = LastUpdatedBy;
	   }
	   public Timestamp getLastUpdateDate() {
	      return LastUpdateDate;
	   }
	   public void setLastUpdateDate(Timestamp LastUpdateDate) {
	      this.LastUpdateDate = LastUpdateDate;
	   }
	   public Long getLastUpdateLogin() {
	      return LastUpdateLogin;
	   }
	   public void setLastUpdateLogin(Long LastUpdateLogin) {
	      this.LastUpdateLogin = LastUpdateLogin;
	   }
	   
	   @Override  
	    public Object clone() {  
	        EmpVO empVO = null;  
	        try{  
	        	empVO = (EmpVO)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return empVO;  
	    } 
	   
	@Override
    public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpVO empVO = new EmpVO();
		empVO.setEmpId(rs.getLong("emp_id"));
		empVO.setEmpNumber(rs.getString("emp_number"));
		empVO.setFirstName(rs.getString("first_name"));
		empVO.setLastName(rs.getString("last_name"));
		empVO.setFullName(rs.getString("full_name"));
		empVO.setSex(rs.getString("sex"));
		empVO.setSexDesc(rs.getString("sex_desc"));
		empVO.setEmail(rs.getString("email"));
		empVO.setPhoneNumber(rs.getString("phone_number"));
		empVO.setHireDate(rs.getDate("hire_date"));
		empVO.setJobId(rs.getObject("job_id")==null?null:rs.getLong("job_id"));
		empVO.setJobName(rs.getString("job_name"));
		empVO.setSalary(rs.getObject("salary")==null?null:rs.getDouble("salary"));
		empVO.setManagerId(rs.getObject("manager_id")==null?null:rs.getLong("manager_id"));
		empVO.setManagerFullName(rs.getString("manager_full_name"));
		empVO.setDepartmentId(rs.getObject("department_id")==null?null:rs.getLong("department_id"));
		empVO.setDeptName(rs.getString("dept_name"));
		empVO.setDeptType(rs.getString("dept_type"));
		empVO.setDeptTypeDesc(rs.getString("dept_type_desc"));
		empVO.setLocationName(rs.getString("location_name"));
		empVO.setUserId(rs.getObject("user_id")==null?null:rs.getLong("user_id"));
		empVO.setEnableDate(rs.getTimestamp("enable_date"));
		empVO.setDisabledDate(rs.getTimestamp("disabled_date"));
		empVO.setRemark(rs.getString("remark"));
		empVO.setCreatedBy(rs.getLong("created_by"));
		empVO.setCreationDate(rs.getTimestamp("creation_date"));
		empVO.setLastUpdatedBy(rs.getLong("last_updated_by"));
		empVO.setLastUpdateDate(rs.getTimestamp("last_update_date"));
		empVO.setLastUpdateLogin(rs.getLong("last_update_login"));
        return empVO;
    }
	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String toString(){
		return "EmpId:"+this.getEmpId()+",EmpNumber:"+this.getEmpNumber()+",FullName:"+this.getFullName();
	}
}
