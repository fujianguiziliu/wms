<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-采购入库单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
 <s:form id="searchForm" action="stockIncomeBill" namespace="/" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
     <%--               <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" class="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" listKey="id" listValue="name" name="qo.deptId"
                                  headerKey="-1" headerValue="全部" class="ui_select01"/>
                    </div> --%>
                    <div id="box_bottom">

<!--                         <input type="button" value="查询" class="ui_input_btn01 btn_page"  data-page="1"/>
 -->
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="stockIncomeBill_input"/>"/>
                      <%--  <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="stockIncomeBill_batchDelete"/>"/> --%>
                    </div>
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
                                        <%--入库编码:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>入库编码</th>
                                        <%--审核人:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--业务时间:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>业务时间</th>
                                    <th>仓库</th>
                                        <%--入库单明细:从map里面去去key对应的数据信息:中文名称--%>
                                
                                        <%--审核状态:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--审核时间:从map里面去去key对应的数据信息:中文名称--%>
                                   
                                    <th>入库总数量</th>
                                    <th>入库总金额</th>
                                        <%--录入时间:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>录入人</th>
                                    <th>审核人</th>
                              
                                    <th>审核状态</th>
                                        <%--仓库:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--入库总金额:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--入库总数量:从map里面去去key对应的数据信息:中文名称--%>
                                        <%--录入人:从map里面去去key对应的数据信息:中文名称--%>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                           
                                <td><s:property value="sn"/> </td>
                                <td><s:property value="vdate"/> </td>
                                <td><s:property value="depot.name"/> </td>
                                <td><s:property value="totalNumber"/> </td>
                                <td><s:property value="totalAmount"/> </td>
                                <td><s:property value="inputUser.name"/> </td>
                                <td><s:property value="auditor.name"/> </td>                         
                               
                                	<s:if test="status==0">
                                		<td><span style="color:green">未审核</span></td>
                                	
                                	 <td>
                                 <s:url namespace="/" action="stockIncomeBill_audit" var="auditUrl">
                                    <s:param name="stockIncomeBill.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_audit" data-url="<s:property value="#auditUrl"/>">
                                 审核
                                </a>	 
                                <s:a namespace="/" action="stockIncomeBill_input">
                                    <s:param name="stockIncomeBill.id" value="id"></s:param>
                                    编辑</s:a>
                                <s:url namespace="/" action="stockIncomeBill_delete" var="deleteUrl">
                                    <s:param name="stockIncomeBill.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
                                
                            </td>
                                	</s:if>
                                	<s:else>
                                		<td><span style="color:red">已审核</span></td>
                                		<td>
                                		       <s:a namespace="/" action="stockIncomeBill_show">
                                    <s:param name="stockIncomeBill.id" value="id"></s:param>
                                  	  查看</s:a>
                                </td>
                            </s:else>    
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
