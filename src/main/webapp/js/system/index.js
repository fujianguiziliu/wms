$(function(){
	//console.log($("#dleft_tab1 a"));
	$("#dleft_tab1 a").click(function(){
		//1.获取到自定义的action
		var action = $(this).data("action");
		//2.根据id获取到iframe组件
		$("#rightMain").prop("src",action);
	});
	
	$("#TabPage2 li").click(
		function(){
			//清除所有的li上面的selected样式
			$.each($("#TabPage2 li"),function(index,item){
				//清除样式
				$(item).removeClass("selected")
				//还原图片
				$(this).find("img").prop("src","/images/common/"+(index+1)+".jpg");
			})
			//修改样式
			$(this).addClass("selected");
			//修改图标
			$(this).find("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
			//修改标题
			$("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png")
			//加载菜单列表
			loadMenu($(this).data("rootmenu"));
		}	
	
	);
})

/*
var setting = {	};

		var zNodes =
			[
			{ name:"业务模块", open:true,
				children: [
					{ name:"货品",},
					{ name:"仓库",},
					{ name:"客户",},
					{ name:"销量",}
					]
				},
			{ name:"系统模块", open:true,
				children: [
					{ name:"员工管理",},
					{ name:"员工管理",},
					{ name:"权限管理",},
					{ name:"角色管理",},
					{ name:"系统菜单管理",}
					]
				},
				{ name:"报表模块", open:true,
					children: [
						{ name:"即时库存表",},
						{ name:"销售报表",},
						{ name:"订货报表",}
						
						]
					}
			];
		$(document).ready(function(){
			$.fn.zTree.init($("#dleft_tab1"), setting, zNodes);
		});
		*/
		
		
		
		var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
			callback:{
				onClick:function(event,treeId,treeNode){
//					console.log(event); 
//					console.log(treeId); 
//					console.log(treeNode);
					if(treeNode.action){
						//获取action地址
						var action=treeNode.action;
						//改变frame里的src属性
						$("#rightMain").prop("src","/"+action+".action");
					//修改当前位置
						$("#here_area").html("当前位置："+treeNode.getParentNode().name+"&nbsp;>&nbsp;"+treeNode.name);
					}
					
				}
				
			}	
};

			/*var zNodes =[
			    {id:1, pId:0, name:"业务模块", open:true},
			    {id:11, pId:1, name:"货品"},
			    {id:12, pId:1, name:"仓库"},
			    {id:13, pId:1, name:"客户"},
				{ id:2, pId:0, name:"系统模块", open:true},
				{ id:21, pId:2, name:"员工管理"},
				{ id:22, pId:2, name:"部门管理"},
				{ id:23, pId:2, name:"权限管理"},
				{ id:24, pId:2, name:"角色管理"},
				{ id:25, pId:2, name:"系统菜单管理"},
				{ id:3, pId:0, name:"报表模块", open:true},
				{ id:31, pId:3, name:"即时库存表"},
				{ id:32, pId:3, name:"销售报表"},
				{ id:33, pId:3, name:"订货报表"},
				
			];
*/
			zNode1=[
			        {id:1, pId:0, name:"业务模块", open:true},
				    {id:11, pId:1, name:"货品"},
				    {id:12, pId:1, name:"仓库"},
				    {id:13, pId:1, name:"客户"},  
			        
			        ];
			zNode2=[
{ id:2, pId:0, name:"系统模块", open:true},
{ id:21, pId:2, name:"员工管理",action:"employee"},
{ id:22, pId:2, name:"部门管理",action:"department"},
{ id:23, pId:2, name:"权限管理",action:"permission"},
{ id:24, pId:2, name:"角色管理",action:"role"},
{ id:25, pId:2, name:"系统菜单管理",action:"systemMenu"},
			        
			        ];
			zNode3=[
{ id:3, pId:0, name:"报表模块", open:true},
{ id:31, pId:3, name:"即时库存表"},
{ id:32, pId:3, name:"销售报表"},
{ id:33, pId:3, name:"订货报表"}, 
			        
			        ]
			zNodes={
				"business":zNode1,
				"systemManage":zNode2,
				"charts":zNode3
					
			}			
			
			/*$(document).ready(function(){
				$.fn.zTree.init($("#dleft_tab1"), setting, zNode1);
			});*/
	function loadMenu(sn){
				
				$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
			}	
	$(function(){
		loadMenu("business");
	})		
/* zTree插件加载目录的处理  
var zTree;

var setting = {
	callback : {
		beforeClick : function(treeId, treeNode) {
			$("#rightMain").attr("src", treeNode.path);
			return false;
		}
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : ""
		}
	}
};

function loadMenu(resourceType, treeObj) {
	menus = {
		"business" : [ {
			"isParent" : true,
			"name" : "业务模块",
			"sn" : "business"
		} ],
		"systemManage" : [ {
			"id" : 1,
			"pId" : 0,
			"name" : "系统管理"
		}, {
			"id" : 2,
			"pId" : 1,
			"name" : "员工管理",
			"path" : "/employee_execute.action"
		}, {
			"id" : 3,
			"pId" : 1,
			"name" : "部门管理",
			"path" : "/department_execute.action"
		}, {
			"id" : 4,
			"pId" : 1,
			"name" : "权限管理",
			"path" : "/permission_execute.action"
		}, {
			"id" : 5,
			"pId" : 1,
			"name" : "角色管理",
			"path" : "/role_execute.action"
		} ],
		"charts" : [ {
			"isParent" : true,
			"name" : "报表",
			"sn" : "charts"
		} ]
	};
	// 将返回的数据赋给zTree
	$.fn.zTree.init($("#dleft_tab1"), setting, menus[resourceType]);
	zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
}

// 加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

*//**
 * 隐藏或者显示侧边栏
 * 
 *//*
function switchSysBar(flag) {
	var side = $('#side');
	var left_menu_cnt = $('#left_menu_cnt');
	if (flag == true) { // flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({
			width : '280px'
		});
		$('#top_nav').css({
			width : '77%',
			left : '304px'
		});
		$('#main').css({
			left : '280px'
		});
	} else {
		if (left_menu_cnt.is(":visible")) {
			left_menu_cnt.hide(10, 'linear');
			side.css({
				width : '60px'
			});
			$('#top_nav').css({
				width : '100%',
				left : '60px',
				'padding-left' : '28px'
			});
			$('#main').css({
				left : '60px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_show.png');
		} else {
			left_menu_cnt.show(500, 'linear');
			side.css({
				width : '280px'
			});
			$('#top_nav').css({
				width : '77%',
				left : '304px',
				'padding-left' : '0px'
			});
			$('#main').css({
				left : '280px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_hide.png');
		}
	}
}

$(function() {
	loadDate();

	$('#TabPage2 li').click(
			function() {
				loadMenu($(this).data("rootmenu"));

				var index = $(this).index();
				$(this).find('img').attr('src',
						'/images/common/' + (index + 1) + '_hover.jpg');
				$(this).css({
					background : '#fff'
				});
				$('#nav_module').find('img').attr('src',
						'/images/common/module_' + (index + 1) + '.png');
				$('#TabPage2 li').each(
						function(i, ele) {
							if (i != index) {
								$(ele).find('img').attr('src',
										'/images/common/' + (i + 1) + '.jpg');
								$(ele).css({
									background : '#044599'
								});
							}
						});

				// 显示侧边栏
				switchSysBar(true);
			});
	loadMenu('business');

	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});*/