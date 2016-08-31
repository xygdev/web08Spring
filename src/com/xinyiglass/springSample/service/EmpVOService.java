package com.xinyiglass.springSample.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;
import xygdev.commons.springjdbc.DevTransactionPub;
import xygdev.commons.sqlStmt.SqlStmtPub;

import com.xinyiglass.springSample.dao.EmpVODao;
import com.xinyiglass.springSample.entity.EmpVO;

@Service
@Transactional(rollbackFor=Exception.class)//指定checked的异常Exception也要回滚！
public class EmpVOService {
	
	@Autowired
	EmpVODao eDao;
	@Autowired
	PagePub pagePub;
	
	public PlsqlRetValue insert(EmpVO e) throws Exception{
		PlsqlRetValue ret=eDao.insert(e);
		if(ret.getRetcode()!=0){
			DevTransactionPub.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	public PlsqlRetValue delete(Long empId) throws Exception{
		PlsqlRetValue ret=eDao.delete(empId);
		if(ret.getRetcode()!=0){
			DevTransactionPub.setRollbackOnly();//该事务必须要回滚！
		}
		return ret;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int countAll() throws Exception{
		return eDao.countAll();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public EmpVO findById(Long empId) throws Exception{
		return eDao.findById(empId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findByIdForJson(Long empId) throws Exception{
		return "{\"rows\":"+eDao.findByIdForResultSet(empId).toJsonStr()+"}";
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby,Date hire_date_f,Date hire_date_t,Long jobid,String empno_f,String empno_t,String fullname,String disableflag) throws Exception{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer sqlBuf=new StringBuffer();
		sqlBuf.append("select * from XYG_JBO_CRM_EMP_V A where 1=1");
		sqlBuf.append(SqlStmtPub.getAndStmt("HIRE_DATE", hire_date_f,hire_date_t,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("JOB_ID", jobid,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("EMP_NUMBER", empno_f,empno_t,paramMap));
		sqlBuf.append(SqlStmtPub.getAndStmt("FULL_NAME", fullname,paramMap));
		if(disableflag!=null&&disableflag.equals("on")){
	        sqlBuf.append(" AND EXISTS(SELECT 1 FROM XYG_JBO_CRM_DEPT D WHERE (DISABLED_DATE IS NULL OR DISABLED_DATE > SYSDATE) AND D.DEPT_ID=A.DEPARTMENT_ID)");
		}
		sqlBuf.append(" ORDER BY "+orderby);
		return pagePub.qPageForJson(sqlBuf.toString(), paramMap, pageSize, pageNo, goLastPage);
	}
	
	//由于更新前要加锁，另外要验证数据是否被别人改了，所以要lockEmp和updEmp
	/*更新的逻辑说明：-->控制点在用户点下确认更新按钮。
	   在用户预更新的时候，整笔记录会自动加到session变量里面。这个就是以后更新时候的数据的对比源。
	   然后，用户在确认更新的时候，再对比预更新的时候的数据和现在的数据库的数据对比，是否有变更。
	   如果有变更，则提示记录已经被更新，要刷新，确认最新的数据，才允许做更新！
	   2016.8.12深度测试：
	   加锁：1个事务如果加锁了(FOR UPDATE NOWAIT)，则另外一个事务会自动等这个事务释放锁才会往下执行。
	   测试结果：
	   事务1，加锁，等待30秒。
	   事务2启动，但是会一直停留卡在lock，没有往下执行。它等待事务1的处理。需要注意的是，貌似程序无法定位到Lock的记录，而是以表来计算的。
	   事务1，30秒之后，更新完毕。
	   事务2，lock继续往下执行
	   事务2，等待30秒，执行完毕。
	   结论：这个效果还是不错的。虽然没我想的另外一个效果的好(直接报错资源在忙的提示)，但是也不错了。至少锁住了资源，没并发的问题！
	   ---
	   经过测试，如果是非LOCK的处理，则直接执行了。不会有任何的等待。
	*/
	public PlsqlRetValue update(EmpVO lockEmpVO,EmpVO updateEmpVO) throws Exception
	{   	
		//加锁，验证数据是否变更了
		//System.out.println("begin lock..");
		PlsqlRetValue ret=eDao.lock(lockEmpVO);
		//System.out.println("--LOCK:"+lockEmpVO.getEmpId()+ret.toJsonStr());
		//Thread.sleep(30000);
		if(ret.getRetcode()==0){
			ret=eDao.update(updateEmpVO);
		}
		/*PlsqlRetValue ret=eDao.update(updateEmpVO);//更新的会直接跑完了。
		System.out.println("--UPDATE:"+updateEmpVO.getEmpId()+ret.toJsonStr());
		Thread.sleep(30000);*/
		//如果返回值<>0那是必须要回滚的！
		if(ret.getRetcode()!=0){
			DevTransactionPub.setRollbackOnly();//该事务必须要回滚！
		}
		//Assert.isTrue(lockRet.getRetcode()==0, "lock处理失败！信息："+lockRet.getErrbuf());
		return ret;
	}
	
}
