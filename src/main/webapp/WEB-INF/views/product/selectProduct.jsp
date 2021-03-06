<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/js/fancyBox/jquery.fancybox.css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/artDialog/iframeTools.js"></script>
    <script type="text/javascript" src="/js/fancyBox/jquery.fancybox.pack.js"></script>   
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script>
    	$(function(){
    		$(".btn_select").click(function(){
    			//获取当前选中的商品信息
    			var productJson=$(this).data("product");
    			
    			//设置数据
    			$.dialog.data("productJson",productJson);
    			
    			//关闭窗口
    			$.dialog.close();
    		})
    	})
    </script>
    <title>PSS-货品管理管理</title>
    <script>
    	$(function(){
    		$('.fancybox').fancybox();
    	})
    </script>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
 <s:form id="searchForm" action="product_selectProduct" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                   <div id="box_center">
                        编码/名称
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        品牌
                        <s:select list="#brands" listKey="id" listValue="name" name="qo.brandId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                          <input type="button" value="查询" class="ui_input_btn01 btn_page"  data-page="1"/>
                    </div>
                    

                        
                        
                      <%--  <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="product_batchDelete"/>"/> --%>
                    
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                       
                       <%-- 定义一个变量,接收数据模型里面的表达式--%>
                            <%--h?keys: h是一个map结构的数据,代表获取到h里面的key的集合--%>
                                <%--遍历keys集合,把每次遍历的数据赋值给变量 key--%>
                                        <%--成本价格:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--货品编码:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>货品图片</th>
                                        <%--货品图片:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--货品名称:从map里面去去key对应的数据信息:中文名称--%>
                                   <th>货品名称</th>
                                    
                                    <th>货品编码</th>
                                        <%--货品品牌:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>货品品牌</th>
                                    
                                     <th>成本价格</th>
                                        <%--销售价格:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>销售价格</th>
                                        <%--备注:从map里面去去key对应的数据信息:中文名称--%>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                                <td> 
                                		<a class="fancybox" href="<s:property value="imagePath"/>"  title="<s:property value="name"/>">
                                		<img src="<s:property value="smallImagePath"/>" width="100">
                                		</a>
                                
                                	
                                </td>
                          
                                <td><s:property value="name"/> </td>
                                <td><s:property value="sn"/> </td>
                                <td><s:property value="brand.name"/> </td>
                                <td><s:property value="costPrice"/> </td>
                                <td><s:property value="salePrice"/> </td>
                                <td>
                                	<input type="button" value="选择该商品" class="ui_input_btn01 btn_select" data-product="<s:property value="productJson"/>"/>
                                </td>
                           
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/WEB-INF/views/common/common_page.jsp" %>
    </div>
</s:form>
</body>
</html>
