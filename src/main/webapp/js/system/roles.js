//列表移动
$(function(){
	$("#select").click(function(){
		$(".all_permission option:selected").appendTo($(".selected_permission"));
	});
	
	$("#selectAll").click(function(){
		$(".all_permission option").appendTo($(".selected_permission"));
	});
	
	$("#deselect").click(function(){
		$(".selected_permission option:selected").appendTo($(".all_permission"));
	});
	
	$("#deselectAll").click(function(){
		$(".selected_permission option").appendTo($(".all_permission"));
	});
	
	$("#editForm").submit(function(){
		$(".selected_permission option").prop("selected",true);
		/*if($(".selected_permissions option:selected").size()!=$(".selected_permissions option")){
			$(".selected_permissions option").prop("selected",true);
		}*/
	})

	//菜单列表移动
	$(function(){
		$("#mselect").click(function(){
			$(".all_menu option:selected").appendTo($(".selected_menu"));
		});
		
		$("#mselectAll").click(function(){
			$(".all_menu option").appendTo($(".selected_menu"));
		});
		
		$("#mdeselect").click(function(){
			$(".selected_menu option:selected").appendTo($(".all_menu"));
		});
		
		$("#mdeselectAll").click(function(){
			$(".selected_menu option").appendTo($(".all_menu"));
		});
		
		$("#editForm").submit(function(){
			$(".selected_permission option").prop("selected",true);
			/*if($(".selected_permissions option:selected").size()!=$(".selected_permissions option")){
				$(".selected_permissions option").prop("selected",true);
			}*/
		})
	})
	//列表的去重
	$(function(){
		//第一种
		console.log($(".selected_permission option"));
		var selectedIds=$.map($(".selected_permission option"),function(item,index,arr){
			return $(item).val();
		});
		console.log(selectedIds);
		//第二种
		$.each($(".all_permission option"),function(index,item){
			//console.log(arguments);
			//ids.push($(item).val());
			//console.log(index,item);
			var id=$(item).val();
			if($.inArray(id,selectedIds)>=0){
				$(item).remove();
			}
		});
		
	})
	//菜单列表的去重
	$(function(){
		//第一种
		console.log($(".selected_menu option"));
		var selectedIds=$.map($(".selected_menu option"),function(item,index,arr){
			return $(item).val();
		});
		console.log(selectedIds);
		//第二种
		$.each($(".all_menu option"),function(index,item){
			//console.log(arguments);
			//ids.push($(item).val());
			//console.log(index,item);
			var id=$(item).val();
			if($.inArray(id,selectedIds)>=0){
				$(item).remove();
			}
		});
		
	})
		
});

/*var arr=$.map($(".selected_permissions option"),function(option){
return $(option).attr("value");
});
$(".all_permissions option").filter(function(index){
return $.inArray($(this).attr("value"),arr)>=0;
}).remove();*/	
//第二种
/*var ids=[];
$.each($(".selected_permission option"),function(index,item){
//console.log(arguments);
ids.push($(item).val());
})*/