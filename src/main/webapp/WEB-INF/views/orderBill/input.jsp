<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css"/>
    <link href="/style/common_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    
	<script>
		$(function(){
			$(".searchproduct").click(function(){
			//alert("弹出产品选择界面");
			var url="/product_selectProduct.action";
			
			//向上面找到匹配的tr
			var currentTr=$(this).closest("tr");
			
			$.dialog.open(url,{
				id:'selectProduct',
				title:'货品选择',
				width:950,
				height:680,
				close:function(){
				var data=$.dialog.data("productJson");
					if(data){
						currentTr.find("[tag=name]").val(data.name);
						currentTr.find("[tag=pid]").val(data.id);
						currentTr.find("[tag=brand]").html(data.brandName);
						currentTr.find("[tag=costPrice]").val(data.costPrice);
						}
					}
				})
			});
			//对于金额小计统计的值的改变
			$("[tag=costPrice],[tag=number]").change(function(){
				
			//找到采购价格和采购数量的值
			var currentTr=$(this).closest("tr");
			//向上找到当前行
			var costPrice=currentTr.find("[tag=costPrice]").val();
			//找到当前行的采购价格和采购数量的值
			var number=currentTr.find("[tag=number]").val();
			var amount=costPrice * number;
			//找到当前行的金额小计，并赋值操作
			currentTr.find("[tag=amount]").html(amount.toFixed(2));
			});
			//添加明细行
			$(".appendRow").click(function(){
				//找到明细列表中的第一行
				var cloneTr=$("#edit_table_body tr:first").clone(true);
				//清空复制行中的数据
				cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
				cloneTr.find("[tag=brand],[tag=amount]").html("");
				//把复制的tr添加到tbody法人最后
				cloneTr.appendTo($("#edit_table_body"));
			});
			//表单的保存提交方法
			$("#editForm").submit(function(){
				$.each($("#edit_table_body tr"),function(index,item){
					$(item).find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
					$(item).find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
					$(item).find("[tag=number]").prop("name","orderBill.items["+index+"].number");
					$(item).find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
				})
			});
			
			$(".removeItem").click(function(){
				var currentTr=$(this).closest("tr");
				if($("#edit_table_body tr").size()>1){
					currentTr.remove();
				}else{
					//清空复制行中的数据
					currentTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
					currentTr.find("[tag=brand],[tag=amount]").html("");
				}
			});
			//日期控件的处理
			$("[name='orderBill.vdate']").addClass("Wdate").click(function(){
					WdatePicker();
    			
    			});
		})
	
	</script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate" method="post" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="orderBill.id"></s:hidden>
                            <tr>
                                <td class="ui_text_rt" width="140">订单编号</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="orderBill.sn" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">供应商</td>
                                <td class="ui_text_lt">
                                <s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name" cssClass="ui_select02"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">业务时间</td>
                                <td class="ui_text_lt">
                                	<s:date name="orderBill.vdate" format="yyyy-MM-dd" var="_vdate"/>
                                    <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02" value="%{#_vdate}"></s:textfield>
                                </td>
                            </tr>
                            <tr>
                   			 <td class="ui_text_rt" width="140">明细</td>
               				 </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            	<s:if test="orderBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="orderBill.items[0].product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="orderBill.items[0].costPrice"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><s:textfield tag="number" name="orderBill.items[0].number"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderBill.items[0].remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
								</s:if>
								<s:else>
									<s:iterator value="orderBill.items">
									<tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name" name="product.name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                       <%--  <s:hidden name="orderBill.items[0].product.id" tag="pid"/> --%>
                                        <s:hidden name="product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                    <td><s:textfield tag="costPrice" name="costPrice"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><s:textfield tag="number" name="number"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><span tag="amount"><s:property value="amount"/></span></td>
                                    <td><s:textfield tag="remark" name="remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
									
									</s:iterator>
								</s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>
                            
                            
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>