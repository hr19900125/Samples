$(function(){
	
	
	var pname = $( "#permission_name" ),pdescr = $( "#permission_descr" ),purl = $( "#permission_url" );
	//allFields = $( [] ).add( name ).add( email ).add( password ),
	//tips = $( ".validateTips" );
    var pid = 0;
    
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"创建": function() {
				var name = pname.val();
				var descr =pdescr.val();
				var url = purl.val();
				
				$.post(
					'savePrivilege.do',
					{
					   'pid':pid,
					   'ptype':name,
					   'pd':descr,
					   'au':url
					},
					function(data){
						if(data.resultCode == 200){
							location.reload();
						}else{
							alertDialog('创建失败');
						}
					},
					'json'
			    );
			},
			"取消": function() {
				$(this).dialog( "close" );
			}
		},
		close: function() {
			$(this).dialog( "close" );
		}
	});

	$( "#create_permission" ).click(function() {
		 pid = 0;
		 $( "#dialog-form" ).dialog( "open" );
		 pname.val('');
		 pdescr.val('');
		 purl.val('');
	});
//	
//	$('a[name="editPrivilege"]').click(function(){
//		 pid = $(this).attr('pid');
//		 $( "#dialog-form" ).dialog("open");
//	     pname.val($(this).attr('ptype'));
//		 pdescr.val($(this).attr('pdescr'));
//		 purl.val($(this).attr('purl'));
//	});
	
	$('a[name="deletePrivilege"]').click(function(){
		var id = $(this).attr('pid');
		confirmDialog('确定是否删除?', '删除', function(){
			//确定
			$.post(
			   'deletePrivilege.do',
			   {
				   'pid':id
			   },
			   function(data){
				   if(data.resultCode == 200){
					   location.reload();
				   }else{
					   alertDialog('删除失败');
				   }
			   },
			   'json'
			);
		},function(){
			//取消
			
		});
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