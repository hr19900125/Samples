$(document).ready(function(){
	
	$('a[name="deleteRole"]').click(function(){
		var rid = $(this).attr("rid");
		
		confirmDialog('确定是否删除?', '删除', function() {
			$.post(
				'deleteRole.do', 
				{
				  'rid' : rid
			    }, 
			    function(data) {
				  if (data.resultCode == 200) {
					location.href = "roles.htm";
				  } else {
					alert("删除角色失败");
				  }
			    }, 'json');
		}, function() {

		});
	});
	
	$('#add_role').click(function(){
		location.href = "roleEdit.htm";
	});
	
	$("#go_to_page_input").keyup(function() { // keyup事件处理
		$(this).val($(this).val().replace(/\D|^0/g, ''));
	}).bind("paste", function() { // CTR+V事件处理
		$(this).val($(this).val().replace(/\D|^0/g, ''));
	}).css("ime-mode", "disabled"); // CSS设置输入法不可用

	$('#go_to_page').click(function() {
		var page = $('#go_to_page_input').val();
		if (page == '') {
			return;
		}
		location.href = "schools.htm?p=" + page;
	});
	
});