$(document).ready(function(){

	$('#submit_user').click(function(){
		var uid = $(this).attr('uid');
		location.href = 'userEdit.htm?uid='+uid;
	});
	
	$('#back_user').click(function(){
		location.href = 'users.htm';
	});
});