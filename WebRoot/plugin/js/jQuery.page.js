/*********************************************************
                    jQuery 分页功能
                    Create Date:2015.12.12
                    Create By:bird
                    Last Update Date:2016.7.12
                    Last Update By:bird
                          修改日志
           2015.12.12   创建文件
           2016.4.18    新增按钮动作绑定监听
           2016.4.28    新增数据遍历参数值jsontype
           				（用于分辨主表数据和不同lov的表格数据）
           2016.5.06    修改html标签data数据传入格式，		
           				由字符串改为json数组传入
           2016.5.10    新增隐藏空白行功能，当当前页数据不足一页时，
           				自动遍历设置空白行display:none
           2016.5.11    代码调优，由400行代码减少至150行
           2016.7.12    代码优化，并新增插件配置说明
*********************************************************/
(function($) {                                      	
	/******************listener start***********************
	              监听 data-pagetype 传递的值
	       暂时只设置对<input> <button> <a> <i> 四种标签的绑定
	              请在listener区域绑定新的监听标签
    *********************************************************/
	$.fn.pageListener = function(){ 
		/****清除绑定****/
		$('input[data-pagetype]').off('click');	
		$('button[data-pagetype]').off('click');
		$('a[data-pagetype]').off('click');	
		$('i[data-pagetype]').off('click');
		/****绑定<inpu>t标签****/
		$('input[data-pagetype]').on('click', function(e) {		
			$(this).page($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-pagetype]').on('click', function(e) {
			$(this).page($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-pagetype]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).page($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-pagetype]').on('click', function(e) {
			$(this).page($(this).data());
		});
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().pageListener();

    $.fn.page = function(options) {	        
		/*********************************************
                        设置默认属性  
        *********************************************/	    
		var defaults={
			pagesetting:'{"loading":"","firstpage":"","lastpage":"","prevpage":"","nextpage":"","setpagesize":"","closebutton":"","pagesize":"","pageno":"","pagerow":"","tablename":""}'
		}		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
		/****全局函数****/
		jQuery.global={
		    main:function(){
		    	jQuery.json.setQueryParam();
		    	/****如果为最后一页按钮，设置goLastPage参数为true,其余则为false****/
		    	if(options.pagetype=='lastpage'){
		    		param=param+'&pageSize='+pageSize+'&pageNo='+pageNo+'&goLastPage=true';
		    	}else{
		    		param=param+'&pageSize='+pageSize+'&pageNo='+pageNo+'&goLastPage=false';
		    	}			
				queryurl=options.queryurl;
				if(queryurl==undefined){
					queryurl=$(options.pagesetting.urltd).val();
				}
				orderby=$('#ORDER_BY').val();
				param=param+'&orderby='+orderby;
				//alert("param:"+param);
				$.ajax({
					async:true,
					type:'post', 
					data:param,
					url:queryurl,
					dataType:'json',
					success: function (data) {
						$(options.pagesetting.tablename+' td[data-column="db"]').html('');/****清空表格内原有的数据****/
						jQuery.json.getMSG(data);/****获取json参数数据****/
						$(options.pagesetting.tablename+' tr').css('display','');/****显示被隐藏行****/
						if(parseInt(data.pageMinRow)!=0){
							jsontype=options.jsontype;
							if(jsontype==undefined){
								jsontype=$(options.pagesetting.typetd).val();
							}
							jQuery.json.getContent(data,jsontype);/****获取json遍历数据，插入表格****/
							
							for(j=0;j<=(pageSize-(pageMaxRow-pageMinRow+1));j++){/****隐藏空白行****/
		                	    $(options.pagesetting.tablename+' tr:eq('+(pageSize-j+1)+')').css('display','none');
		                	}
							if(firstPageFlag=='true'){/****判断是否为首页，若是，隐藏第一页与上一页按钮****/
			       				$(options.pagesetting.prevpage).css('display','none');
			       				$(options.pagesetting.firstpage).css('display','none');
			       			}else{
			       				$(options.pagesetting.prevpage).css('display','');
			       				$(options.pagesetting.firstpage).css('display','');
			       			}
			       			if(lastPageFlag=='true'){/****判断是否为末页，若是，隐藏最后一页与下一页按钮****/
			        			$(options.pagesetting.lastpage).css('display','none');
			       				$(options.pagesetting.nextpage).css('display','none');
			       			}else{
			       				$(options.pagesetting.lastpage).css('display','');
			       				$(options.pagesetting.nextpage).css('display','');
			       			}
							if(pageMinRow!=pageMaxRow){/****判断该页是否只有一行数据****/
								$(options.pagesetting.pagerow).text(pageMinRow+'-'+pageMaxRow);
							}else{
								$(options.pagesetting.pagerow).text(pageMaxRow);
							}
							if(options.pagetype=='setpagesize'){/****如果为设置页行数按钮，则点击关闭按钮****/
								$(options.pagesetting.pageno).val(1);
								$(options.pagesetting.closebutton).click();
								//$('.title a',$('#setting')).click();
							}else if(options.pagetype=='lastpage'){/****如果为最后一页按钮，将参数totalPages赋值到页码html标签中****/
								$(options.pagesetting.pageno).val(parseInt(totalPages));
							}else{
								$(options.pagesetting.pageno).val(pageNo);
							}
						}else{
							if(options.pagetype=='nextpage'){
								alert("当前页无数据,即将自动跳转到最后一页");
								//$(options.pagesetting.prevpage).click();
								$(options.pagesetting.lastpage).click();
							}
							else if(options.pagetype=='refresh'){
								alert("当前页无数据,即将自动跳转到最后一页");
								$(options.pagesetting.lastpage).click();
							}
						}
						$(options.pagesetting.loading).hide();/****隐藏加载动画****/
					},
					error: function () {
						if(options.pagetype=='lastpage'){
						    $(options.tablename+' td').html('');
						    alert("当前页无数据");
						    $(options.pagesetting.pagerow).text('');
						    $(options.pagesetting.loading).hide();
						}else{
							alert("获取Json数据失败");
						}						
					}
				});	
		    }
       	}
			
        return this.each(function() {
        	$(options.pagesetting.loading).show();/****显示加载数据加载动画****/
        	pageSize=parseInt($(options.pagesetting.pagesize).val());
            /****预查询****/
			if(options.pagetype=='refresh'){
				pageNo=parseInt($(options.pagesetting.pageno).val());
				$('td[data-column="db"]',$('tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',$(options.pagesetting.tablename));
				$('td',$(options.pagesetting.tablename)).parent().remove();
				for(j=1;j<=pageSize;j++){
					$('tr:eq(0)',$(options.pagesetting.tablename)).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}
			/****第一页****/
			else if(options.pagetype=='firstpage'){
				pageNo=parseInt(1);
				jQuery.global.main();
			}
			/****最后一页****/
			else if(options.pagetype=='lastpage'){
				pageNo=parseInt($(options.pagesetting.pageno).val())+1;
				jQuery.global.main();
			}
			/****下一页****/
			else if(options.pagetype=='nextpage'){
				pageNo=parseInt($(options.pagesetting.pageno).val())+1;
				jQuery.global.main();
			}
			/****上一页****/
			else if(options.pagetype=='prevpage'){	
				pageNo=parseInt($(options.pagesetting.pageno).val())-1;
				jQuery.global.main();
			}				
			/****页行数设置****/
			else if(options.pagetype=='setpagesize'){
				pageSize=parseInt($(this).text());
				$('i',$(options.pagesetting.setpagesize)).css('visibility','hidden');
				$('i',$(this)).css('visibility','visible');
				$(options.pagesetting.pagesize).attr('value',pageSize);
				pageNo=parseInt(1);
				linenum=($(options.pagesetting.tablename+' tr').length-1);
				for(i=1;i<linenum;i++){
					$('tr:eq(1)',$(options.pagesetting.tablename)).remove();
				}
				$('td[data-column="db"]',$('tr:eq(1)')).html('');
				blank_tr=$('tr:eq(1)',$(options.pagesetting.tablename));
				for(j=1;j<pageSize;j++){
					$('tr:eq(1)',$(options.pagesetting.tablename)).parent().append(blank_tr.clone());
				}
				jQuery.global.main();
			}
		});
	}
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-pagetype:
@    data-pagetype 为分页按钮的类型，有'refresh','firstpage','lastpage',
@    'prevpage','nextpage','setpagesize'六种类型
@    'refresh':      刷新按钮
@    'firstpage':    第一页按钮
@    'lastpage':     最后一页按钮
@    'prevpage':     上一页按钮
@    'nextpage':     下一页按钮
@    'setpagesize':  设置页码按钮
@    data-pagesetting:
@    data-pagesetting 为页面设置json参数，包含的属性有{
@		loading:ajax载入动画class或id
@       firstpage:第一页按钮id
@       lastpage:最后一页按钮id
@       prevpage:上一页按钮id
@       nextpage:下一页按钮id
@       setpagesize:页行数设置按钮class
@		closebutton:关闭设置弹出框按钮的class
@       pagesize:存放页行数的html标签class或id
@		pageno:存放页码的html标签class或id
@		pagerow:存放数据下标数的html标签class或id
@		tablename:表格id 
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