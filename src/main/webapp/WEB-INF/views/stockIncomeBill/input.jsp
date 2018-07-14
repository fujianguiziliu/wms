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
    <script type="text/javascript" src="/js/jquery-validate/jquery.validate.min.js"></script>

	<script>
		$(function(){
			$("#edit_table_body").on("click",".searchproduct",function(){
				var url="/product_selectProduct.action";
				//找到当前行
				var currentTr=$(this).closest("tr"); 
				$.dialog.open(url,{
					id:"seleProduct",
					title:"货品选择列表",
					width:900,
					height:600,
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
				
			}).on("change","[tag=costPrice],[tag=number]",function(){
				var currentTr=$(this).closest("tr");
				var costPrice=currentTr.find("[tag=costPrice]").val();
				var number=currentTr.find("[tag=number]").val();
			
				var amount=number*costPrice;
				currentTr.find("[tag=amount]").html(amount.toFixed(3));
			}).on("click",".removeItem",function(){
				var currentTr=$(this).closest("tr");
				if($("#edit_table_body tr").size()>1){
					currentTr.remove();
				}else{
					currentTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
					currentTr.find("[tag=brand],[tag=amount]").html("");
				}
			
			
		})
			//绑定添加明细的事件
			$(".appendRow").click(function(){
				var cloneTr=$("#edit_table_body tr:first").clone();
				//清空数据
				cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
				cloneTr.find("[tag=brand],[tag=amount]").html("");
				cloneTr.appendTo($("#edit_table_body"));
			});
			//表单提交事件
			$("#editForm").submit(function(){
				$.each($("#edit_table_body tr"),function(index,item){
					$(item).find("[tag=pid]").prop("name","stockIncomeBill.items["+index+"].product.id");
				
					$(item).find("[tag=costPrice]").prop("name","stockIncomeBill.items["+index+"].costPrice");
				
					$(item).find("[tag=number]").prop("name","stockIncomeBill.items["+index+"].number");
					
					$(item).find("[tag=remark]").prop("name","stockIncomeBill.items["+index+"].remark");
				})
			});
		})
	
	</script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
<s:form name="editForm" namespace="/" action="stockIncomeBill_saveOrUpdate" method="post" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购入库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="stockIncomeBill.id"></s:hidden>
                            <tr>
                                <td class="ui_text_rt" width="140">入库编码</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="stockIncomeBill.sn" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="ui_text_rt" width="140">仓库</td>
                                <td class="ui_text_lt">
                                   <s:select list="#depots" listKey="id" listValue="name" name="stockIncomeBill.depot.id" cssClass="ui_select02"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt" width="140">业务时间</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="stockIncomeBill.vdate" cssClass="ui_input_txt02" ></s:textfield>
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
                            	<s:if test="stockIncomeBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="stockIncomeBill.items[0].product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="stockIncomeBill.items[0].costPrice"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><s:textfield tag="number" name="stockIncomeBill.items[0].number"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="stockIncomeBill.items[0].remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
								</s:if>
								<s:else>
									<s:iterator value="stockIncomeBill.items">
									<tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name" name="product.name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                       <%--  <s:hidden name="stockIncomeBill.items[0].product.id" tag="pid"/> --%>
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