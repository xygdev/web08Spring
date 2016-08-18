package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.page.PagePub;
import xygdev.commons.sqlStmt.SqlStmtPub;

//正常应该是写在dao层的。Lov特殊处理，因为只有一个SQL就可以得到要的内容，所以先写在Service层。
//而且Lov的数据访问的重用性不是很高，以查询和验证数据为主，所以确实可以不用封装dao。
//关于分页处理的必须要封装。基本的逻辑是：给一个SQL，还有相关的条件，返回页的json数据结果-->2016.8.9已经完成
@Service
//不需要事务管理的(只查询的)方法，可以提高效率。
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class LovService {

	@Autowired
	PagePub pagePub;
	
	public String findJobForPage(int pageSize,int pageNo,boolean goLastPage,Long jobId,String jobName) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT JOB_ID,JOB_NAME FROM XYG_JBO_CRM_JOB WHERE DISABLED_DATE IS NULL");
		sqlBuf.append(SqlStmtPub.getAndStmt("JOB_ID", jobId,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("JOB_NAME", jobName,paramMap));
		sqlBuf.append(" ORDER BY JOB_ID ");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	public String findDeptForPage(int pageSize,int pageNo,boolean goLastPage,String deptName,String deptTypeDesc,String managerName) throws Exception{
		StringBuffer sqlBuf=new StringBuffer();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		sqlBuf.append("SELECT DEPT_ID,DEPT_NAME,DEPT_TYPE_DESC"
							+ ",MANAGER_NAME,LOCATION_NAME,ENABLE_DATE,REMARK "
							+ " FROM XYG_JBO_CRM_DEPT_V "
		                    + " WHERE SYSDATE BETWEEN  ENABLE_DATE AND NVL(DISABLED_DATE,SYSDATE+1) ");
		sqlBuf.append(SqlStmtPub.getAndStmt("DEPT_NAME", deptName,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("DEPT_TYPE_DESC", deptTypeDesc,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("MANAGER_NAME", managerName,paramMap));
		sqlBuf.append(" ORDER BY 1 ");
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	/*2016.8.17
	 * 需要同步修改前端JS功能。因为json变了：由
	 * 	sb.append("{"); 
		if(SRS.getResultSet().size()>0){
			sb.append("\"EXISTS\": \"Y\",\"rows\":"); 
		    sb.append("{");
		    sb.append("\""+SRS.getColName()[0]+"\":\"" + SRS.getResultSet().get(0)[0]); 	
			sb.append("\"}");
		}else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"rows\":{}"); 
	    }
		sb.append("}");
		变为：直接就是：{"rows":[xxxx]}
	 */
	public String countByJobName(String jobName) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_JBO_CRM_JOB WHERE JOB_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", jobName);
		return "{\"rows\":"+pagePub.getDevJdbcTemplate().queryForResultSet(sql, paramMap).toJsonStr()+"}";
	}
	
	public String findForJobId(String jobName) throws Exception{
		String sql = "SELECT JOB_ID FROM XYG_JBO_CRM_JOB WHERE JOB_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", jobName);
		return "{\"rows\":"+pagePub.getDevJdbcTemplate().queryForResultSet(sql, paramMap).toJsonStr()+"}";
	}
	
	public String countByDeptName(String deptName) throws Exception{
		String sql = "SELECT COUNT(*) COUNT FROM XYG_JBO_CRM_DEPT WHERE DEPT_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", deptName);
		return "{\"rows\":"+pagePub.getDevJdbcTemplate().queryForResultSet(sql, paramMap).toJsonStr()+"}";
	}
	
	public String findForDeptId(String deptName) throws Exception{
		String sql = "SELECT DEPT_ID FROM XYG_JBO_CRM_DEPT WHERE DEPT_NAME=:1 AND DISABLED_DATE IS NULL";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", deptName);
		return "{\"rows\":"+pagePub.getDevJdbcTemplate().queryForResultSet(sql, paramMap).toJsonStr()+"}";
	}
}
