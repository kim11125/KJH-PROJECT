/**
 *	작성자 : 김정현
 *	작성일 : 2022/01/14
 */

var myTimeout;
var speed = 1000;
var imgSize = 1200;
var delay = 1000;
var imgs;

//이미지
var imgurls = [
	"/images/visual/main-ban1.jpg", 
	"/images/visual/main-ban2.jpg", 
	"/images/visual/main-ban3.jpg", 
	"/images/visual/main-ban4.jpg", 
	"/images/visual/main-ban5.jpg", 
	"/images/visual/main-ban6.jpg", 
];

//브라우저 최소화되었을때 타이머 스톱
document.addEventListener("visibilitychange", function() {
	//console.log( document.visibilityState );
	if(document.visibilityState=="hidden"){ stop(); }else{ start(); }
});

$(function(){ 
	imgLoading();
	
	imgs.hover(function(){
		stop();
	}, function(){
		start();
	});
	
});

function stop(){
	clearTimeout(myTimeout);
}
function start(){
	myTimeout = setTimeout(move, delay);
}


function imgLoading(){
	imgs=$("#visual>.visual-area .images");
	var lis=$("#visual>.visual-area .images .img"); 
	for(var i=0; i < imgurls.length ; i++){
		lis.eq(i).css("background-image", "url("+imgurls[i]+")" )
	}
	imgs.css("width",imgSize * imgurls.length);
	
	start();
}



function move(){ 
	//첫번째 이미지 
	var first_li=$("#visual>.visual-area .images li:first-child"); 
	//마지막 이미지 
	var last_li=$("#visual>.visual-area .images li:last-child"); 
	imgs.animate({marginLeft: -imgSize}, speed, function(){
		//이미지가 이동한다음 실행
		last_li.after(first_li);
		imgs.css("margin-left" , 0); 
		
		start();
	}); 
}