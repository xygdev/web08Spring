package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.SqlResultSet;

public interface LovDao {
	/**
	 * 
	 **/
	public SqlResultSet countByJobName(String jobname) throws Exception;
	/**
	 * 
	 **/
	public SqlResultSet findForJobId(String jobname) throws Exception;
	/**
	 * 
	 **/
	public SqlResultSet countByDeptName(String deptname) throws Exception;
	/**
	 * 
	 **/
	public SqlResultSet findForDeptId(String deptname) throws Exception;

}
