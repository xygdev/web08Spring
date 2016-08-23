package com.xinyiglass.springSample.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xygdev.commons.page.PagePub;

//类似Lov的处理
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class ListService {

	@Autowired
	PagePub pagePub;
	
	public String findForSex() throws Exception{
		String sql = "SELECT MEANING DISPLAY,LOOKUP_CODE VALUE"
				+ " FROM XYG_JBO_CRM_LOOKUP  "
				+ " WHERE LOOKUP_TYPE = 'XYG_JBO_CRM_EMP_SEX' "
				+ " AND LANGUAGE = 'ZHS' "
				+ " AND ENABLED_FLAG='Y' "
				+ " AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1) "
				+ " ORDER BY LOOKUP_CODE";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForCurtainType() throws Exception{
		String sql = "SELECT MEANING DISPLAY,LOOKUP_CODE VALUE"
				+ " FROM XYG_JBO_CRM_LOOKUP  "
				+ " WHERE LOOKUP_TYPE = 'XYG_JBO_CRM_CURTAIN_TYPE' "
				+ " AND LANGUAGE = 'ZHS' "
				+ " AND ENABLED_FLAG='Y' "
				+ " AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1) "
				+ " ORDER BY LOOKUP_CODE";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		return pagePub.qSqlForJson(sql, paramMap);
	}
	
	public String findForProjectStatus() throws Exception{
		String sql = "SELECT MEANING DISPLAY12,LOOKUP_CODE VALUE"
				+ " FROM XYG_JBO_CRM_LOOKUP  "
				+ " WHERE LOOKUP_TYPE = 'XYG_JBO_CRM_PROJECT_STATUS' "
				+ " AND LANGUAGE = 'ZHS' "
				+ " AND ENABLED_FLAG='Y' "
				+ " AND SYSDATE BETWEEN START_DATE_ACTIVE AND NVL(END_DATE_ACTIVE,SYSDATE+1) "
				+ " ORDER BY LOOKUP_CODE";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		return pagePub.qSqlForJson(sql, paramMap);
	}
}
