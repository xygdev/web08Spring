package com.xinyiglass.springSample.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xinyiglass.springSample.dao.LovDao;

import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

public class LovDaoImpl extends DevJdbcDaoSupport implements LovDao {
	
	public SqlResultSet countByJobName(String jobname) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_JBO_CRM_JOB WHERE JOB_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", jobname);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public SqlResultSet findForJobId(String jobname) throws Exception{
		String sql = "SELECT JOB_ID FROM XYG_JBO_CRM_JOB WHERE JOB_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", jobname);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public SqlResultSet countByDeptName(String deptname) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_JBO_CRM_DEPT WHERE DEPT_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", deptname);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	public SqlResultSet findForDeptId(String deptname) throws Exception{
		String sql = "SELECT DEPT_ID FROM XYG_JBO_CRM_DEPT WHERE DEPT_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", deptname);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	
	//Testing
	@SuppressWarnings("resource")
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	LovDao lDao= (LovDao)context.getBean("LovDao");
    	try{
    		String jobname="123";
    		SqlResultSet SRS=new SqlResultSet();
    		SRS=lDao.countByJobName(jobname);
    		System.out.println(SRS.getColName()[0]);
    		System.out.println(SRS.getResultSet().get(0)[0]);
    	}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
