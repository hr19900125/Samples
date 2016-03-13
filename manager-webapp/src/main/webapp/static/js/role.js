$(document).ready(function(){
	
	$('#submit_role').click(function(){
		var rid = $('#role_id').val();
		var id =  parseInt(rid);
		if(id > 0){
		   location.href = 'roleEdit.htm?rid='+rid;
		}else{
		   location.href = 'roleEdit.htm';
		}
	});
	
	$('#back_role').click(function(){
		location.href = 'roles.htm';
	});
});