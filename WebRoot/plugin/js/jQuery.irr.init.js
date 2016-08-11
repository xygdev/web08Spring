/*********************************************************
                    jQuery 交互式报表(IRR)格式初始化/保存/加载功能
                    Create Date:2016.7.13
                    Create By:bird
                    Last Update Date:2016.7.13
                    Last Update By:bird
                          修改日志
           2016.7.13   创建文件,并新增插件配置说明  
		   2016.8.11   整合Spring框架进行的修改:和数据库栏位相关的变量统一大写。另外，插件js名称添加irr关键字。 by sam.t     
*********************************************************/
(function($) {
	/****属性设置****/
	options={
	    		
	}

	/**************************
        	默认属性
	**************************/	
	var defaults = {  
		userid:'#USER_ID',     			/****用户id值存放标签id****/
		interactcode:'#INTERACT_CODE',  /****报表格式code值存放标签id****/
		orderpara:'#ORDER_BY',        /****排序参数值存放标签id****/
		headerid:'#HEADER_ID',      	/****报表格式头id值存放标签id****/
		pagesize:'#PAGE_SIZE',          /****每页显示行数值存放标签id****/
		tableid:'#tb',					/****表格id****/
		refresh:'#refresh',				/****刷新按钮id****/
		url:'irr/getDefaultIrr.do'			/****ajax get地址****/
	}; 
		
	/****继承默认属性****/
    var options = $.extend({}, defaults, options); 
        
    user_id=$(options.userid).val();
    interact_code=$(options.interactcode).val();
    param='USER_ID='+user_id+'&INTERACT_CODE='+interact_code;
    $.ajax({
    	type:'get', 
		data:param,
		url:options.url,
		dataType:'json',
		success: function (data) {
			if(data.EXISTS=='Y'){
				$(options.orderpara).val(data.rows.HEADER[0].ORDER_BY);
				$(options.headerid).val(data.rows.HEADER[0].HEADER_ID);
				$(options.pagesize).val(data.rows.HEADER[0].PAGE_SIZE);
				$(options.tableid+' th').css('display','none');
				$(options.tableid+' td').css('display','none');
				linenum=$(options.tableid+' tr').length-1;
            	setnum=data.rows.SEQ.length;
            	data.rows.SEQ.reverse();
            	for(i=0;i<setnum;i++){		   
					if(data.rows.SEQ[i].COLUMN_NAME!=null){			  				
						row=$('.'+data.rows.SEQ[i].COLUMN_NAME);
						rowindex=row.index();				
						$('.'+data.rows.SEQ[i].COLUMN_NAME).css('display','');
						if(rowindex!=0){
							$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(0)'));
							for(j=0;j<=linenum;j++){
     	                		$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(0)',$(options.tableid+' tr:eq('+j+')')));
     	            		}
						}
						else{
							$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(1)'));
							for(j=0;j<=linenum;j++){
     	                		$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(1)',$(options.tableid+' tr:eq('+j+')')));
     	            		}
						}
					}
				}	
				$(options.refresh).click();
			}
			else{
				$(options.refresh).click();
			}			
		},
		error: function () {
			alert("获取Json数据失败");
		}
	}); 
    
    /******************listener start***********************
					监听data-config属性
				暂时只设置对<button> <a> 两种标签的绑定
			如需对更多标签进行绑定，请在listener区域绑定新的监听标签
    *********************************************************/	
    $.fn.configListener = function(){ 
    	/****绑定<a>标签****/
		$('a[data-config]').on('click', function(e) {	
			/****阻止<a>标签默认的点击事件（超链接跳转)****/
			e.preventDefault();
			$(this).config($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-config]').on('click', function() {	
			$(this).config($(this).data());
		});
    }
    /******************listener end***********************/	
    
    /****执行监听函数****/
    $().configListener();
    
    $.fn.config = function(options) {	        
		/*********************************************
                           设置默认属性
        *********************************************/	    
		var defaults={
			userid:'#USER_ID',     			/****用户id值存放标签id****/
			interactcode:'#INTERACT_CODE',  /****报表格式code值存放标签id****/
			interactname:'#USER_INTERACT_NAME',	/****报表格式name值存放标签id****/
			interactdesc:'#DESCRIPTION',	/****报表格式desc值存放标签id****/
			orderpara:'#ORDER_BY',        /****排序参数值存放标签id****/
			headerid:'#HEADER_ID',      	/****报表格式头id值存放标签id****/
			pagesize:'#page_size',          /****每页显示行数值存放标签id****/
			tableid:'#tb',					/****表格id****/
			configid:'#config',				/****个人配置框id****/
			refresh:'#refresh',				/****刷新按钮id****/
			setframe:'#setting',			/****设置框id****/
			initurl:'irr/getIrrHead.do',	    /****获取用户报表格式信息url****/
			saveurl:'irr/saveIrr.do',		/****保存用户报表格式信息url****/
			loadurl:'irr/getIrr.do',			/****加载用户报表格式信息url****/
			publicflag:'#PUBLIC_FLAG',		/****共享标志框id****/
			defaultflag:'#DEFAULT_FLAG',	/****默认标志框id****/
			autoflag:'#AUTOQUERY_FLAG'		/****自动查询标志框id****/
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
			
        return this.each(function() {
        	configType=options.config;
        	interact_code=$(options.interactcode).val();
    		user_id=$(options.userid).val();
    		header_id=$(options.headerid).val();
        	if(configType=='init'){
        		$(options.setframe).css('visibility','hidden');
        		$(options.interactname).val('');
        		$(options.interactdesc).val('');
        		param='INTERACT_CODE='+interact_code+'&USER_ID='+user_id;
        		$.ajax({
    				type:'get', 
    				data:param,
    				url:options.initurl,
    				dataType:'json',
    				success: function (data) {
    					$(options.configid+' select').html('');
    					loadingindex=data.rows.length;
    					for(i=0;i<loadingindex;i++){
    						name=data.rows[i].USER_INTERACT_NAME;
    						value=data.rows[i].HEADER_ID;
    						$(options.configid+' select').append('<option value="'+value+'" >'+name+'</option>');	
    					}
    					$(options.configid+' option[value="'+header_id+'"]').prop('selected',true);
    				},error: function(){
    				    alert("获取json数据错误");
    				}
    			});	
        	}
        	else if(configType=='save'){   		
        		user_interact_name=$(options.interactname).val();
        		order_by=$(options.orderpara).val();
        		page_size=$(options.pagesize).val();
        		if(user_interact_name==''){
        			alert('自定义名称不能为空');
        			return;
        		}
        		description=$(options.interactdesc).val();
        		seq = new Array();
        		count=$(options.tableid+' th').length-1;/****获取总列数（排除最后一列的空列）****/
         	    for(i=0;i<count;i++){/****遍历标题列（除最后一列空列外）****/
         	        if($(options.tableid+' th:eq('+i+')').css('display')!='none'){
    				    seq[i]=$(options.tableid+' th:eq('+i+')').attr('class');
         	        }
         	    }
         	    //seq.reverse();
        		if($(options.publicflag).prop("checked")==true){
        			public_flag='Y';
        		}
        		else{
        			public_flag='N';
        		}
        		if($(options.defaultflag).prop("checked")==true){
        			default_flag='Y';
        		}
        		else{
        			default_flag='N';
        		}
        		if($(options.autoflag).prop("checked")==true){
        			autoquery_flag='Y';
        		}
        		else{
        			autoquery_flag='N';
        		}
        		param='INTERACT_CODE='+interact_code+'&USER_ID='+user_id+'&USER_INTERACT_NAME='+user_interact_name+'&ORDER_BY='+order_by+'&PAGE_SIZE='+page_size+'&DESCRIPTION='+description+'&SEQ='+seq+'&PUBLIC_FLAG='+public_flag+'&DEFAULT_FLAG='+default_flag+'&AUTOQUERY_FLAG='+autoquery_flag;
        		$.ajax({
    				type:'post', 
    				data:param,
    				url:options.saveurl,
    				dataType:'json',
    				success: function (data) {
    					if(data.retcode!=0){
    						alert("保存处理失败！错误信息:"+data.errbuf);
    					}else{
    						alert("保存成功");
        					$(options.configid+' .close-reveal-modal').click();
    					}
    				},error: function(){
    				    alert("save出现未知错误");
    				}
    			});
        	}
        	else if(configType=='load'){
        		header_id=$(options.configid+' option:selected').val()      
        		if(header_id=='blank'){
        			alert('载入模板不能为空，请重新选择！');
        			return;
        		}
                param='HEADER_ID='+header_id;
                $.ajax({
    				type:'get', 
    				data:param,
    				url:options.loadurl,
    				dataType:'json',
    				success: function (data) {
    				    if(data.EXISTS=='Y'){
    				        $(options.orderpara).val(data.rows.HEADER[0].ORDER_BY);
    				        $(options.headerid).val(data.rows.HEADER[0].HEADER_ID);
    				        $(options.pagesize).val(data.rows.HEADER[0].PAGE_SIZE);
    				        $(options.setframe+' i[data-value]').css('visibility','hidden');
    				        $(options.setframe+' i[data-value="'+data.rows.PAGE_SIZE+'"]').css('visibility','visible');
    				        $(options.tableid+' th').css('display','none');
                			$(options.tableid+' td').css('display','none');
                			linenum=$(options.tableid+' tr').length-1;
                			setnum=data.rows.SEQ.length;
                			data.rows.SEQ.reverse();
                			for(i=0;i<setnum;i++){		   
    							if(data.rows.SEQ[i].COLUMN_NAME!=null){			  				
    								row=$('.'+data.rows.SEQ[i].COLUMN_NAME);
    								rowindex=row.index();				
    								$('.'+data.rows.SEQ[i].COLUMN_NAME).css('display','');
    								if(rowindex!=0){
    									$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(0)'));
    									for(j=0;j<=linenum;j++){
         	                				$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(0)',$(options.tableid+' tr:eq('+j+')')));
         	            				}
    								}
    								else{
    									$(options.tableid+' th:eq('+rowindex+')').insertBefore($(options.tableid+' th:eq(1)'));
    									for(j=0;j<=linenum;j++){
         	                				$('td:eq('+rowindex+')',$(options.tableid+' tr:eq('+j+')')).insertBefore($('td:eq(1)',$(options.tableid+' tr:eq('+j+')')));
         	            				}
    								}
    							}
    				    	}	
    				    	$(options.refresh).click();
    				    	alert("加载成功");
        					$(options.configid+' .close-reveal-modal').click();
    				    }
    				    else{
    				    
    				    }			
    				},
    				error: function () {
    					alert("获取Json数据失败");
    				}
    			}); 	      		
        	}
        	else{
        		alert('获取json数据失败，请联系IT部门');
        	}       	
		});
	}		
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-config:
@    data-config 为个人配置按钮的类型，有'init','save','load'三种类型
@    'init':      个人配置面板初始化按钮
@	 'save':      个人配置保存按钮
@    'load':      个人配置加载按钮
@    非必需参数：
@    data-userid:用户id值存放标签id
@    不设置此参数则默认为'#user_id'
@	 data-interactcode:报表格式code值存放标签id
@    不设置此参数则默认为'#interact_code'
@	 data-interactname:报表格式name值存放标签id
@    不设置此参数则默认为'#define_name'
@	 data-interactdesc:报表格式desc值存放标签id
@    不设置此参数则默认为'#define_desc'
@	 data-orderpara:排序参数值存放标签id
@    不设置此参数则默认为'#order_para'
@	 data-headerid:报表格式头id值存放标签id
@    不设置此参数则默认为'#header_id'
@	 data-pagesize:每页显示行数值存放标签id
@    不设置此参数则默认为'#page_size'
@	 data-tableid:表格id
@    不设置此参数则默认为'#tb'
@	 data-configid:个人配置框id
@    不设置此参数则默认为'#config'
@	 data-refresh:刷新按钮id
@    不设置此参数则默认为'#refresh'
@	 data-setframe:设置框id
@    不设置此参数则默认为'#setting'
@	 data-initurl:获取用户报表格式信息url
@    不设置此参数则默认为'getUserIRR.do'
@	 data-saveurl:保存用户报表格式信息url
@    不设置此参数则默认为'getSaveData.do'
@	 data-loadurl:加载用户报表格式信息url
@    不设置此参数则默认为'getIRR.do'
@	 data-publicflag:共享标志框id
@    不设置此参数则默认为'#public_flag'
@	 data-defaultflag:默认标志框id
@    不设置此参数则默认为'#default_flag'
@	 data-autoflag:自动查询标志框id
@    不设置此参数则默认为'autoquery_flag'
@
@
***********************************************************/