$(document).ready(function() {

	$('a[name="deleteUser"]').click(function() {
		var uid = $(this).attr('uid');
		confirmDialog('确定是否删除?', '删除', function() {
			$.post('deleteUser.do', {
				'uid' : uid
			}, function(data) {
				if (data.resultCode == 200) {
					location.reload();
				} else {
					alertDialog('删除用户失败');
				}
			}, 'json');
		}, function() {

		});
	});

	$('#add_user').click(function() {
		location.href = "userEdit.htm";
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
		location.href = "users.htm?p=" + page;
	});
});