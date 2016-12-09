<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/comm/path-param.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/comm/static-resource.jsp"></jsp:include>
<jsp:include page="/comm/tfs-static-resource.jsp"></jsp:include>
</head>
<body>
<form action="<%=basePath%>/loan/credit/submitCreditApply.shtml" id="crditApplyForm" method="post">
	
	<input type="hidden" name="companyData" id="companyData" value=""/>
	<input type="hidden" name="companyAppendData" id="companyAppendData" value=""/>
	<input type="hidden" name="companyEnsureData" id="companyEnsureData" value=""/>
	<input type="hidden" name="companyAccessoryData" id="companyAccessoryData" value=""/>
	
	<div id="company">
	
	</div>
	
	<div id="companyAppend">
	
	</div>
	
	<div id="companyEnsure">
	
	</div>
	
	<div id="companyAccessory">
	
	</div>

</form>
<jsp:include page="/comm/static-js.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>/js/ajaxfileupload.js"></script>
</body>
<script type="text/javascript">
	beat();//心跳
	var creditBasePath = '<%=basePath%>';
	var imageBasePath = '<%=cssSaasPath%>';
	
	$(function(){
	
		$.ajax({
			async : false,
			type : 'get',
			url : creditBasePath + '/loan/credit/getCreditData.shtml'+"?DateTime="+new Date().getTime(),
			dataType : 'json',
			success : function(data) {
				if(data)
				{
					navObj.navList[0]['data'] =  data['creditCompany'];
					
					if(data['companyAppendInfo'])
					{
						navObj.navList[1]['data']["controllDatas"] = data['companyAppendInfo']['controllDatas'];
						navObj.navList[1]['data']["cooperationCompanyInfos"] = data['companyAppendInfo']['cooperationCompanyInfos'];
						navObj.navList[1]['data']["mainBusinessDatas"] = data['companyAppendInfo']['mainBusinessDatas'];
						navObj.navList[1]['data']["companyLease"] = data['companyAppendInfo']['companyLease'];
					}
					
					if(data['creditOrder'])
					{
						navObj.navList[2]['data']["assureType"]=data['creditOrder']["assureType"];	
					}
					navObj.navList[2]['data']["companyEnsure"] =  data["companyEnsure"];
					navObj.navList[2]['data']["loanPersonEnsure"] =  data["loanPersonEnsure"];
					if(data['creditCompany'])
					{
						navObj.navList[3]['data']["creditCompany"] = data['creditCompany'];
						navObj.navList[3]['data']["companyEnsure"] = data['companyEnsure'];
						navObj.navList[3]['data']["loanPersonEnsure"] = data['loanPersonEnsure'];
					}
					
					
				}
			},
			error:function(){
					new Tip({msg : '数据加载失败！',type : 3 });
			}
		});
		navObj.show();
	});
	
	
	//导航对象
	var navObj = {
		
		 currIndex: 0,
			
		 navList : [{zoneId:'company' , url:creditBasePath+'/loan/credit/applyCompany.shtml' ,showEventHandle:'', 
			 		render:'companyRender', afterDone :"afterDoneCompany",dataVlidate:'vlidateCompany' , data:{} } ,
		            {zoneId:'companyAppend' , url:creditBasePath+'/loan/credit/applyCompanyAppend.shtml', showEventHandle:'',
			 			render:'companyAppendRender' ,  afterDone :"afterDoneCompanyAppend",dataVlidate:'vlidateCompanyAppend' ,data:{}} , 
		            {zoneId:'companyEnsure' , url:creditBasePath+'/loan/credit/applyCompanyEnsure.shtml' , showEventHandle:'', 
			 				render:'companyEnsureRender',afterDone :"afterDoneCompanyEnsure",dataVlidate:'vlidateCompanyEnsure' ,data:{}} , 
		            {zoneId:'companyAccessory' , url:creditBasePath+'/loan/credit/applyCompanyAccessory.shtml' ,showEventHandle:'companyAccessoryShow',
			 					render:'companyAccessoryRender',afterDone :"afterDoneAccessory", dataVlidate:'vlidateCompanyAccessory' ,data:{}}],
		            
		show: function()
		{
			var obj = navObj.navList[navObj.currIndex];
			if(obj )
			{
				var isload = $('#' + obj.zoneId).attr('isload');
				if(isload === 'true'){
					$('div[isload=true]').hide();
					$('#' + obj.zoneId).show();
					if(obj["showEventHandle"] && obj["showEventHandle"] !='')
					{
						eval(obj["showEventHandle"]+"()");
					}
				}else{
					F.loading.show();
				 	 $.ajax({
				        dataType : 'html',
				        context: document.body,
				        url : obj.url+"?DateTime="+new Date().getTime() ,
				        success : function(html){
				        	
				        	 $('#' + obj.zoneId).html(html);
				        	 $('div[isload=true]').hide();
				        	 
				        	 if(obj.render)
				        	 {
// 				        		 try  {
				        	 		eval(obj.render+"('"+JSON.stringify( obj.data)+"','"+creditBasePath+"')");
// 								 }catch(e)
// 								 {
// 									 alert(e);
// 								 }
				        	 }
				        	 
				        	 if(obj["showEventHandle"] && obj["showEventHandle"] !='')
		 					 {
		 						eval(obj["showEventHandle"]+"()");
		 					 }
				        	 $('#' + obj.zoneId).attr('isload','true');
							 $('#' + obj.zoneId).show();
							 setTimeout(function(){
								 dataHandleObj.restoreData( obj.data ,  obj.zoneId);
								 
								 if(obj["afterDone"] && obj["afterDone"] !='')
			 					 {
			 						eval(obj["afterDone"]+"()");
			 					 }
								 F.loading.hide();
							 } , 1000);
				        },
				        error:function(){
				        	new Tip({msg : '页面加载失败！',type : 3 });
				        }
				    });
				}
			}
		},
		
		backMain: function(){
			window.location.href=creditBasePath+'/loan/credit/checkCreditStatus.shtml'+"?DateTime="+new Date().getTime();
		},
		
		next : function() {
			
			var data = {};
			
			var obj = navObj.navList[navObj.currIndex];
			data[obj.zoneId] = dataHandleObj.serializeData(obj.zoneId);
			var result = true;
			if(data[obj.zoneId]  != obj['data'])
			{
				if(obj["dataVlidate"])
				{
					try
					{
						eval("var vlidateResult = "+obj["dataVlidate"]+"()");
						if(vlidateResult === false)
						{
							return;
						}
					}catch(e){}
				}
				result = dataHandleObj.saveData(data)
			}
			
			if(result)
			{
				obj['data'] = data[obj.zoneId];
				navObj.currIndex++;
				navObj.show();
			}
		},

		prev : function() {
			var data = {};
			var obj = navObj.navList[navObj.currIndex];
			data[obj.zoneId] = dataHandleObj.serializeData(obj.zoneId);
			var result = true;
			if(data[obj.zoneId]  != obj['data'])
			{
// 				if(obj["dataVlidate"])
// 				{
// 					try
// 					{
// 						eval("var vlidateResult = "+obj["dataVlidate"]+"()");
// 						if(vlidateResult === false)
// 						{
// 							return;
// 						}
// 					}catch(e){}
// 				}
				result = dataHandleObj.saveData(data);
			}
			
			if(result)
			{
				obj['data'] = data[obj.zoneId];
				navObj.currIndex--;
				navObj.show();
			}
		}
	};

	//数据处理对象
	var dataHandleObj = {
		
		filterValue: function(obj)
		{
			if(obj.val() == null || obj.val()=='null') 	{
				return '';
			}
			
			var value =  obj.val();
			if(obj.is('select'))
			{
				var filterList = ['请选择'];
				for(var i=0; i<filterList.length;i++)
				{
					value = value.replace(filterList[i],""); 
				}
			}
			
			if(obj.is('input[type="radio"]'))
			{
				var groupName = obj.attr("name");
				value = $('input[name="'+groupName+'"]:checked ').val();
			}
			return value;
		},
		
		setObjValue:function(obj , newValue)
		{
			if(newValue == null || newValue=='null' || newValue == '') 	{
				return ;
			}
			if(obj.is('input[type="radio"]'))
			{
				var groupName = obj.attr("name");
				$('input[name="'+groupName+'"][value="'+newValue+'"]').prop("checked", "checked");
				$('input[name="'+groupName+'"][value="'+newValue+'"]').click();
				$('input[name="'+groupName+'"][value="'+newValue+'"]').change();
			}else
			{
				
				if(newValue != null &&  newValue != "" && obj.is('select') && typeof(newValue)=='string')
				{
					if(newValue.substring(0 , 1) == '0')
					{
						newValue = parseInt(newValue);
					}
				}
				
				obj.val(newValue);
				if(obj.is('select'))
				{
					obj.change();
				}
			}
		},
		
		serializeObj:function(node)
		{
			var obj = {};
			//单个解析
			node.find('[field]').each(function(){
				
				var parent = $(this).parents("[fieldObj]");
				
				if(parent.length > 0 &&  node.html() != parent.html())
				{
					return;
				}
				
				var fieldName = $(this).attr("field");
				obj[fieldName] = dataHandleObj.filterValue($(this));
			});
			
			//解析数组
			node.find('[fields]').each(function(){
				
				var parent = $(this).parents("[fieldObj]");
				
				if(parent.length > 0 && node.html() != parent.html())
				{
					return;
				}
				
				var fieldName = $(this).attr("fields");
				obj[fieldName] = '';
				var split =$(this).attr('split');
				
				$(this).find('[item]').each(function(){
					
					if(obj[fieldName] != '')
					{
						obj[fieldName] = obj[fieldName]+ split;
					}
					obj[fieldName] = obj[fieldName]+ dataHandleObj.filterValue($(this));
				});
			});
			
			return obj;
		},
		
		serializeChildObj:function(node , obj)
		{
			node.find('[fieldObj]').each(function(){
				var parent = $(this).parents("[fieldObj]");
				if(parent.length > 0 && node.html() != parent.html())
				{
					return;
				}
				var fieldName = $(this).attr("fieldObj");
				var sobj = dataHandleObj.serializeObj( $(this));
				
				//如果又子对象，那么继续序列化咯
				if($(this).find('[fieldObj]').length > 0)
				{
					dataHandleObj.serializeChildObj($(this) , sobj);
				}
				//初始化
				if(!obj[fieldName])
				{
					obj[fieldName]= node.find('[fieldObj='+fieldName+']').length > 1 ? new Array() : {};
				}
				
				if ( $.isArray(obj[fieldName])) {
					obj[fieldName][obj[fieldName].length] = sobj;
				}
				else{
					obj[fieldName]= sobj;
				}
			});
		},
			
		serializeData:function(zoneId){
			
			var obj = dataHandleObj.serializeObj($('#'+zoneId));
			dataHandleObj.serializeChildObj($('#'+zoneId) , obj);
			return JSON.stringify(obj);		
		},
		
		restoreObj:function(obj , node)
		{
			if(node.length <= 0)
			{
				return;
			}
			for ( var key in obj)
			{
				var value = obj[key];
				if ($.isPlainObject(value) || $.isArray(value)) 
				{
					if($.isArray(value))
					{	
						var tempnodes = node.find('[fieldObj='+key+']');
						for(var i=0;i<value.length;i++)
						{
							if(tempnodes.length > i)
							{
								dataHandleObj.restoreObj(value[i] ,	$(tempnodes[i]));
							}
						}
					}else
					{
						var tempNode = node.find('[fieldObj='+key+']');
						dataHandleObj.restoreObj(value , tempNode);
					}
				}
				else
				{
					var tempObj =node.find('[field=' + key + ']');
					if (tempObj.length > 0) {
						dataHandleObj.setObjValue(tempObj, value);
					} else {
						tempObj = node.find('[fields=' + key + ']');
	
						if (tempObj.length > 0) {
							var splitValues = new Array();
							splitValues[splitValues.length] = value;
	
							if ($(tempObj).attr('split')) {
								splitValues = value.split($(tempObj).attr('split'));
							}
	
							tempObj.find('[item]').each(function(i, n) {
								if (i < splitValues.length) {
									dataHandleObj.setObjValue($(this),splitValues[i]);
								}
							});
						}
					}
				}
			}
		},
		restoreData:function(obj , zoneId)
		{
			dataHandleObj.restoreObj(obj, $('#' + zoneId));
		},

		saveData : function(data) {
			var result = false;
			$.ajax({
				async : false,
				type : 'post',
				data : {
					"companyData" : data['company'],
					"companyAppendData" : data['companyAppend'],
					"companyEnsureData" : data['companyEnsure'],
					"companyAccessoryData" : data['companyAccessory']
				},
				url : creditBasePath + '/loan/credit/saveCreditData.shtml'+"?DateTime="+new Date().getTime(),
				dataType : 'json',
				success : function(obj) {
					if (obj.code == 1) {
						result = true;
					} else {
						result = false;
						new Tip({msg :obj.msg,type : 3 });
					}
				},
		        error:function(){
		        	new Tip({msg : '数据保存失败！',type : 3 });
		        }
			});
			return result;
		}
	};
	
	
	
	function validateSelects(value, customArgs, inputDom)
	{
		var result = true;
		
		if ($(inputDom).is(":hidden")) {
			return result;
		}

		$(inputDom).find("[item]").each(function() {

			if ($(this).is(":hidden") || $(this).parents().is(":hidden")) {
				return;
			}
			var value = $(this).val();
			if (value == '' || value == '请选择' || value == null) {
				result = false;
				return result
			}
		});
		return result;
	}

	function validateTextValue(value, customArgs, inputDom)
	{
		if ($(inputDom).is(":hidden")
				|| $(inputDom).parents().is(":hidden")) {
			return true;
		}
		
		if(customArgs !=null && customArgs !='')
		{
			eval('var pattern=' + customArgs);
			if(pattern.test(value))
			{
				return true;
			}else{
				return false;
			}
		}
		
		if (value == '' || value == null) {
			return false;
		}
		return true;
	}
	
	function validateSelect(value, customArgs, inputDom) {
		
		if ($(inputDom).is(":hidden") 
				|| $(inputDom).parents().is(":hidden")) {
			return true;
		}
		
		if (value == '' || value == '请选择' || value == null) {
			return false;
		}
		return true;
	}

	function submitCrditApply()
	{
		$("#companyData").val(dataHandleObj.serializeData('company'));
		$("#companyAppendData").val(dataHandleObj.serializeData('companyAppend'));
		$("#companyEnsureData").val(dataHandleObj.serializeData('companyEnsure'));
		$("#companyAccessoryData").val(dataHandleObj.serializeData('companyAccessory'));
		
		
		try {
			eval("var vlidateResult = vlidateCompanyAccessory()");
			if (vlidateResult === false) {
				return;
			}
		} catch (e) {
		}

		$('#crditApplyForm').submit();
	}
</script>
</html>