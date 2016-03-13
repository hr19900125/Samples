$(document).ready(function(){
	
	$.formValidator.initConfig({onError:function(){alertDialog("校验没有通过，具体错误请看错误提示")}});
	$("#user_name").formValidator({onShow:"",onFocus:"用户名至少6个字符,最多20个字符",onCorrect:"",onSuccess:""}).inputValidator({min:1,max:20,onError:"姓名不能为空"});
	$("#telephone").formValidator({onShow:"",onFocus:"用户名至少6个字符,最多20个字符",onCorrect:"",onSuccess:""}).inputValidator({min:1,max:20,onError:"姓名不能为空"});
	$("#device_id").formValidator({onShow:"",onFocus:"",onCorrect:"",onSuccess:""}).inputValidator({min:1,max:32,onError:"设备不能为空"});
	$("#rfid").formValidator({onShow:"",onFocus:"",onCorrect:"",onSuccess:""}).inputValidator({min:1,max:32,onError:"rfID不能为空"});
	$("#first_guardian_phone").formValidator({onShow:"",onFocus:"",onCorrect:"",onSuccess:""}).inputValidator({min:1,max:32,onError:"rfID不能为空"});
	$("#birthday").datepicker(); 
	
//	$("#dialog-form").dialog({
//		autoOpen: false,
//		height: 300,
//		width: 350,
//		modal: true,
//		buttons: {
//			"确定": function() {
//				roleIds ='';
//				roleTextHtml = '';
//				$('input[name="role_checkbox"]:checked',$('#dialog-form')).each(function(){
//					roleIds += ($(this).attr('rid')+',');
//					roleTextHtml += ' <a href="role.htm?rid='+$(this).attr('rid')+'">'+$(this).attr('rname')+'</a> ';
//				});
//				$('#selectedIds').val(roleIds);
//				$('#role_list').html(roleTextHtml);
//				$(this).dialog("close");
//			}
//		},
//		close: function() {
//			$(this).dialog( "close" );
//		}
//	});
	
//	$('#role_select_button').click(function(){
//		
//		var selectedStr = $('#selectedIds').val();
//		var selectedIds = selectedStr.split(",");
//		$.each(selectedIds, function (index, tx) {
//			$('#user_role_'+tx).attr("checked",'true');
//		});
//		//选择角色
//		$("#dialog-form").dialog("open");
//	});
	
	$('#save_user').click(function(){
		
		if ($.formValidator.pageIsValid('1')==false) 
		{ 
			return;
		} 
		
		var userName = $('#user_name').val();
		var birthday = $('#birthday').val();
		var sex = $('input:radio[name="sex"]').val();
		var telephone = $('#telephone').val();
		var email = $('#email').val();
		var qq = $('#qq').val();
		var deviceID = $('#device_id').val();
		var rfid = $('#rfid').val();
		var firstGuardianPhone = $('#first_guardian_phone').val();
		var secondGuardianPhone = $('#second_guardian_phone').val();
		var thirdGuardianPhone = $('#third_guardian_phone').val();
		var schoolID = $('#school_select').val();
		var schoolName = $('#school_select').find('option:selected').text();
		var classID = $('#class_select').val();
		var className = $('#class_select').find('option:selected').text();
		
		$.post(
		  'saveUser.do',
		  {
			  'uid':telephone,
			  'username':userName,
			  'birthday':birthday,
			  'sex':sex,
			  'email':email,
			  'qq':qq,
			  'deviceID':deviceID,
			  'rfID':rfid,
			  'firstGuardianPhone':firstGuardianPhone,
			  'secondGuardianPhone':secondGuardianPhone,
			  'thirdGuardianPhone':thirdGuardianPhone,
			  'schoolID':schoolID,
			  'schoolName':schoolName,
			  'classID':classID,
			  'className':className
		  },
		  function(data){
			  if(data.resultCode==200){
				    var uid = parseInt(data.resultValue);
				    var param = '';
				    if(uid > 0){
				    	param = '?uid='+uid;
				    	location.href = 'user.htm'+param;
				    }
				}else{
					alertDialog("创建用户失败");
				}
		  },
		  'json'
		);
	});
	
	$('#back').click(function(){
		history.go(-1);
	});
	
	$('#school_select').change(function(){
		var sid = $(this).val();
		$.post(
	       'getClassesOfSchool.do',
	       {
	    	  'sid':sid
	       },
	       function(data){
	    	  if(data.resultCode == 200){
	    		 $('#class_select').val('');
	    		 $('#class_select').html('');
	    		 var list = data.resultValue;
	    		 for(var index in list){
	    			 $("#class_select").append('<option value ="'+list[index].ID+'">'+list[index].className+'</option>'); 
	    		 }
	    	  }
	       },
	       'json'
		);
	});
	
	$(document).ready(function(){ 
		
		var schoolID = $('#schoolID').val();
		var classID = $('#classID').val();
		if(schoolID == 0){
			schoolID = $('#school_select').val();
		}else{
		    $('#school_select').val(schoolID);
	    }
	    alert(schoolID);
		$.post(
	       'getClassesOfSchool.do',
	       {
	    	  'sid':schoolID
	       },
	       function(data){
	    	  if(data.resultCode == 200){
	    		 $('#class_select').val('');
	    		 $('#class_select').html('');
	    		 var list = data.resultValue;
	    		 for(var index in list){
	    			 $("#class_select").append('<option value ="'+list[index].ID+'">'+list[index].className+'</option>'); 
	    		 }
	    		 $('#class_select').val(classID);
	    	  }
	       },
	       'json'
		);
	}) 
	
});