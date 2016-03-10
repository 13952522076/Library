<!--时分插件-->
<!--by maxu @Sencloud Team on 2015.4.14-->
<script>
	$().ready(function(){
		var startTime=$("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime!=""){
			var times=getHourAndMin(startTime);
			var startHour = times[0];
			var startMin = times[1];
			$("#startHour"+startHour+"").attr("selected",true);
			$("#startMin"+startMin+"").attr("selected",true);
		}
		if(endTime!=""){
			var times=getHourAndMin(endTime);
			var endHour = times[0];
			var endMin = times[1];
			$("#endHour"+endHour+"").attr("selected",true);
			$("#endMin"+endMin+"").attr("selected",true);
		}
	});
	//拆分时分
	function getHourAndMin(time){
		var times= new Array(); //定义一数组 
		times=time.split(":"); //字符分割 
		return times;
	}
	//改变时间
	function changeTime(){
		validateTime();
		joinTime();
	}
	//拼接时间，设置时间
	function joinTime(){
		var startTime = $("#startHour").val()+":"+$("#startMin").val();
		$("#startTime").val(startTime);
		var endTime = $("#endHour").val()+":"+$("#endMin").val();
		$("#endTime").val(endTime);
	}
	//判断开始时间小雨结束时间
	function validateTime(){
		var startTamp = parseInt($("#startHour").val())*60;
		startTamp = startTamp + parseInt($("#startMin").val());
		var endTamp = parseInt($("#endHour").val())*60;
		endTamp = endTamp + parseInt($("#endMin").val());
		if(startTamp>endTamp){
			$("#timeOverError").fadeIn(1000);
			$("#endHour").val($("#startHour").val());
			$("#endMin").val($("#startMin").val());
			$("#timeOverError").fadeOut(4000);
		}
	}
</script>
<input type="hidden" name="startTime" id="startTime"  value="${course.startTime}">
<input type="hidden" name="endTime" id="endTime"  value="${course.endTime}">
<div class="col-sm-8">
	<select id="startHour" onchange="changeTime()">
		[#list 1..24 as startHour]
		<option id="startHour${startHour}" value="${startHour}">${startHour}</option>
		[/#list]
	</select>
	:
	<select id="startMin" onchange="changeTime()">
		[#list 0..59 as startMin]
		[#if startMin<10]
		<option id="startMin0${startMin}" value="0${startMin}">0${startMin}</option>
		[#else]
		<option id="startMin${startMin}" value="${startMin}">${startMin}</option>
		[/#if]
		[/#list]
	</select>
	至
	<select id="endHour" onchange="changeTime()">
		[#list 1..24 as endHour]
		<option id="endHour${endHour}" value="${endHour}">${endHour}</option>
		[/#list]
	</select>
	:
	<select id="endMin" onchange="changeTime()">
		[#list 0..59 as endMin]
		[#if endMin<10]
		<option id="endMin0${endMin}" value="0${endMin}">0${endMin}</option>
		[#else]
		<option id="endMin${endMin}" value="${endMin}">${endMin}</option>
		[/#if]
		[/#list]
	</select>
	<div style="color:red;display:none;"id="timeOverError">
		开始时间超过结束时间
	</div>
</div>