$(document).ready(function(){
	
	$('#submit_role').click(function(){
		var isEdit = $('#isEdit').val();
		var rid = $('#roleID').val();
		var roleName = $('#p_name').val();
		var roleDescr = $('#role_descr_area').val();
		var permissionIds = '';
		$('input[name="permission"]:checked').each(function(){
			permissionIds += ($(this).attr('pid')+',');
		});
		
		$.post(
			'saveRole.do',  //url
			{
			  'rid':rid,
			  'n':roleName,
			  'd':roleDescr,
			  'ids':permissionIds
			},
			function(data){
				if(data.resultCode==200){
					location.href = "role.htm?rid="+data.resultValue;
				}else{
					alert("创建角色失败");
				}
				
		    },
		    'json'
		);
		
	});
	
});