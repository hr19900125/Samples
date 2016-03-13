function alertDialog(msg, title, okClickHanler, width, height) {
	var w = width || 300;
	var h = height || 200;
	var alertdiv = $(
			'<div id="dialog-confirm" title="'
					+ (title ? title : '')
					+ '"><p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'
					+ msg + '</p></div>').appendTo($('body'));
	var content = alertdiv.text(msg);
	alertdiv.dialog({
		autoOpen : true,
		height : h,
		width : w,
		modal : true,
		resizable : false,
		bgiframe : true,
		close : function(evt, ui) {
			alertdiv.dialog("destroy");
			alertdiv.html("").remove();
		},
		buttons : {
			"确定" : function() {
				if (okClickHanler && $.isFunction(okClickHanler)) {
					okClickHanler();
				}
				alertdiv.dialog("close");
			}
		}
	});
}

// 确认对话框
function confirmDialog(msg, title, okClickHanler, cancelClickHandler, width,
		height) {
	var w = width || 300;
	var h = height || 150;
	var confirmdiv = $(
			'<div id="dialog-confirm" title="'
					+ title
					+ '"><p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;">'
					+ msg + '</span></p></div>').appendTo($("body"));
	var content = confirmdiv.text(msg);
	confirmdiv.dialog({
		autoOpen : true,
		height : h,
		width : w,
		modal : true,
		resizable : false,
		bgiframe : true,
		close : function(evt, ui) {
			confirmdiv.dialog("destroy");
			confirmdiv.html("").remove();
		},
		buttons : {
			"确定" : function() {
				if (okClickHanler && $.isFunction(okClickHanler)) {
					okClickHanler();
				}
				confirmdiv.dialog("close");
			},
			"取消" : function() {
				if (cancelClickHandler && $.isFunction(cancelClickHandler)) {
					cancelClickHandler();
				}
				confirmdiv.dialog("close");
			}
		}
	});
}