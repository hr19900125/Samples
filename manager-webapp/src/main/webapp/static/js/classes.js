$(document).ready(function() {

	$('#create_class').click(function(){
		location.href = "classEdit.htm";
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
		location.href = "classes.htm?p=" + page;
	});
	
	$('a[name="delete_class"]').click(function(){
		var cid = $(this).attr("cid");
		
		confirmDialog('确定是否删除?', '删除', function() {
			$.post('deleteClass.do', {
				'cid' : cid
			}, function(data) {
				if (data.resultCode == 200) {
					location.reload();
				} else {
					alertDialog('删除班级失败');
				}
			}, 'json');
		}, function() {

		});
	});
});