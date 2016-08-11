package com.xinyiglass.springSample.dao.impl;


import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xygdev.commons.entity.PlsqlRetValue;
import xygdev.commons.entity.SqlResultSet;
import xygdev.commons.springjdbc.DevJdbcDaoSupport;

import com.xinyiglass.springSample.entity.EmpVO;
import com.xinyiglass.springSample.util.Constant;
import com.xinyiglass.springSample.dao.EmpVODao;

public class EmpVODaoImpl extends DevJdbcDaoSupport implements EmpVODao {
	public void log(String log){
		if (Constant.DEBUG_MODE){
			System.out.println(log);
		}
	}
	public long getEmpIdNextSeq(){
		return this.getDevJdbcTemplate().queryForLong("SELECT XYG_JBO_CRM_EMP_S.NEXTVAL FROM DUAL");
	}
	public PlsqlRetValue insert(EmpVO e) throws Exception{
		String sql = "Declare "
				+	"  l_emp_id number; "
				+	"begin "
				+ "  XYG_JBO_CRM_EMP_PKG.INSERT_EMP( "
				+ "  :1"//P_EMP_ID  IN OUT NUMBER
				+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
				+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                + " ,:4"//,P_LAST_NAME IN VARCHAR2
                + " ,:5"//,P_FULL_NAME IN VARCHAR2
                + " ,:6"//,P_SEX IN      VARCHAR2
                + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                + " ,:15"//,P_ENABLE_DATE IN DATE
                + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", e.getEmpId());
		paramMap.put("2", e.getEmpNumber());
		paramMap.put("3", e.getFirstName());
		paramMap.put("4", e.getLastName());
		paramMap.put("5", e.getFullName());
		paramMap.put("6", e.getSex());
		paramMap.put("7", e.getEmail());
		paramMap.put("8", e.getPhoneNumber());
		paramMap.put("9", e.getHireDate());
		paramMap.put("10", e.getJobId());
		paramMap.put("11", e.getSalary());
		paramMap.put("12", e.getManagerId());
		paramMap.put("13", e.getDepartmentId());
		paramMap.put("14", e.getUserId());
		paramMap.put("15", e.getEnableDate());
		paramMap.put("16", e.getDisabledDate());
		paramMap.put("17", e.getRemark());
		Map<String,Integer> outParamMap=new HashMap<String,Integer>();
		outParamMap.put("1", Types.BIGINT);//定义输出参数
		Map<String,Object> outValueMap=this.getDevJdbcTemplate().execute(sql, paramMap, outParamMap);
		Long empId = (Long) outValueMap.get("1");
		//PlsqlRetValue prv=this.getDevJdbcTemplate().executeForPrv(sql, paramMap);
		//Long empId = Long.parseLong(prv.getParam1());
		log("INSERT Emp ID:"+empId);
		//empId=null;
		PlsqlRetValue ret=new PlsqlRetValue();
		if(empId==null){
			ret.setRetcode(2);
			ret.setErrbuf("新增记录失败!");
		}else{
			ret.setRetcode(0);
			ret.setErrbuf("新增记录成功！Emp ID为:"+empId);
		}
		return ret;
	}
	
	public PlsqlRetValue delete(Long empId) throws Exception{
		String sql = "Declare "
				+	"  l_emp_id number; "
				+	"begin "
				+ "  XYG_JBO_CRM_EMP_PKG.DELETE_EMP("
				+ "  :1"//P_EMP_ID IN NUMBER
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", empId);
		/*PlsqlRetValue ret=this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
		Assert.isTrue(ret.getRetcode()==0, "delete处理失败！信息："+ret.getErrbuf());*/
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
	}
	
	//lock
	public PlsqlRetValue lock(EmpVO e) throws Exception{
		String sql = "Declare "
					+	"  l_emp_id number; "
					+	"begin "
					+ "  XYG_JBO_CRM_EMP_PKG.LOCK_EMP( "
					+ "  :1"//P_EMP_ID IN NUMBER
					+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
					+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                    + " ,:4"//,P_LAST_NAME IN VARCHAR2
                    + " ,:5"//,P_FULL_NAME IN VARCHAR2
                    + " ,:6"//,P_SEX IN      VARCHAR2
                    + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                    + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                    + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                    + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                    + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                    + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                    + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                    + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                    + " ,:15"//,P_ENABLE_DATE IN DATE
                    + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                    + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
    				+ ",:"+PlsqlRetValue.RETCODE
    				+ ",:"+PlsqlRetValue.ERRBUF
					+ " ); "
					+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", e.getEmpId());
		paramMap.put("2", e.getEmpNumber());
		paramMap.put("3", e.getFirstName());
		paramMap.put("4", e.getLastName());
		paramMap.put("5", e.getFullName());
		paramMap.put("6", e.getSex());
		paramMap.put("7", e.getEmail());
		paramMap.put("8", e.getPhoneNumber());
		paramMap.put("9", e.getHireDate());
		paramMap.put("10", e.getJobId());
		paramMap.put("11", e.getSalary());
		paramMap.put("12", e.getManagerId());
		paramMap.put("13", e.getDepartmentId());
		paramMap.put("14", e.getUserId());
		paramMap.put("15", e.getEnableDate());
		paramMap.put("16", e.getDisabledDate());
		paramMap.put("17", e.getRemark());
		//this.getDevJdbcTemplate().execute(sql, paramMap);
		log("LOCK Emp ID:"+e.getEmpId());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
		//log("lock ret:"+ret);
		//Assert.isTrue(ret.getRetcode()==0, "lock处理失败！信息："+ret.getErrbuf());
	}
	
	public PlsqlRetValue update(EmpVO e) throws Exception{
		String sql = "Declare "
				+	"  l_emp_id number; "
				+	"begin "
				+ "  XYG_JBO_CRM_EMP_PKG.UPDATE_EMP("
				+ "  :1"//P_EMP_ID IN NUMBER
				+ " ,:2"//,P_EMP_NUMBER IN VARCHAR2 DEFAULT NULL
				+ " ,:3"//,P_FIRST_NAME IN VARCHAR2
                + " ,:4"//,P_LAST_NAME IN VARCHAR2
                + " ,:5"//,P_FULL_NAME IN VARCHAR2
                + " ,:6"//,P_SEX IN      VARCHAR2
                + " ,:7"//,P_EMAIL IN    VARCHAR2 DEFAULT NULL
                + " ,:8"//,P_PHONE_NUMBER IN VARCHAR2 DEFAULT NULL
                + " ,:9"//,P_HIRE_DATE IN DATE DEFAULT NULL
                + " ,:10"//,P_JOB_ID IN   NUMBER DEFAULT NULL
                + " ,:11"//,P_SALARY IN   NUMBER DEFAULT NULL
                + " ,:12"//,P_MANAGER_ID IN NUMBER DEFAULT NULL
                + " ,:13"//,P_DEPARTMENT_ID IN NUMBER DEFAULT NULL
                + " ,:14"//,P_USER_ID IN  NUMBER DEFAULT NULL
                + " ,:15"//,P_ENABLE_DATE IN DATE
                + " ,:16"//,P_DISABLED_DATE IN DATE DEFAULT NULL
                + " ,:17"//,P_REMARK IN   VARCHAR2 DEFAULT NULL
				+ ",:"+PlsqlRetValue.RETCODE
				+ ",:"+PlsqlRetValue.ERRBUF
				+ " ); "
				+ "end;";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("1", e.getEmpId());
		paramMap.put("2", e.getEmpNumber());
		paramMap.put("3", e.getFirstName());
		paramMap.put("4", e.getLastName());
		paramMap.put("5", e.getFullName());
		paramMap.put("6", e.getSex());
		paramMap.put("7", e.getEmail());
		paramMap.put("8", e.getPhoneNumber());
		paramMap.put("9", e.getHireDate());
		paramMap.put("10", e.getJobId());
		paramMap.put("11", e.getSalary());
		paramMap.put("12", e.getManagerId());
		paramMap.put("13", e.getDepartmentId());
		paramMap.put("14", e.getUserId());
		paramMap.put("15", e.getEnableDate());
		paramMap.put("16", e.getDisabledDate());
		paramMap.put("17", e.getRemark());
		log("UPDATE Emp ID:"+e.getEmpId());
		return this.getDevJdbcTemplate().executeForRetValue(sql, paramMap);
		//log("update ret:"+ret);
		//Assert.isTrue(ret.getRetcode()==0, "update处理失败！信息："+ret.getErrbuf());
	}

	public int countAll() throws Exception{
		String sql="select count(*) count from XYG_JBO_CRM_EMP_V";
		return this.getDevJdbcTemplate().queryForInt(sql);	
	}
	
	public EmpVO findById(Long empId) throws Exception{
		/*if (empId==null){
			log("empId is null!");
			return null;
		}*/
		String sql = "select * from XYG_JBO_CRM_EMP_V where emp_id = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", empId);
		return this.getDevJdbcTemplate().queryForObject(sql, paramMap, new EmpVO());
	}
	
	public SqlResultSet findByIdForResultSet(Long empId) throws Exception{
		String sql = "select * from XYG_JBO_CRM_EMP_V where emp_id = :1";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", empId);
		return this.getDevJdbcTemplate().queryForResultSet(sql, paramMap);
	}
	
	/*//下面的2个方法不再使用
	public StringBuffer findBySQL(String sql,int pagesize,int output,int pagemax,int pagemin) throws Exception
	public List<EmpVO> findByCondition(String condition,List<Integer> para,List<String> paraVal) throws Exception
	*/

	//测试专用
	@SuppressWarnings("resource")
	public static void main(String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	EmpVODao eDao= (EmpVODao)context.getBean("EmpVODao");
		try {
			EmpVO e=eDao.findById(1080L);
			System.out.println("QUERY:"+e);
			//e.setEmpId(eDao.getEmpIdNextSeq());
			e.setEmpNumber("");
			e.setFirstName("firstName");
			e.setLastName("lastName");
			//long newEmpId=eDao.insert(e);
			//System.out.println("INSERT:"+e);
			//eDao.delete(newEmpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
