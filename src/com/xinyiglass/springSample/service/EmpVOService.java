package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.page.PagePub;

import com.xinyiglass.springSample.dao.EmpVODao;
import com.xinyiglass.springSample.entity.EmpVO;

@Service @Transactional
public class EmpVOService {
	
	@Autowired
	EmpVODao eDao;
	@Autowired
	PagePub pagePub;
	
	public PlsqlRetValue insert(EmpVO e) throws Exception{
		return eDao.insert(e);
	}
	public PlsqlRetValue delete(Long empId) throws Exception{
		return eDao.delete(empId);
	}
	public PlsqlRetValue lock(EmpVO e) throws Exception{
		return eDao.lock(e);
	}
	public PlsqlRetValue update(EmpVO e) throws Exception{
		return eDao.update(e);
	}
	public int countAll() throws Exception{
		return eDao.countAll();
	}
	public EmpVO findById(Long empId) throws Exception{
		return eDao.findById(empId);
	}
	
	public String findByIdForJson(Long empId) throws Exception{
		return "{\"rows\":"+eDao.findByIdForResultSet(empId).toJsonStr()+"}";
	}
	
	public String findForPage(int pageSize,int pageNo,boolean goLastPage,String orderby) throws Exception{
		String sql="select * from XYG_JBO_CRM_EMP_V A WHERE 1=:1 order by "+orderby;
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", "1");
		return pagePub.qPageForJson(sql, paramMap, pageSize, pageNo, goLastPage);
	}
	
	//由于更新前要加锁，另外要验证数据是否被别人改了，所以要lockEmp和updEmp
	/*更新的逻辑说明：-->控制点在用户点下确认更新按钮。
	   在用户预更新的时候，整笔记录会自动加到session变量里面。这个就是以后更新时候的数据的对比源。
	   然后，用户在确认更新的时候，再对比预更新的时候的数据和现在的数据库的数据对比，是否有变更。
	   如果有变更，则提示记录已经被更新，要刷新，确认最新的数据，才允许做更新！
	*/
	public PlsqlRetValue update(EmpVO lockEmpVO,EmpVO updateEmpVO) throws Exception
	{   	
		//加锁，验证数据是否变更了
		PlsqlRetValue lockRet=eDao.lock(lockEmpVO);
		if(lockRet.getRetcode()!=0){
			return lockRet;
		}else{
			return eDao.update(updateEmpVO);
		}
		//Assert.isTrue(lockRet.getRetcode()==0, "lock处理失败！信息："+lockRet.getErrbuf());
		//return eDao.update(updateEmpVO);
	}
	
}
