$(document).ready(function(){
	
	$(document).keyup(function(event){
		  if(event.keyCode ==13){
		    $("#login_button").trigger("click");
		  }
    });
	
	$('#login_button').click(function(){
		var username = $('#user_name').val();
		var type = $('input[name="login_type"]:checked').val();
		$('#h_user_name').val(username+';'+type);
	});
	
});