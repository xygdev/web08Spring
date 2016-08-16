/***************************************************************************************
                    jQuery 增删查改功能
                    Create Date:2015.1.20
                    Create By:bird
                    Last Update Date:2016.7.12
                    Last Update By:bird
                          修改日志
           2015.1.20   创建文件
           2016.4.18   新增按钮动作绑定监听
		   2016.4.26   为删除功能设置传入数组参数delparam[],数组容量为2
			           delparam[0]为参数名 delparam[1]为获取参数值的html标签id或class
			           为预更新功能设置传入数组参数updateparam[]		
			           updateparam[0]为参数名 updateparam[1]为获取参数值的html标签id或class	
		   2016.4.27   新增数据更新功能
		   2016.4.28   bug:{
			             翻页时需要对删除按钮和预更新按钮被重置，所以每次翻页时需要重新执行绑定监听函数，
			             而确认更新按钮并没有被重置，所以导致了确认按钮被重复绑定了多次监听，点一下确认更新按钮会导致更新多次
			           }
			           解决方法：在绑定监听函数前，新增了解绑函数，绑定前先解绑，避免事件重复绑定
		   2016.5.3    新增条件查询功能
		   2016.5.10   新增隐藏空白行功能，当当前页数据不足一页时，自动遍历设置空白行display:none
		   2016.5.11   代码调优，由300行代码减少至150行
		   2016.7.12   代码优化，并新增插件配置说明
		   2016.8.11   整合Spring框架进行的小修改 by sam.t
		   2016.8.16   新增global函数，validate(),
		               验证更新框中的所有required的input框的值，返回一个boolean值validateFlag
		               点击更新按钮时，验证validateFlag,如果为true,则可以更新
		               如果为false,则存在值为空的必填项
***************************************************************************************/
(function($) {
	/******************listener start***********************
	               监听 data-crudtype 传递的值                      
	        暂时只设置对<input> <button> <a> <i> 四种标签的监听绑定               
	              如需为其他更多元素增加点击弹出框效果                      
	               请在listener区域绑定新的监听标签
    *******************************************************/
	$.fn.crudListener = function(){ 
		/****清除绑定****/
		$('input[data-crudtype]').off('click');	
		$('button[data-crudtype]').off('click');
		$('a[data-crudtype]').off('click');	
		$('i[data-crudtype]').off('click');
		/****绑定<input>标签****/
		$('input[data-crudtype]').on('click', function(e) {		
			$(this).crud($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-crudtype]').on('click', function(e) {
			$(this).crud($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-crudtype]').on('click', function(e) {
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			$(this).crud($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-crudtype]').on('click', function(e) {
			$(this).crud($(this).data());
		});
	}  
	/******************listener end***********************/	
    $.fn.crud = function(options) {	
    	/*********************************************
        			       设置默认属性
			queryurl:查询url(若无设定此属性，则需设置pagesetting.urltd，queryurl属性主要用于主表查询) 
			jsontype:数据遍历函数参数，选定遍历函数(若无设定此属性，则需设置pagesetting.typetd,jsontype属性主要用于主表查询) 
			crudsetting{
				loading:ajax载入动画class或id
				refresh:刷新当前页按钮id
				firstpage:第一页按钮id
				lastpage:最后一页按钮id
				prevpage:上一页按钮id
				nextpage:下一页按钮id
				setpagesize:页行数设置按钮class
				pagesize:存放页行数的html标签class或id
				pageno:存放页码的html标签class或id
				pagerow:存放数据下标数的html标签class或id
				delmsg:删除确认框抓取的信息相关的class或id(delmsg属性主要用于删除按钮)
				tablename:表格id 
				lovid:LOV框id(lovid属性主要用于lov查询)
				urltd:存放url的html标签id(urltd属性主要用于lov查询)
				typetd:存放jsontype的html标签的id(typetd属性主要用于lov查询)
				}    
		*********************************************/   
        var defaults={
        	crudsetting:'{"loading":"","refresh":"","firstpage":"","lastpage":"","prevpage":"","nextpage":"","setpagesize":"","pagesize":"","pageno":"","pagerow":"","delmsg":"","tablename":"","lovid":"","urltd":"","typetd":""}'
        }             	
        /******继承默认属性******/
        var options = $.extend({}, defaults, options); 
        /****全局函数****/
        jQuery.global={
        	/******判断当前页是否为首页或末页******/
            pageFlag:function(firstPageFlag,lastPageFlag){
           	    if(firstPageFlag=='true'){
           			$(options.crudsetting.prevpage).css('display','none');
           			$(options.crudsetting.firstpage).css('display','none');
           		}else{
           			$(options.crudsetting.prevpage).css('display','');
           			$(options.crudsetting.firstpage).css('display','');
           		}
           		if(lastPageFlag=='true'){
           			$(options.crudsetting.lastpage).css('display','none');
            		$(options.crudsetting.nextpage).css('display','none');
           		}else{
           			$(options.crudsetting.lastpage).css('display','');
           			$(options.crudsetting.nextpage).css('display','');
           		}
           	},
           	validate:function(){
           		var input=$(options.crudsetting.tablename+' input[required="required"]');
           		validate_flag=true;
				for(i=0;i<input.length;i++){
					if(input[i].value==''||input[i].value==null){
						id=input[i].id;
						label=$(options.crudsetting.tablename+' label[for="'+id+'"]').text();
						alert('提交失败!错误信息:'+label+'不能为空！');
						input[i].focus();
						validate_flag=false;
						return;
					}else{
						continue;
					}
				}
           	}
            
        }
        	
        return this.each(function() {       	
        	/******删除方法******/
			if(options.crudtype=='del'){
				pageSize=parseInt($(options.crudsetting.pagesize).val());
				/****删除提示信息弹出框内容尚未更改为可设置参数，待处理****/
				tr=$(this).parent().parent();
				name=tr.children(options.crudsetting.delmsg).text();
				result=confirm('是否要删除用户('+name+')?');
				/****删除提示信息弹出框内容尚未更改为可设置参数，待处理****/
				if(result==true){
					$(options.crudsetting.loading).show();/****显示加载动画****/
					pageNo=parseInt($(options.crudsetting.pageno).val());
					param='pageSize='+pageSize+'&pageNo='+pageNo+'&'+options.delparam[0]+'='+tr.children(options.delparam[1]).text();
					$.ajax({
						type:'post', 
						data:param,
						url:options.delurl,
						dataType:'json',
						success: function (data) {
							$(options.crudsetting.refresh).click();/****点击刷新当前页按钮，刷新数据****/	
						},
						error: function () {
							alert("获取Json数据失败");
						}
					}); 
					return;			
				}else{            
					result=null;
				}    				
			}
			/******预更新方法******/
			else if(options.crudtype=='pre-update'){
				$(options.crudsetting.loading).show();/****显示加载动画****/
				tr=$(this).parent().parent();
				param=options.updateparam[0]+'='+tr.children(options.updateparam[1]).text();/****设置参数****/
				$.ajax({
					type:'post', 
					data:param,
					url:options.preupdateurl,
					dataType:'json',
					success: function (data) {
						jQuery.json.getUpdateJSON(data);/****获取目标更新行数据****/
						$(options.crudsetting.loading).hide();/****隐藏加载动画****/
					},
					error: function () {
						alert("获取Json数据失败");
					}
				});			
			}
			/******更新方法******/
			else if(options.crudtype=='update'){ 
				jQuery.global.validate();
				if(validate_flag==true){
				/****************************/
				jQuery.json.setUpdateParam();
				pageSize=parseInt($(options.crudsetting.pagesize).val());
				pageNo=parseInt($(options.crudsetting.pageno).val());
				param=options.updateparam[0]+'='+$(options.updateparam[1]).val()+'&'+param;/****设置参数****/
				$.ajax({
					type:'post', 
					data:param,
					url:options.updateurl,
					dataType:'json',
					success: function (data) {
    					if(data.retcode=="0"){
    						alert("更新成功!");
    						$('.'+options.dismissmodalclass).click();/****点击关闭更新框按钮****/
    						$(options.crudsetting.refresh).click();/****点击刷新当前页按钮，刷新数据****/
    					}else{
    						alert("更新处理失败！错误信息:"+data.errbuf);
    					}						  
					},
					error: function () {
						alert("获取数据失败");
					}			
				});	
				/*****************************************/
				}else{
					return;
				}
			}
			/******条件查询方法******/
			else if(options.crudtype=='query'){
				jQuery.json.setQueryParam();/****设置查询条件参数函数，暂时写在页面里，考虑改进中****/				
				pageSize=parseInt($(options.crudsetting.pagesize).val());				
				pageNo=parseInt(1);
				$(options.crudsetting.pageno).val(pageNo);
				param=param+'&pageSize='+pageSize+'&pageNo='+pageNo;
				queryurl=$(options.crudsetting.urltd).val();
				$.ajax({
					type:'post', 
					data:param,
					url:queryurl,
					dataType:'json',
					success: function (data) {
						$(options.crudsetting.tablename+' td').html('');
						jQuery.json.getMSG(data);
						$(options.crudsetting.tablename+' tr').css('display','');/****显示被隐藏行****/
						jQuery.json.getContent(data,$(options.crudsetting.typetd).val());
						for(j=0;j<=(pageSize-(pageMaxRow-pageMinRow+1));j++){/****隐藏空白行****/
	                	    $(options.crudsetting.tablename+' tr:eq('+(pageSize-j+1)+')').css('display','none');
	                	};
						jQuery.global.pageFlag(firstPageFlag,lastPageFlag);
						width='-'+parseInt($(options.crudsetting.lovid).css('width'))/2+'px';      	    
		      	        $(options.crudsetting.lovid).css('margin-left',width);
					},
					error: function () {
						alert("获取Json数据失败");
					}
				}); 
			}
        });
    }
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-crudtype:
@    data-crudtype 为分页按钮的类型，有'del','pre-update','update',
@    'query' 四种类型
@    'del':          删除按钮
@    'pre-update':   加载更新框按钮
@    'update':       更新按钮
@    'query':        条件查询按钮
@    data-crudsetting:
@    data-crudsetting 为页面设置json参数，包含的属性有{
@		loading:ajax载入动画class或id
@		refresh:刷新当前页按钮id
@		firstpage:第一页按钮id
@		lastpage:最后一页按钮id
@		prevpage:上一页按钮id
@		nextpage:下一页按钮id
@		setpagesize:页行数设置按钮class
@		pagesize:存放页行数的html标签class或id
@		pageno:存放页码的html标签class或id
@		pagerow:存放数据下标数的html标签class或id
@		delmsg:删除确认框抓取的信息相关的class或id(delmsg属性主要用于删除按钮)
@		tablename:表格id 
@		lovid:LOV框id(lovid属性主要用于lov查询)
@		urltd:存放url的html标签id(urltd属性主要用于lov查询)
@		typetd:存放jsontype的html标签的id(typetd属性主要用于lov查询)
@    }
@    非必需参数：
@    data-queryurl:
@    data-queryurl 为查询url
@    (若无设定此属性，则需设置pagesetting.urltd，queryurl属性主要用于主表查询) 
@    data-jsontype:
@    data-jsontype:数据遍历函数参数，选定遍历函数
@    (若无设定此属性，则需设置pagesetting.typetd,jsontype属性主要用于主表查询) 
@
@
***********************************************************/