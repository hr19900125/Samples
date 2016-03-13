$(document).ready(function() {

	$('#save_class').click(function(){
		var cid = $('#classID').val();
		var className = $('#class_name').val();
		var schoolName = $("#school_select").find("option:selected").text()
		var schoolId = $('#school_select').val();
		$.post(
		    'saveClass.do',
		    {
		    	'cid':cid,
		    	'className':className,
		    	'schoolId':schoolId,
		    	'schoolName':schoolName
		    },
		    function(data){
		    	if(data.resultCode == 200){
		    		location.href = "classes.htm";
		    	}else{
		    		alert("创建班级失败");
		    	}
		    },
		    'json'
	    );
	});
	
	
});