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
	<script src="http://192.168.88.123:8080/web08Spring/plugin/js/webSocket.js"></script>
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
        <table id="tb" data-table="EMP">
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
     	    <th style="display:none" data-column="hidden">&nbsp;</th>
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
     	      <i class="fa fa-trash-o delete pointer" data-crudtype="del" data-delurl="emp/delete.do" data-col="FULL_NAME" data-delmsg="是否要删除用户" data-delparam=["EMP_ID",".EMP_ID"]></i>
     	      <i class="fa fa-pencil fa-fw update pointer" data-reveal-id="uf" data-dismissmodalclass="close-update-frame" data-crudtype="pre-update" data-preupdateurl="emp/preUpdate.do" data-type="update" data-updateparam=["EMP_ID",".EMP_ID"]></i>
     	    </td>
     	    <td style="display:none" data-column="hidden">&nbsp;</td>
     	  </tr>
       </table>
     </div>
     <!-- 主表格区域 end -->
   
     <!-- 主表格按钮区域 start -->
     <div class="table_button" id="table" data-table="EMP">
       <div class="setting">
         <i class="fa fa-cog pointer" data-reveal-id="setting" data-dismissmodalclass="close-setting"></i>
       </div>
       <div class="setting">
         <i class="fa fa-user-plus pointer" data-reveal-id="uf" data-dismissmodalclass="close-update-frame" data-crudtype="pre-insert" data-type="insert" ></i>
       </div>
       <div class="setting">
         <i class="fa fa-search-plus pointer" data-reveal-id="query" data-dismissmodalclass="close-query-frame"></i>
       </div>
       <div class="setting">
         <i id='refresh' class="fa fa-refresh pointer" data-pagetype='refresh' data-pageframe="table"></i>
       </div>       
       <div id="setting">
         <div class="title">
           <span>设置</span>
           <a class="close-setting">&#215;</a>
         </div>  
         <div class="line"></div>
         <div class="content">
           <ul>
             <li><a class="pointer" data-rowdefine="init" data-reveal-id="row-def" data-pageframe="row-def" data-table="#tb">定义列</a></li>
             <li><a class="pointer">显示行数</a>
               <ul>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize" data-pageframe="table">
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="5"></i>
                   5</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize" data-pageframe="table">
                   <i class="fa fa-dot-circle-o" aria-hidden="true" data-value="10"></i>
                   10</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize" data-pageframe="table">
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="15"></i>
                   15</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize" data-pageframe="table">
                   <i class="fa fa-dot-circle-o hidden" aria-hidden="true" data-value="25"></i>
                   25</a>
                 </li>
                 <li>
                   <a class="set_page_size pointer" data-pagetype="setpagesize" data-pageframe="table">
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
       <div>
         <div class="arrow_S margin">
           <i id="last" class="fa fa-step-forward pointer" data-pagetype="lastpage" data-pageframe="table"></i>
         </div>
         <div class="arrow_L">
           <i id="next" class="fa fa-chevron-circle-right pointer" data-pagetype="nextpage" data-pageframe="table"></i>
         </div>
         <div class="pageRow">
       	   <span id="pageRow" data-type="row">1</span>
         </div>
         <div class="arrow_L">
           <i id="previous" class="fa fa-chevron-circle-left pointer" data-pagetype="prevpage" data-pageframe="table"></i>
         </div>
         <div class="arrow_S">
           <i id="first" class="fa fa-step-backward pointer" data-pagetype="firstpage" data-pageframe="table"></i>
         </div>
         <input type="hidden" data-type="size" id="page_size" value="10"/>
         <input type="hidden" data-type="number" id="page_no" value="1"/>
         <input type="hidden" data-type="cond" value="HIRE_DATE_F=&HIRE_DATE_T="/>
         <input type="hidden" data-type="orderby" id="ORDER_BY" value="EMP_ID ASC"/> 
         <input type="hidden" data-type="url" value="emp/getEmpPage.do"/>
         <input type="hidden" data-type="jsontype" value="table"/> 
       </div>
     </div>
     <!-- 主表格按钮区域 end --> 
   
     <!-- 定义列区域 start --> 
     <jsp:include page="rowdefine.jsp"></jsp:include>
     <!-- 定义列区域 end -->
    
     <!-- 多维排序区域 start -->
     <jsp:include page="orderby.jsp"></jsp:include>
     <!-- 多维排序区域 end -->
   
     <!-- 个人配置区域 start -->
     <jsp:include page="config.jsp"></jsp:include>
     <!-- 个人配置区域 end -->
     
     <!-- lov区域 start -->
     <jsp:include page="lov.jsp"></jsp:include>
     <!-- lov区域 end -->
   
     <!-- 更新区域 start -->
     <div id='uf' class='update_frame'>     
       <div class='title'>      
         <span data-type="update"><i class="fa fa-user"></i>更新员工</span>
         <span data-type="insert"><i class="fa fa-user"></i>新增员工</span>
       </div>
       <a class="close-update-frame" data-type="close">&#215;</a>
       <div class='line'></div>
       <div class='content'>
         <form>
           <input type='hidden' id='EMP_ID' data-update="db">
           <label for='EMP_NUMBER' class='left'>工号</label>
           <input type='text' id='EMP_NUMBER' name='EMP_NUMBER' data-update="db" required='required' class='left'>
           <label for='FULL_NAME' class='left'>全名</label>
           <input type='text' readonly='readonly' id='FULL_NAME' name='FULL_NAME' data-update="db" required='required' class='left'>
           <label for='LAST_NAME' class='left'>姓</label>
           <input type='text' id='LAST_NAME' data-name="LAST_NAME" name='LAST_NAME' data-update="db" required='required' class='left'>
           <label for='FIRST_NAME' class='left'>名</label>
           <input type='text' id='FIRST_NAME' data-name="FIRST_NAME" name='FIRST_NAME' data-update="db" required='required' class='left'>
           <label for='SEX' class='left'>性别</label>
           <select class='left' id='SEX' name='SEX' data-update="db" required='required' data-listurl="list/getSex.do"></select>       
           <label for='PHONE_NUMBER' class='left'>电话</label>
           <input type='text' id='PHONE_NUMBER' name='PHONE_NUMBER' data-update="db" required='required' class='left'>
           <label for='SALARY' class='left'>薪酬</label>
           <input type='text' id='SALARY' name='SALARY' data-update="db" required='required' class='left'>
           <label for='HIRE_DATE' class='left'>雇佣日期</label>
           <input type='text' id='HIRE_DATE' name='HIRE_DATE' data-update="db" required='required' class='left'>  
           <label for='JOB_NAME' class='left'>职位</label> 
           <input type='text' id='JOB_NAME' name='JOB_NAME' data-update="db" class='left short' data-modify='true' required='required' data-pageframe="uf" data-validurl='lov/validJobName.do' data-queryurl='lov/getJobId.do' data-lovbtn='JOB_LOV' data-hiddenid=["JOB_ID"] data-hiddenval=["JOB_ID"] data-param="jobname"/>
           <input type='hidden' id='JOB_ID' name='JOB_ID' data-update="db">
           <input type='button' id="JOB_LOV" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职位查询" data-queryurl="lov/getJobPage.do" data-jsontype="job" data-defaultquery="true" data-th=["职务编号","职务名称"] data-td=["JOB_ID","JOB_NAME"] data-selectname=["职务编号","职务名称"] data-selectvalue=["JOB_ID","JOB_NAME"] data-choose=[".JOB_ID",".JOB_NAME"] data-recid=["#JOB_ID","#JOB_NAME"] value="···">
           <label for='DEPT_NAME' class='left'>部门</label>
           <input type='text' id='DEPT_NAME' name='DEPT_NAME' data-update="db" class='left short' data-modify='true' required='required' data-pageframe="uf" data-validurl='lov/validDeptName.do' data-queryurl='lov/getDeptId.do' data-lovbtn='DEPT_LOV' data-hiddenid=["DEPARTMENT_ID"] data-hiddenval=["DEPT_ID"] data-param="deptname"/>
           <input type='hidden' id='DEPARTMENT_ID' name='DEPARTMENT_ID' data-update="db">
           <input type='button' id='DEPT_LOV' class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="部门查询" data-queryurl="lov/getDeptPage.do" data-jsontype="dept" data-th=["部门编号","部门名称","部门分类","部门主管","部门地点","启用日期","备注"] data-td=["DEPT_ID","DEPT_NAME","DEPT_TYPE_DESC","MANAGER_NAME","LOCATION_NAME","ENABLE_DATE","REMARK"] data-selectname=["部门名称","部门分类","部门主管"] data-selectvalue=["DEPT_NAME","DEPT_TYPE_DESC","MANAGER_NAME"] data-choose=[".DEPT_ID",".DEPT_NAME"] data-recid=["#DEPARTMENT_ID","#DEPT_NAME"]  value="···">
           <br style="clear:both"/>
           <label for='REMARK' class='left'>备注</label>
           <textarea class='left' id='REMARK' name='REMARK' data-update="db"></textarea>
         </form>
       </div>
       <div class='foot'>       
         <button class="right update_confirm pointer" data-type="update" data-crudtype="update" data-pageframe="uf" data-updateurl="emp/update.do" data-updateparam=["EMP_ID","#EMP_ID"] >提交更新</button>
         <button class="right update_confirm pointer" data-type="insert" data-crudtype="insert" data-pageframe="uf" data-inserturl="emp/insert.do">新增</button>
       </div>    
     </div> 
     <!-- 更新区域 end -->  
     
     <!-- 查询区域 start -->
     <div id='query' class='query_frame'>     
       <div class='title'>      
         <span><i class="fa fa-user"></i>员工查询</span>
       </div>
       <a class="close-query-frame" data-type="close">&#215;</a>
       <div class='line'></div>
       <div class='content'>
         <form>
            <label for='HIRE_DATE_F' class='left'>雇佣日期:</label>
            <input type='text' id='HIRE_DATE_F' name='HIRE_DATE_F' class='left' placeholder="起始雇佣日期">
            <input type='text' id='HIRE_DATE_T' name='HIRE_DATE_T' class='left' placeholder="截止雇佣日期">
            <label for='EMP_NUMBER_F' class='left'>工号:</label>
            <input type='text' id='EMP_NUMBER_F' name='EMP_NUMBER_F' class='left' placeholder="起始工号">
            <input type='text' id='EMP_NUMBER_T' name='EMP_NUMBER_T' class='left' placeholder="截止工号">
            <label for='JOB_NAME_Q' class='left'>职位:</label>
            <input type='text' id='JOB_NAME_Q' name='JOB_NAME' class='left short' data-modify='true'  data-pageframe="query" data-validurl='lov/validJobName.do' data-queryurl='lov/getJobId.do' data-lovbtn='JOB_LOV_Q' data-hiddenid=["JOB_ID_Q"] data-hiddenval=["JOB_ID"] data-param="jobname">
            <input type='hidden' id='JOB_ID_Q' name='JOB_ID'>
            <input type='button' id="JOB_LOV_Q" class='left button pointer' data-pageframe="lov" data-reveal-id="lov" data-bg="lov-modal-bg" data-dismissmodalclass='close-lov' data-lovname="职位查询" data-queryurl="lov/getJobPage.do" data-jsontype="job" data-defaultquery="true" data-th=["职务编号","职务名称"] data-td=["JOB_ID","JOB_NAME"] data-selectname=["职务编号","职务名称"] data-selectvalue=["JOB_ID","JOB_NAME"] data-choose=[".JOB_ID",".JOB_NAME"] data-recid=["#JOB_ID_Q","#JOB_NAME_Q"] value="···">
            <br style="clear:both"/>
            <label for='FULL_NAME_Q' class='left'>全名</label>
            <input type='text' id='FULL_NAME_Q' name='FULL_NAME' class='left'>
            <br style="clear:both"/>
            <label for='DISABLE_FLAG' class='left nolength'>是否排除已失效部门中的员工</label>
            <input type="checkbox" id='DISABLE_FLAG' name='DISABLE_FLAG' style="width:auto;margin:10px 10px "/> 
         </form>
       </div>
       <div class='foot'>             
         <button class="right pointer"  data-buttonframe="table" data-crudtype="query" data-pageframe="query">员工查询</button>
       </div> 
     </div> 
     <!-- 查询区域 end -->  
   
     <!-- 用户信息存放区域 start -->
     <input type="hidden" id="USER_ID" value="123456">  
     <input type="hidden" id="INTERACT_CODE" value="USER_INFO"> 
     <input type="hidden" id="HEADER_ID" value=""> 
     <!-- 用户信息存放区域 end -->  
   </div>
     
    <script>       
         $(function() {
            //设置拖拽
    		$("#uf").draggable();
    		$("#query").draggable();
    		$("#row-def").draggable();
    		$("#orderby").draggable();
    		$("#config").draggable();
    		$("#lov").draggable();
    		//设置日期选择器
    		//$("#HIRE_DATE").datepicker({
      		//	changeMonth: true,
     		//    changeYear: true
   		    //});
    		//$("#HIRE_DATE").datepicker("option", "dateFormat","yy-mm-dd");
    		//$("#HIRE_DATE").datepicker("option", "showAnim", "slide" );
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
       	        $('#LAST_NAME').val(data.rows[0].LAST_NAME);
       	        $('#FIRST_NAME').val(data.rows[0].FIRST_NAME);
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