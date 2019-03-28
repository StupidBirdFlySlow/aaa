/*头部js*/
$(function() {
	$(".nav_ul").find('li').each(function() {
		$(this).mouseover(function() {
			$(this).addClass("on");
			$(this).siblings().removeClass("on");
		})
		$(this).mouseout(function() {
			$(this).removeClass("on");
		})
	});
	$("#userheadInfo").mouseover(function() {
		$(".user_info_w").addClass("changeColor");
		$("#usernamecolor").css("color", "black");
		$(".settings").show();

	});
	$("#userheadInfo").mouseout(function() {
		$(".user_info_w").removeClass("changeColor");
		$("#usernamecolor").css("color", "white");
		$(".settings").hide();
	});
	
	$("#search_input").blur(function(){
		$(".search_result").hide();
	})
})

function suggestions(){
	$(".search_result").show();
	$(".search_result").empty();
	console.log($("#search_input").val());
	$.ajax({
		url:'http://localhost/design/search.json',
		type:'post',
		data:{keywords:$("#search_input").val()},
		success:function(result){
			var size=result.idList.length;
			var idList=result.idList;
			var nameList=result.nameList;
			console.log(size);
			for(var i=0;i<size;i++){
				$(".search_result").append("<a href='http://localhost/design/healthyRecipes/details/"+idList[i]+".html' class='suggestion_item'>"+nameList[i]+"</a>")
			}
		}
	})
}
