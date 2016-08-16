package com.xinyiglass.springSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.dao.LovDao;

@Service
//不需要事务管理的(只查询的)方法，可以提高效率。
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class LovValidService {
	
	@Autowired
	LovDao lov;
	
	public StringBuffer countJobForPage(String jobname) throws Exception{
		SqlResultSet SRS=new SqlResultSet();
		System.out.println("JobName="+jobname);
		SRS=lov.countByJobName(jobname);
		System.out.println("Record="+SRS.getResultSet().get(0)[0]);
		StringBuffer sb=new StringBuffer();
		sb.append("{"); 
		if(SRS.getResultSet().size()>0){
			sb.append("\"EXISTS\": \"Y\",\"rows\":"); 
		    sb.append("{");
		    sb.append("\""+SRS.getColName()[0]+"\":\"" + SRS.getResultSet().get(0)[0]); 	
			sb.append("\"}");
		}else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"rows\":{}"); 
	    }
		sb.append("}");
	    System.out.println(sb);
		return sb;
	}
	
	public StringBuffer findJobIdForPage(String jobname) throws Exception{
		SqlResultSet SRS=new SqlResultSet();
		SRS=lov.findForJobId(jobname);
		StringBuffer sb=new StringBuffer();
		sb.append("{"); 
		if(SRS.getResultSet().size()>0){
			sb.append("\"EXISTS\": \"Y\",\"rows\":"); 
		    sb.append("{");
		    sb.append("\"ID\":\"" + SRS.getResultSet().get(0)[0]); 	
			sb.append("\"}");
		}else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"rows\":{}"); 
	    }
		sb.append("}");
	    System.out.println(sb);
		return sb;
	}
	
	public StringBuffer countDeptForPage(String deptname) throws Exception{
		SqlResultSet SRS=new SqlResultSet();
		System.out.println("DeptName="+deptname);
		SRS=lov.countByDeptName(deptname);
		System.out.println("Record="+SRS.getResultSet().get(0)[0]);
		StringBuffer sb=new StringBuffer();
		sb.append("{"); 
		if(SRS.getResultSet().size()>0){
			sb.append("\"EXISTS\": \"Y\",\"rows\":"); 
		    sb.append("{");
		    sb.append("\""+SRS.getColName()[0]+"\":\"" + SRS.getResultSet().get(0)[0]); 	
			sb.append("\"}");
		}else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"rows\":{}"); 
	    }
		sb.append("}");
	    System.out.println(sb);
		return sb;
	}
	
	public StringBuffer findDeptIdForPage(String deptname) throws Exception{
		SqlResultSet SRS=new SqlResultSet();
		SRS=lov.findForDeptId(deptname);
		StringBuffer sb=new StringBuffer();
		sb.append("{"); 
		if(SRS.getResultSet().size()>0){
			sb.append("\"EXISTS\": \"Y\",\"rows\":"); 
		    sb.append("{");
		    sb.append("\"ID\":\"" + SRS.getResultSet().get(0)[0]); 	
			sb.append("\"}");
		}else{//不存在数据
		    sb.append("\"EXISTS\": \"N\",\"rows\":{}"); 
	    }
		sb.append("}");
	    System.out.println(sb);
		return sb;
	}

}
