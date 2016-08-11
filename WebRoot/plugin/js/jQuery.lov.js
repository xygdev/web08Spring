/*********************************************************
                    jQuery 页面弹出框功能
                    Create Date:2015.1.20
                    Create By:bird
                    Last Update Date:2016.7.13
                    Last Update By:bird
                          修改日志
           2015.1.20   创建文件
           2016.5.10   新增Lov取值函数
           2016.7.13   代码优化，并新增插件配置说明
*********************************************************/
(function($) {	
	/******************listener start***********************
    			监听 data-lovname 传递的值                      
		暂时只设置对<input> <button> <a> <i> 四种标签的监听绑定               
   				如需为其他更多元素增加点击弹出框效果                      
    			请在listener区域绑定新的监听标签
	 *******************************************************/
	$.fn.lovListener = function(){ 
		/****绑定<input>标签****/
		$('input[data-lovname]').on('click', function(e) {		
			$(this).lov($(this).data());
		});
		/****绑定<button>标签****/
		$('button[data-lovname]').on('click', function(e) {
			$(this).lov($(this).data());
		});
		/****绑定<a>标签****/
		$('a[data-lovname]').on('click', function(e) {
			e.preventDefault();/****阻止<a>标签默认的点击事件（超链接跳转）****/
			$(this).lov($(this).data());
		});
		/****绑定<i>标签****/
		$('i[data-lovname]').on('click', function(e) {
			$(this).lov($(this).data());
		});				
	}   
	/******************listener end***********************/	
	
	/****执行监听函数****/
	$().lovListener();
	
	
    $.fn.lov = function(options) {	
        /**************************
        		设置默认属性
		**************************/	    
    	var defaults={
    		lovsetting:'{"defaultquery":"","queryval":"","lovclass":"","title":"","querybox":"","contentbox":"","tablename":"","prevpage":"","nextpage":"","query":"","pageno":"","queryurl":"","jsontype":"","typetd":"","urltd":""}'
    	} 
    	/****继承默认属性****/
        var options = $.extend({}, defaults, options); 
    	
        return this.each(function() {  
        	/****Lov取值函数，将Lov表格中的值选入预更新框并关闭lov框****/        	        	
        	$.fn.choose = function(){
            	$(options.lovsetting.lovclass+' td').on('click', function() {
            		for(k=0;k<2;k++){
            			text=$(this).parent().children(options.choose[k]).text();
            			if(!text){
            				alert('不能选择空值');	
            				return;
            			}else{
            				$(options.recid[k]).val(text);
            			}   			
            		}
            		$(options.lovsetting.lovclass+' .'+options.dismissmodalclass).click();
            	});
        	}       	
        	$(options.lovsetting.title+' h1').text(options.lovname);
        	$(options.lovsetting.pageno).val('1');
        	$(options.lovsetting.prevpage).css('display','none');
        	$(options.lovsetting.nextpage).css('display','none');
        	$(options.lovsetting.typetd).val(options.lovsetting.jsontype);
        	$(options.lovsetting.urltd).val(options.lovsetting.queryurl);
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).html('');       	
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<tr>');
        	for(i=0;i!=-1;i++){
        		if(options.th[i]!=null){
        			$('tr:eq(0)',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<th>'+options.th[i]+'</th>');
        		}else{
        			break;
        			return i;
        		}
        	}
        	for(m=1;m<=10;m++){
        		$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<tr>');
        		for(n=0;n<i;n++){
        			$('tr:eq('+m+')',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).append('<td class=\''+options.td[n]+'\'></td>');
        		}
        	}
        	$(options.lovsetting.querybox+' select').html('');
        	$(options.lovsetting.queryval).val('');
        	$('table',$(options.lovsetting.lovclass+' '+options.lovsetting.contentbox)).attr('id',options.lovsetting.tablename);
        	for(j=0;j!=-1;j++){
        		if(options.selectname[j]!=null&&options.selectvalue[j]!=null){
        			$(options.lovsetting.querybox+' select').append('<option value='+options.selectvalue[j]+'>'+options.selectname[j]+'</option>');
        		}else{
        			break;
        		}
        	}
        	$().choose();
        	/****默认查询参数如果为true，则默认打开Lov时点击一次查询按钮****/
        	if(options.lovsetting.defaultquery==true){
        		$(options.lovsetting.query).click();
        	}
      	    width='-'+parseInt($(options.lovsetting.lovclass).css('width'))/2+'px';      	    
      	    $(options.lovsetting.lovclass).css('margin-left',width);   
        });    	
    }
})(jQuery);


/*****************************插件配置说明*****************************
@                              配置参数                             
@    必需参数：														 
@    data-lovname:
@    data-lovname 为lov弹出框的标题名称，用于标题栏中
@    data-lovsetting:
@    data-lovsetting 为页面设置json参数，包含的属性有{
@		defaultquery:lov默认查询属性，有true和false两个选项
@		queryval:查询条件框的id或class
@		lovclass:lov框的class
@	    title:lov的标题框的class
@		querybox:lov查询框的id或class
@		contentbox:lov内容框的id或class
@		tablename:lov的表格id
@       prevpage:上一页按钮id
@       nextpage:下一页按钮id
@       query:查询按钮的id或class
@		pageno:存放页码的html标签class或id
@       queryurl:lov条件查询的url
@       jsontype:lov的数据类型
@		urltd:存放url的html标签id(urltd属性主要用于lov查询)
@		typetd:存放jsontype的html标签的id(typetd属性主要用于lov查询)
@    }
@    data-th:
@    data-th 为一个Array数组格式属性，存放lov表格的表格头名称
@    data-td:
@	 data-td 为一个Array数组格式属性，存放lov表格的行td的class值
@    (data-td的顺序必须与data-th的顺序保持对应)
@    data-selectname： 
@    data-selectname 为一个Array数组格式属性
@    存放lov查询条件<select>选择框中的条件选项名
@    data-selectvalue： 
@    data-selectvalue 为一个Array数组格式属性
@    存放lov查询条件<select>选择框中的条件选项值
@    （选项值的存放顺序必须与选项名保持对应）
@    data-choose:
@    data-choose 为一个Array数组格式属性
@    存放lov要选取的值所对应的td的class
@    data-recid:
@    data-recid 为一个Array数组格式属性
@    存放lov要选取的值所要放置的目标位置的id或class
@    (data-recid中数据的顺序必须与data-choose中的顺序保持相对应)   
@
***********************************************************/