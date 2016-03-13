$(document).ready(function() {

	$('#create_school').click(function(){
		location.href = "schoolEdit.htm";
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
	
	$('a[name="delete_school"]').click(function(){
		var sid = $(this).attr("sid");
		
		confirmDialog('确定是否删除?', '删除', function() {
			$.post('deleteSchool.do', {
				'sid' : sid
			}, function(data) {
				if (data.resultCode == 200) {
					location.reload();
				} else {
					alertDialog('删除学校失败');
				}
			}, 'json');
		}, function() {

		});
	});
});