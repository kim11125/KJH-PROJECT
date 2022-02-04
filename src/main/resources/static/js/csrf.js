var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
});

$.ajax({
			type: 'POST',
			contentType: "application/json",
			url:'/csrf/ajax',
			data: JSON.stringify(jsonData), // String -> json 형태로 변환
			beforeSend : function(xhr){   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(header, token);
      },
			dataType: 'json', // success 시 받아올 데이터 형
			async: true, //동기, 비동기 여부
			cache :false, // 캐시 여부
			success: function(data){
				console.log(data.name);
			},
			error:function(xhr,status,error){
				console.log('error:'+error);
			}
		});