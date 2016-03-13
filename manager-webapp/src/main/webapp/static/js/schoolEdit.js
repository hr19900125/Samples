$(document).ready(function(){
//	schoolName=,provinceId=SD4W5AlLTlmmXhf7MhNRyrHzfPg=,cityId=uIMcazYzTxeHdTDr+k7rilC+DzM=,countyId=sPMtJV1tSjWdxHYwqNR2w1C9RlU=,region=四川省-成都市-锦江区 p=undefined,c=undefined,a=undefined
	$("body").find(".proCityQuery,.proCityQueryAll").val($('#school_region').val()==''?'请选择/输入城市名称':$('#school_region').val());
	$('#submit_school').click(function(){
		var sid = $('#schoolID').val();
        var schoolName = $('#s_name').val();
        if(schoolName == ""){
        	return;
        }
        var schoolRegion = $('#city_select').val();
        var provinceId = $("body").data("pAllId");
        var cityId = $("body").data("cAllId");
        var countyId = $("body").data("aAllId");
		var region = $("input.current2").val();
//		alertDialog("schoolName="+schoolName+",provinceId="+provinceId+","+"cityId="+cityId+",countyId="+countyId+",region="+region+" p="+$("body").data("pId")+",c="+$("body").data("cId")+",a="+$("body").data("aId")+",ss="+ss);
		$.post(
			'saveSchool.do',  //url
			{
			  'sid':sid,
			  'schoolName':schoolName,
			  'provinceID':provinceId,
			  'cityID':cityId,
			  'countyID':countyId,
			  'region':region
			},
			function(data){
				if(data.resultCode==200){
//					location.href = "school.htm?sid="+data.resultValue;
					location.href = "schools.htm"
//					alert("创建成功");
				}else{
					alert("创建学校失败");
				}
				
		    },
		    'json'
		);
		
	});
	
});