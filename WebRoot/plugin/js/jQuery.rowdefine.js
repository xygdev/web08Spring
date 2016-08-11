/*********************************************************
                    jQuery 定义列功能
                    Create Date:2015.12.9
                    Create By:bird
                    Last Update Date:2016.7.12
                    Last Update By:bird
                          修改日志
           2015.12.9   创建文件
           2016.1.15   解决同时上移（或下移）两个或两个以上的栏位的
                       时候，当栏位到达顶部（或底部）的时候，后面的
                       栏位会顶到前面，的问题
           2016.7.11   代码优化
           2016.7.12   新增插件配置说明 
*********************************************************/

(function($) {
	/******************listener start***********************
	  		  		监听data-rowdefine属性
		        暂时只设置对<button> <a> 两种标签的绑定
		    如需对更多标签进行绑定，请在listener区域绑定新的监听标签
	*********************************************************/	
	$.fn.rowdefineListener = function(){ 	
		/****绑定<button>标签****/
		$('button[data-rowdefine]').on('click', function(e) {
			$(this).rowdefine($(this).data());
		});	
		/****绑定<a>标签****/
		$('a[data-rowdefine]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).rowdefine($(this).data());
		});	
	}   
	/******************listener end***********************/	

	/****执行监听函数****/
	$().rowdefineListener();
	
	$.fn.rowdefine = function(options) {
		/**************************
        		设置默认属性
		**************************/	
		var defaults = {  
			definetable:'#tb',     /****定义表格id默认值***/
			setbutton:'#setting',  /****设置按钮id默认值***/
		    showframe:'#show',     /****显示框id默认值***/
		    hideframe:'#hide'      /****隐藏框id默认值***/
		}; 
		
		/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
        
        /*****************************
        		  设置全局变量
        *****************************/

        /****设置全局变量lastindex（最后一列空白列的列索引****/
        var lastindex=($(options.definetable+' td').length/($(options.definetable+' tr').length-1))-1;
                     	
        return this.each(function() {
            /****定义列按钮****/       
        	if(options.rowdefine=='init'){
        		linenum=($(options.definetable+' tr').length-1);/****获取表格除标题栏外的总行数****/
            	$(options.setbutton).css('visibility','hidden');
             	$(options.showframe).empty();/****清空show <option>中的内容****/
             	$(options.hideframe).empty();/****清空hide <option>中的内容****/
        		count=$(options.definetable+' th').length-1;/****获取总列数（排除最后一列的空列）****/
             	for(i=0;i<count;i++){/****遍历标题列（除最后一列空列外）****/
             	    if($(options.definetable+' th:eq('+i+')').css('display')!='none'){
        				/****如果标题列不隐藏，则标题名加入show <option>中****/
             	        $(options.showframe).append('<option value='+$(options.definetable+' th:eq('+i+')').attr('class')+'>'+$(options.definetable+' th:eq('+i+')').text()+'</option>')
             	    }
             	    else{
        				/****如果标题列隐藏，则标题名加入hide <option>中****/
             	        $(options.hideframe).append('<option value='+$(options.definetable+' th:eq('+i+')').attr('class')+'>'+$(options.definetable+' th:eq('+i+')').text()+'</option>')
             	    }
             	} 
        	}       	
        	/****显示按钮****/   
        	else if(options.rowdefine=='show'){
        		value=[];
             	linenum=($(options.definetable+' tr').length-1);/****获取表格除标题栏外的总行数****/
        		hiddenindex=($(options.showframe+'  option').length);/****设置当前第一列隐藏列的索引值totalindex为show <option>选项的总数（如果所有都显示，则totalindex=lastindex,即第一列隐藏列为空白列）****/
             	$(options.hideframe+' option:selected').each(function(){	
             	    value.push($(this).val());/****遍历hide <option>，将选中项的值存入value数组****/
             	});
                value=value.reverse();/****将value数组的元素逆序排列****/
        		$(options.showframe).append($(options.hideframe+' option:selected'));/****将hide <option>中的选中项加入到show <option>中****/
             	$(options.hideframe+' option:selected').remove(); /****将hide <option>中的选中项删除****/
             	for(i=0;i!=-1;i++){
        			/****遍历value数组****/
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();/****设置列索引值rowindex为<option>中value的索引值****/ 
        				if(rowindex!=hiddenindex){
        					$(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq('+hiddenindex+')')); //将索引值为rowindex的<th>标题单元格插入到totalindex隐藏列第一列的前一列
        					for(j=0;j<=linenum;j++){
             	                $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$(options.definetable+' tr:eq('+j+')')));
        						/****将每一行中索引值为rowindex的<td>单元格插入到隐藏列第一列相应单元格的前一格****/
             	            }
        				}
        				$('.'+value[i]).css('display','');/****将class为value[i]的元素显示****/
             	    }
             	    else{
             	        break;
             	    }
             	}
        	}
        	/****显示所有按钮****/   
        	else if(options.rowdefine=='show_all'){
        		value=[];
             	linenum=($(options.definetable+' tr').length-1);
        		hiddenindex=($(options.showframe+' option').length);
             	$(options.hideframe+' option').each(function(){
             	    value.push($(this).val());
             	}); 	
             	value=value.reverse();
        		$(options.showframe).append($(options.hideframe+' option'));
             	$(options.hideframe+' option').remove();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        $(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq('+hiddenindex+')'));
             	        for(j=0;j<=linenum;j++){
             	            $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq('+hiddenindex+')',$(options.definetable+' tr:eq('+j+')')));
             	        }
                        $('.'+value[i]).css('display','');			
             	    }
             	    else{
             	        break;
             	    }
             	} 
        	}
        	/****隐藏按钮****/   
        	else if(options.rowdefine=='hide'){
        		value=[];		
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option:selected').each(function(){
             	    value.push($(this).val());
             	});
        		$(options.hideframe).append($(options.showframe+' option:selected')); 
             	$(options.showframe+' option:selected').hide();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        $(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq('+lastindex+')'));//将索引值为rowindex的<th>标题单元格插入到lastindex空白列的前一列
             	        for(j=0;j<=linenum;j++){
             	            $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$(options.definetable+' tr:eq('+j+')')));
             	        } 
             	        $('.'+value[i]).css('display','none');
             	    }
             	    else{
        				
             	        break;
             	    }
             	} 
        	}
        	/****隐藏所有按钮****/   
        	else if(options.rowdefine=='hide_all'){
        		value=[];
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option').each(function(){
             	    value.push($(this).val());
             	});
        		$(options.hideframe).append($(options.showframe+' option'));
             	$(options.showframe+' option').remove(); 
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){ 
             	        rowindex=$('.'+value[i]).index(); 
             	        $(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq('+lastindex+')'));
             	        for(j=0;j<=linenum;j++){
             	            $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq('+lastindex+')',$(options.definetable+' tr:eq('+j+')')));
             	        } 
             	        $('.'+value[i]).css('display','none');
             	    }
             	    else{
             	        break;
             	    }
             	}	
        	}
        	/****上移按钮****/   
        	else if(options.rowdefine=='up'){
            	value=[];
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option:selected').each(function(){
             	    value.push($(this).val());
             	});
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=0){
             	            $(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq('+(rowindex-1)+')'));
             	            for(j=0;j<=linenum;j++){
             	                $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq('+(rowindex-1)+')',$(options.definetable+' tr:eq('+j+')')));
             	            }
             	            $(options.showframe+' option:eq('+rowindex+')').insertBefore($(options.showframe+' option:eq('+(rowindex-1)+')')); 
             	        }
             	        else{
             	            break;
             	        }
             	    }	
             	    else{
             	        break;
             	    }
             	}   
            }
        	/****下移按钮****/   
            else if(options.rowdefine=='down'){
            	value=[];
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option:selected').each(function(){
             	    value.push($(this).val());
             	}); 
             	showindex=($(options.showframe+' option').length-1);
             	value=value.reverse();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=showindex){
             	            $(options.definetable+' th:eq('+rowindex+')').insertAfter($(options.definetable+' th:eq('+(rowindex+1)+')'));
             	            for(j=0;j<=linenum;j++){
             	                $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertAfter($('td:eq('+(rowindex+1)+')',$(options.definetable+' tr:eq('+j+')')));
             	            }
             	            $(options.showframe+' option:eq('+rowindex+')').insertAfter($(options.showframe+' option:eq('+(rowindex+1)+')')); 
             	        }
             	        else{
             	            break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        	/****上移至顶部按钮****/   
            else if(options.rowdefine=='top'){
            	value=[];
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option:selected').each(function(){
             	    value.push($(this).val());
             	});
             	value=value.reverse();
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index(); 
             	        if(rowindex!=0){
             	            $(options.definetable+' th:eq('+rowindex+')').insertBefore($(options.definetable+' th:eq(0)'));
             	            for(j=0;j<=linenum;j++){
             	                $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertBefore($('td:eq(0)',$(options.definetable+' tr:eq('+j+')')));
             	            }
             	            $(options.showframe+' option:eq('+rowindex+')').insertBefore($(options.showframe+' option:eq(0)')); 
             	        }
             	        else{
                 	        break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        	/****下移至底部按钮****/   
            else if(options.rowdefine=='bottom'){
            	value=[];
             	linenum=($(options.definetable+' tr').length-1);
             	$(options.showframe+' option:selected').each(function(){
             	    value.push($(this).val());
             	}); 
             	showindex=($(options.showframe+' option').length-1);
             	for(i=0;i!=-1;i++){
             	    if(value[i]!=null){
             	        rowindex=$('.'+value[i]).index();
             	        if(rowindex!=showindex){
             	            $(options.definetable+' th:eq('+rowindex+')').insertAfter($(options.definetable+' th:eq('+showindex+')'));
             	            for(j=0;j<=linenum;j++){
             	                $('td:eq('+rowindex+')',$(options.definetable+' tr:eq('+j+')')).insertAfter($('td:eq('+showindex+')',$(options.definetable+' tr:eq('+j+')')));
             	            }
             	            $(options.showframe+' option:eq('+rowindex+')').insertAfter($(options.showframe+' option:eq('+showindex+')')); 
             	        }
             	        else{
             	            break;
             	        }
             	    }
             	    else{
             	        break;
             	    }
             	}
            }
        });     
    }
})(jQuery);

/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-rowdefine:
@    data-raveal-id 为定义列按钮的类型，有'init','show','show_all','hide',
@    'hide_all','up','down','top','bottom'九种类型
@    'init'：      定义列初始化按钮
@	 'show':      单个栏位显示按钮
@    'show_all':  全部栏位显示按钮
@    'hide':      单个栏位隐藏按钮
@    'hide_all':  全部栏位隐藏按钮
@    'up':        栏位上移按钮（前移）
@    'down':      栏位下移按钮（后移）
@    'top':       栏位移至顶部按钮（移至第一列）
@    'bottom':    栏位移至底部按钮（移至最后一列）
@    非必需参数：
@    data-definetable:
@    data-definetable 为设置定义列功能的表格id
@    不设置此参数则默认表格id为'#id'
@    data-setbutton:
@    data-setbutton 为存放定义列初始化按钮的设置弹出框的id
@    不设置此参数则默认设置弹出框id为'#setting'
@    data-showframe:
@    data-showframe 为显示栏位存放的<select>标签的id
@    不设置此参数则默认id为'#show'
@    data-hideframe:
@    data-hideframe 为隐藏栏位存放的<select>标签的id
@    不设置此参数则默认id为'#hide'
@
@
***********************************************************/