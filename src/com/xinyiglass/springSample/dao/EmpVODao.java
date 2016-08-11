package com.xinyiglass.springSample.dao;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;

import com.xinyiglass.springSample.entity.EmpVO;

public interface EmpVODao {
	public long getEmpIdNextSeq();
	public PlsqlRetValue insert(EmpVO e) throws Exception;
	public PlsqlRetValue delete(Long empId) throws Exception;
	public PlsqlRetValue lock(EmpVO e) throws Exception;
	public PlsqlRetValue update(EmpVO e) throws Exception;
	public int countAll() throws Exception;
	public EmpVO findById(Long empId) throws Exception;
	public SqlResultSet findByIdForResultSet(Long empId) throws Exception;
}
