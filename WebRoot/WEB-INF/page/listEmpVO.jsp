<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"
	      import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>分页查询</title>
    <base href="<%=basePath%>"> 
    <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<link rel="stylesheet" href="plugin/css/font-awesome.min.css">
	<link rel="stylesheet" href="plugin/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="plugin/css/style.css">
	<script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>	
	<script src="plugin/js/jquery-ui.min.js"></script>
  </head> 
  <body>
    <div id="container">
  
      <!-- 数据加载动画 start -->
  	  <div class="ajax_loading">
        <i class="fa fa-spinner fa-pulse fa-4x" style="color:white"></i>
      </div>
      <!-- 数据加载动画 end -->
    
      <!-- 主表格区域 start -->
      <div class="table">
        <table id="tb">
          <tr>
            <th class="EMP_ID" data-column="db">id</th>
     	    <th class="EMP_NUMBER" data-column="db">工号</th>
     	    <th class="FULL_NAME" data-column="db">姓名</th>
     	    <th class="SEX_DESC" data-column="db">性别</th>
     	    <th class="PHONE_NUMBER" data-column="db">电话</th>
     	    <th class="SALARY" data-column="db">薪酬</th>
     	    <th class="HIRE_DATE" data-column="db">雇用日期</th>
     	    <th class="JOB_NAME" data-column="db">职位</th>
     	    <th class="DEPT_NAME" data-column="db">部门</th>
     	    <th class="ACTION" data-column="normal">操作</th> 
     	    <th style="display:none" data-column="normal">&nbsp;</th>
     	  </tr>
     	  <tr>
     	    <td class="EMP_ID" data-column="db"></td>
     	    <td class="EMP_NUMBER" data-column="db"></td>
		    <td class="FULL_NAME" data-column="db"></td>
     	    <td class="SEX_DESC" data-column="db"></td>
     	    <td class="PHONE_NUMBER" data-column="db"></td>
     	    <td class="SALARY" data-column="db"></td>
     	    <td class="HIRE_DATE" data-column="db"></td>
     	    <td class="JOB_NAME" data-column="db"></td>
     	    <td class="DEPT_NAME" data-column="db"></td>
     	    <td class="ACTION" data-column="normal">
     	      <i class="fa fa-trash-o delete pointer" data-crudtype="del" data-delurl="emp/delete.do" data-jsontype="table" data-delparam=["EMP_ID",".EMP_ID"] data-crudsetting='{"loading":".ajax_loading","refresh":"#refresh","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","delmsg":".FULL_NAME","tablename":"#tb"}'></i>
     	      <i class="fa fa-pencil fa-fw update pointer" data-reveal-id="uf" data-dismissmodalclass="close-update-frame" data-crudtype="pre-update" data-preupdateurl="emp/preUpdate.do" data-updateparam=["EMP_ID",".EMP_ID"] data-crudsetting='{"loading":".ajax_loading"}'></i>
     	    </td>
     	    <td style="display:none" data-column="normal">&nbsp;</td>
     	  </tr>
       </table>
     </div>
     <!-- 主表格区域 end -->
   
     <!-- 主表格按钮区域 start -->
     <div class="table_button">
       <div class="setting">
         <i class="fa fa-cog fa-2x pointer" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
       </div>
       <div class="setting">
         <i id='refresh' class="fa fa-refresh fa-2x pointer" data-pagetype='refresh' data-orderby="emp_id" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
       </div>
       <div id="setting">
         <div class="title">
           <span>设置</span>
           <a class="close-setting">&#215;</a>
         </div>  
         <div class="line"></div>
         <div class="content">
           <ul>
             <li><a class="pointer" data-rowdefine="init" data-reveal-id="row-def">定义列</a></li>
             <li><a class="pointer">显示行数</a>
               <ul>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","closebutton":".close-setting","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="5"></i>
                   5</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","closebutton":".close-setting","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                   <i class="fa fa-dot-circle-o" aria-hidden="true" data-value="10"></i>
                   10</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","closebutton":".close-setting","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="15"></i>
                   15</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","closebutton":".close-setting","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="25"></i>
                   25</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize"  data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","closebutton":".close-setting","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'>
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="50"></i>
                   50</a>
                 </li> 
               </ul>
             </li>
             <li><a class="pointer" data-ordertable="#tb" data-reveal-id="orderby">多维排序</a></li>
             <li><a class="pointer" data-config="init" data-reveal-id="config">个人配置</a></li>
           </ul>
         </div>      
       </div>
       <div class="arrow_S margin">
         <i id="last" class="fa fa-step-forward fa-2x pointer" data-pagetype="lastpage" data-orderby="emp_id" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
       </div>
       <div class="arrow_L">
         <i id="next" class="fa fa-chevron-circle-right fa-2x pointer" data-pagetype="nextpage" data-orderby="emp_id" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
       </div>
       <div class="pageRow">
       	 <span id="pageRow">1</span>
       </div>
       <div class="arrow_L">
         <i id="previous" class="fa fa-chevron-circle-left fa-2x pointer" data-pagetype="prevpage" data-orderby="emp_id" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
       </div>
       <div class="arrow_S">
         <i id="first" class="fa fa-step-backward fa-2x pointer" data-pagetype="firstpage" data-orderby="emp_id" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-pagesetting='{"loading":".ajax_loading","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#tb"}'></i>
       </div>
     </div>
     <!-- 主表格按钮区域 end --> 
   
     <!-- 定义列区域 start --> 
     <div id="row-def">
       <div class="title">
         <span class="title_name">定义列</span>
       </div>
	   <a class="close-reveal-modal">&#215;</a>
       <div class="line"></div>
       <div class="contain">
         <select class="option_frame" id="hide" multiple='multiple' title='隐藏'></select>
         <div class="button_frame">
     	   <button class="button" data-rowdefine="hide" >
     	     <i class="fa fa-angle-left fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="show">
     	     <i class="fa fa-angle-right fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="hide_all">
     	     <i class="fa fa-angle-double-left fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="show_all">
     	     <i class="fa fa-angle-double-right fa-2x"></i>
     	   </button>
         </div>
         <select class="option_frame" id="show" multiple='multiple' title='显示'></select>
         <div class="button_frame">
     	   <button class="button" data-rowdefine="up">
     	     <i class="fa fa-angle-up fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="down">
     	     <i class="fa fa-angle-down fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="top">
     	     <i class="fa fa-angle-double-up fa-2x"></i>
     	   </button>
     	   <button class="button" data-rowdefine="bottom">
     	     <i class="fa fa-angle-double-down fa-2x"></i>
     	   </button>
         </div>
       </div>
     </div>
     <!-- 定义列区域 end -->
    
     <!-- 多维排序区域 start -->
     <div id="orderby">
       <div class="title">
         <span class="title_name">多维排序</span>
       </div>
       <a class="close-reveal-modal">&#215;</a>
       <div class="line"></div>
       <div class="contain">
         <div class="item" id="col1">
           <span>排序一：</span>
           <select class="select"></select>
		   <input type="radio" name="col1" class="col1" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col1" class="col1" value="DESC" />DESC 
         </div>  
         <div class="item" id="col2">
           <span>排序二：</span>
           <select class="select"></select>
		   <input type="radio" name="col2" class="col2" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col2" class="col2" value="DESC" />DESC 
         </div> 
         <div class="item" id="col3">
           <span>排序三：</span>
           <select class="select"></select>
		   <input type="radio" name="col3" class="col3" checked="checked" value="ASC" />ASC 
           <input type="radio" name="col3" class="col3" value="DESC" />DESC 
         </div> 
       </div>
       <div class="footer">
         <button class='right pointer' data-order=true >排序</button>
       </div>
     </div>
     <!-- 多维排序区域 end -->
   
     <!-- 个人配置区域 start -->
     <div id="config">
       <div class="title">
         <span class="title_name">个人配置</span>
       </div>
       <a class="close-reveal-modal">&#215;</a>
       <div class="line"></div>
       <div class="contain" id="configtabs">
         <ul>
           <li><a href="#tabs-1">保存配置</a></li>
           <li><a href="#tabs-2">加载配置</a></li>
         </ul>
         <div id="tabs-1" class='tab'>
           <div class="item" style="text-indent:3rem">
             <label class="pointer" for="USER_INTERACT_NAME">自定义名称：</label>
             <input type="text" name="USER_INTERACT_NAME" id="USER_INTERACT_NAME">
           </div>
           <div class="item" style="text-indent:6rem">
             <label class="pointer" for="DESCRIPTION">描述：</label>
             <input type="text" name="DESCRIPTION" id="DESCRIPTION">
           </div>
           <div class="item" style="text-indent:6rem">
             <label class="pointer" for="PUBLIC_FLAG">共享</label>
             <input type="checkbox" name="PUBLIC_FLAG" id="PUBLIC_FLAG">
             <label class="pointer" for="DEFAULT_FLAG">默认</label>
             <input type="checkbox" name="DEFAULT_FLAG" id="DEFAULT_FLAG">
             <label class="pointer" for="AUTOQUERY_FLAG">自动查询</label>
             <input type="checkbox" name="AUTOQUERY_FLAG" id="AUTOQUERY_FLAG"> 
           </div>
           <div class="item">
             <button class='pointer' data-config="save" >保存</button>
           </div>
         </div>
         <div id="tabs-2" class='tab'>
           <div class="item" style="text-indent:6rem;margin:50px auto 60px">
             <label class="pointer" for="loding_format">选择配置:</label>
             <select id="loding_format"></select>
           </div>
           <div class="item">
             <button class='pointer' data-config="load">加载</button>
           </div>
         </div>
       </div>
     </div>
     <!-- 个人配置区域 end -->
   
     <!-- 更新区域 start -->
     <div id='uf' class='update_frame'>     
       <div class='title'>      
         <span><i class="fa fa-user fa-1x" aria-hidden="true"></i>更新员工</span>
       </div>
       <a class="close-update-frame">&#215;</a>
       <div class='line'></div>
       <div class='content'>
         <form id='updateData'>
           <input type='hidden' id='EMP_ID'>
           <label for='EMP_NUMBER' class='left'>工号</label>
           <input type='text' id='EMP_NUMBER' name='EMP_NUMBER' class='left'>
           <label for='FULL_NAME' class='left'>全名</label>
           <input type='text' readonly='readonly' id='FULL_NAME' name='FULL_NAME' class='left'>
           <label for='SEX' class='left'>性别</label>
           <select class='left' id='SEX' name='SEX' >
             <option value='M'>男</option>
             <option value='F'>女</option>
             <option value='U'>不明</option>
           </select>       
           <label for='PHONE_NUMBER' class='left'>电话</label>
           <input type='text' id='PHONE_NUMBER' name='PHONE_NUMBER' class='left'>
           <label for='SALARY' class='left'>薪酬</label>
           <input type='text' id='SALARY' name='SALARY' class='left'>
           <label for='HIRE_DATE' class='left'>雇佣日期</label>
           <input type='text' id='HIRE_DATE' name='HIRE_DATE' class='left'>  
           <label for='JOB_NAME' class='left'>职位</label> 
           <input type='text' id='JOB_NAME' name='JOB_NAME' class='left short' >
           <input type='hidden' id='JOB_ID' name='JOB_ID'>
           <input type='button' id='JOB_ID' class='left button pointer' data-reveal-id="lov" data-animation="none" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职位查询" data-th=["职务编号","职务名称"] data-td=["JOB_ID","JOB_NAME"] data-selectname=["职务编号","职务名称"] data-selectvalue=["JOB_ID","JOB_NAME"] data-choose=[".JOB_ID",".JOB_NAME"] data-recid=["#JOB_ID","#JOB_NAME"] data-lovsetting='{"defaultquery":true,"queryval":"#lov_query_value","lovclass":".lov_frame","title":".lov_title","querybox":".querybox","contentbox":".contentbox","tablename":"job_query","prevpage":"#lov_prev","nextpage":"#lov_next","query":"#lov_query","pageno":"#lov_page_no","jsontype":"job","queryurl":"lov/getJobPage.do","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' value="...">
           <label for='DEPT_NAME' class='left'>部门</label>
           <input type='text' id='DEPT_NAME' name='DEPT_NAME' class='left short' readonly='readonly'>
           <input type='hidden' id='DEPARTMENT_ID' name='DEPARTMENT_ID'>
           <input type='button' id='' class='left button pointer' data-reveal-id="lov" data-animation="none" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="部门查询" data-th=["部门编号","部门名称","部门分类","部门主管","部门地点","启用日期","备注"] data-td=["DEPT_ID","DEPT_NAME","DEPT_TYPE_DESC","MANAGER_NAME","LOCATION_NAME","ENABLE_DATE","REMARK"] data-selectname=["部门名称","部门分类","部门主管"] data-selectvalue=["DEPT_NAME","DEPT_TYPE_DESC","MANAGER_NAME"] data-choose=[".DEPT_ID",".DEPT_NAME"] data-recid=["#DEPARTMENT_ID","#DEPT_NAME"] data-lovsetting='{"defaultquery":false,"queryval":"#lov_query_value","lovclass":".lov_frame","title":".lov_title","querybox":".querybox","contentbox":".contentbox","tablename":"job_query","prevpage":"#lov_prev","nextpage":"#lov_next","query":"#lov_query","pageno":"#lov_page_no","jsontype":"dept","queryurl":"lov/getDeptPage.do","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' value="...">
           <br style="clear:both"/>
           <label for='REMARK' class='left'>备注</label>
           <textarea class='left' id='REMARK' name='REMARK'></textarea>
         </form>  
       </div>
       <div class='foot'>       
         <button class="right update_confirm pointer" data-dismissmodalclass="close-update-frame" data-crudtype="update" data-updateurl="emp/update.do" data-queryurl="emp/getEmpPage.do" data-jsontype="table" data-updateparam=["EMP_ID","#EMP_ID"] data-crudsetting='{"loading":".ajax_loading","refresh":"#refresh","firstpage":"#first","lastpage":"#last","prevpage":"#previous","nextpage":"#next","setpagesize":".set_page_size","pagesize":"#page_size","pageno":"#page_no","pagerow":"#pageRow","tablename":"#uf"}'>确认更新</button>
       </div>
     </div> 
     <!-- 更新区域 start -->
   
     <!-- Lov区域 start -->
     <div class='lov_frame' id='lov'>
       <a class="close-lov">&#215;</a>
       <div class='lov_title'>
         <h1>此处填写标题</h1>
       </div>
       <div class='blackline'></div>
       <div class='querybox'>
         <div class="left">
           Search  
         </div>  
         <select id="lov_select" class="left">
         </select>  
         <input type="text" id="lov_query_value" class="left" >
         <div class="left lov_button">
           <i class="fa fa-search pointer" id="lov_query" aria-hidden="true" data-crudtype="query" data-crudsetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","lovid":"#lov","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}'></i>
         </div>
         <div class="left lov_button">
           <i class="fa fa-arrow-circle-left pointer" id="lov_prev" aria-hidden="true" data-pagetype="prevpage"  data-pagesetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}'></i>
         </div>
         <!-- <div id="lov_page_no">1</div>  -->
         <input type="text" id="lov_page_no" class="left" value="1" readonly='readonly'>
         <div class="left lov_button">
           <i class="fa fa-arrow-circle-right pointer" id="lov_next" aria-hidden="true" data-pagetype="nextpage"  data-pagesetting='{"prevpage":"#lov_prev","nextpage":"#lov_next","pagesize":"#lov_page_size","pageno":"#lov_page_no","tablename":"#job_query","typetd":"#lov_jsontype","urltd":"#lov_queryurl"}' ></i> 
         </div>                  
       </div>
       <div class='contentbox'>
         <table></table>
       </div>
       <div class='footer'></div>
     </div>
     <div class='lov-modal-bg'>
     </div>
     <!-- lov区域 end -->
   
     <!-- hidden属性存放区域 start -->
     <input type="hidden" id="page_size" value="10">
     <input type="hidden" id="page_no" value="1">
     <input type="hidden" id="lov_page_size" value="10">
     <input type="hidden" id="lov_queryurl">
     <input type="hidden" id="lov_jsontype"> 
     <input type="hidden" id="ORDER_BY" value="EMP_ID ASC">  
     <input type="hidden" id="USER_ID" value="123456">  
     <input type="hidden" id="INTERACT_CODE" value="USER_INFO"> 
     <input type="hidden" id="HEADER_ID" value=""> 
     <!-- hidden属性存放区域 end -->  
   </div>
     
    <script>       
         $(function() {
            //设置拖拽
    		$("#uf").draggable();
    		$("#row-def").draggable();
    		$("#orderby").draggable();
    		$("#config").draggable();
    		$("#lov").draggable();
    		//设置日期选择器
    		$("#HIRE_DATE").datepicker({
      			changeMonth: true,
     		    changeYear: true
   		    });
    		$("#HIRE_DATE").datepicker("option", "dateFormat","yy-mm-dd");
    		$("#HIRE_DATE").datepicker("option", "showAnim", "slide" );
    		//设置tab分栏
    		/**屏蔽<base>标签对JQuery UI tabs()的影响**/
    		$.fn.__tabs = $.fn.tabs;
			$.fn.tabs = function (a, b, c, d, e, f) {
				var base = window.location.href.replace(/#.*$/, '');
				$('ul>li>a[href^="#"]', this).each(function () {
					var href = $(this).attr('href');
					$(this).attr('href', base + href);
				});
				$(this).__tabs(a, b, c, d, e, f);
			};
    		$( "#configtabs").tabs();
    		/****/
    		$("#JOB_NAME").on("change",function(){
    		    alert("data changed");
    		});
  		 });
        
        jQuery.json={
        	getContent:function(data,JSONtype){  
        	    if(JSONtype=='table'){
        	        for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
                    	$('.EMP_ID',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].EMP_ID); 
                   	 	$('.EMP_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].EMP_NUMBER); 
                    	$('.FULL_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].FULL_NAME); 
                    	$('.SEX_DESC',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SEX_DESC); 
                    	$('.PHONE_NUMBER',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].PHONE_NUMBER); 
                    	$('.SALARY',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].SALARY);  
                    	$('.HIRE_DATE',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].HIRE_DATE);
                    	$('.JOB_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].JOB_NAME); 
                    	$('.DEPT_NAME',$('#tb tr:eq('+(i+1)+')')).html(data.rows[i].DEPT_NAME);       
                	}
                	$().crudListener();
                	$().revealListener();
        	    }else if(JSONtype=='job'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	            for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            	    $('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].JOB_ID);       	                    
       	            	    $('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].JOB_NAME);       	               	        
       	            	} 
        	        }       	            	    
        	    }else if(JSONtype=='dept'){
        	        if(pageMaxRow==0&&pageMinRow==0){
        	            console.log('no data');
        	        }else{
        	        	for(i=0;i<(pageMaxRow-pageMinRow+1);i++){
       	            		$('td:eq(0)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DEPT_ID);       	                    
       	            		$('td:eq(1)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DEPT_NAME); 
       	            		$('td:eq(2)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].DEPT_TYPE_DESC);       	                    
       	            		$('td:eq(3)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].MANAGER_NAME); 
       	            		$('td:eq(4)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].LOCATION_NAME);       	                    
       	            		$('td:eq(5)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].ENABLE_DATE);  
       	            		$('td:eq(6)',$('.contentbox tr:eq('+(i+1)+')')).html(data.rows[i].REMARK);     	               	        
       	            	}
       	            }   	    
        	    }   	            	   	
       	    },	
       	    getMSG:function(data){
       	        pageMinRow=parseInt(data.pageMinRow);
        	    pageMaxRow=parseInt(data.pageMaxRow);
        	    firstPageFlag=data.firstPageFlag;
        	    lastPageFlag=data.lastPageFlag;
        	   	totalPages=data.totalPages;
       	    },
       	    getUpdateJSON:function(data){   
       	        $('#EMP_ID').val(data.rows[0].EMP_ID); 	        
       	        $('#EMP_NUMBER').val(data.rows[0].EMP_NUMBER); 
       	        $('#FULL_NAME').val(data.rows[0].FULL_NAME);
       	        $('#SEX option[value='+data.rows[0].SEX+']').attr('selected',true);
       	        $('#SEX option[value='+data.rows[0].SEX+']').insertBefore($('option:eq(0)',$('#SEX')));
       	        $('#PHONE_NUMBER').val(data.rows[0].PHONE_NUMBER);
       	        $('#SALARY').val(data.rows[0].SALARY);
       	        $('#HIRE_DATE').val(data.rows[0].HIRE_DATE);
       	        $('#JOB_NAME').val(data.rows[0].JOB_NAME);
       	        $('#JOB_ID').val(data.rows[0].JOB_ID);
       	        $('#DEPT_NAME').val(data.rows[0].DEPT_NAME);
       	        $('#DEPARTMENT_ID').val(data.rows[0].DEPARTMENT_ID);
       	        $('#REMARK').val(data.rows[0].REMARK);
       	        $('#SEX option').attr('selected',false);      
       	    },
       	    setUpdateParam:function(){
       	        param=$('#updateData').serialize();
       	        console.log(param);
       	    },
       	    setQueryParam:function(){
       	        param=$('#lov_select option:selected').val();
       	        value=$('#lov_query_value').val();
       	        if(!value){
       	            param=null;
       	        }else{
       	            /***************************************
       	                 	因为%在url中为转义符，%25表达%字符本身，
       	                 	所以需要使用正则表达式全局替换，把字符串中
       	                 	所有的%替换为%25
       	            *****************************************/
       	            value=value.replace(/%/g,'%25');       
       	            param=param+'='+value;
       	        }
       	    }
       	}
    </script>
    <script type="text/javascript" src="plugin/js/jQuery.reveal.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.page.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.lov.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.crud.js"></script> 
    <script type="text/javascript" src="plugin/js/jQuery.rowdefine.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.irr.orderby.js"></script>	
    <script type="text/javascript" src="plugin/js/jQuery.irr.init.js"></script>	    
  </body>
</html>