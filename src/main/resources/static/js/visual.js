/**
 *	작성자 : 김정현
 *	작성일 : 2022/01/14
 */

var myTimeout; 
var speed=500;
var imgSize=1200; 
var delay=2000;
var images; 
var flag=0;


$(function(){ 
	dbImgLoading();
});
function dbImgLoading(){
	$.ajax({
		url: "/visuals",
		success: function(result){
			$("#visual>.wrap>.visual-area").html(result);
			//태그가 완성된이후에 처리해야함..
			visualController();
		}
	});
}

function visualController(){
var i=0;
var pos=1;
var buls=$("#visual .visual-area .bullets .bullet"); //li태그
buls.eq(i).addClass("target");

images=$("#visual .visual-area .images"); //ol태그
var imgs=$("#visual .visual-area .images .img");//li태그 개수
//ol태그의 사이즈 정의
images.css("width",imgSize * imgs.length);
//페이지로딩되면 타이머시작
myTimeout = setTimeout(start, delay+2000);


//브라우저 최소화되었을때 타이머 스톱
document.addEventListener("visibilitychange", function() {
	console.log( document.visibilityState );
	if(document.visibilityState=="hidden"){ stop(); }else{ 
		myTimeout = setTimeout(start, delay);
	 }
});


$("#visual .visual-area").hover(function(){
	stop();
}, function(){
	myTimeout = setTimeout(start, delay);
});

$(".btn>.next").click(function(){
	next();
})

$(".btn>.prev").click(function(){
	prev();
})

//블릿을 클릭했을때 
buls.click(function(){
	var clicked_idx=$(this).index();
	console.log("클릭된 블릿 인덱스 번호 : "+clicked_idx);
	
	var img_idx=images.find(".img_"+clicked_idx).index();
	console.log("이미지위 위치 번호 : "+img_idx);
	
	buls.removeClass("target");
	//클릭된 블릿의 스타일
	buls.eq(clicked_idx).addClass("target")
	i=clicked_idx;
	//이미지 이동
	pos=img_idx;
	//이미지 이동
	imgMove();
	
});


function stop(){
	clearTimeout(myTimeout);
}
function start(){
	stop();
	next();
	myTimeout = setTimeout(start, delay);
	
}

//왼쪽이미지를 오른쪽으로 한번만 이동시킨다.
function prev(){
	if(flag==1)return;
	flag=1;
	//첫번째 이미지 
	var first_li=$("#visual .visual-area .images .img:first-child"); 
	//마지막 이미지 
	var last_li=$("#visual .visual-area .images .img:last-child");
	//첫번째 img 앞쪽에 마지막 img 를 이동
	first_li.before(last_li);
	images.css("margin-left" , -imgSize);
	pervBullet();
	images.animate({marginLeft: 0},speed,function(){
		flag=0;
	});
	
}

//오른쪽이미지를 왼쪽으로 한번만 이동시킨다.
function next(){ 
	//블릿도 이동
	nextBullet();
	//이미지 이동
	imgMove(); 
}

function pervBullet(){
	//자동으로 이동할때
	buls.removeClass("target");
	i = --i % imgs.length;	// 0~3
	buls.eq(i).addClass("target")
}
function nextBullet(){
	//자동으로 이동할때
	buls.removeClass("target");
	i = ++i % imgs.length;	// 0~3
	buls.eq(i).addClass("target")
}

function imgMove(){ 
	if(flag==1)return;
	flag=1;
	//이미지 이동
	images.animate({marginLeft: -(imgSize*pos)}, speed, function(){
		//이미지가 이동한다음 실행
		for(var k=0; k<pos; k++){
			var first_li=$("#visual .visual-area .images .img:first-child"); 
			var last_li=$("#visual .visual-area .images .img:last-child");
			last_li.after(first_li);
		}
		images.css("margin-left" , 0); 
		flag=0;
		pos=1;
	}); 
}
}