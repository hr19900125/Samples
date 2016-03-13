$(document).ready(function() {
	
	$('#edit_password').click(function(){
		
		var op = $('cur_password').val();
		var np = $('new_password').val();
		
		$.post(
	       'editPassword.do',
	       {'op':op,
	    	'np':np
	       },function(data){
	    	   
	    	   if(data.resultCode == 200){
		    		location.href = "users.htm";
		    	}else{
		    		alert("修改密码失败");
		    	}
	    	   
	       },'json'
		);
		
	});
	
});
