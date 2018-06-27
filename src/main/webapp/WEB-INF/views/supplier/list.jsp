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
    <title>PSS-供应商管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
 <s:form id="searchForm" action="supplier" namespace="/" method="post">
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
                               data-url="<s:url namespace="/" action="supplier_input"/>"/>
                      <%--  <input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
                               data-url="<s:url namespace="/" action="supplier_batchDelete"/>"/> --%>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                       <%-- 定义一个变量,接收数据模型里面的表达式--%>
                            <%--h?keys: h是一个map结构的数据,代表获取到h里面的key的集合--%>
                                <%--遍历keys集合,把每次遍历的数据赋值给变量 key--%>
                                        <%--地址:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>供应商名称</th>
                                        <%--联系电话:从map里面去去key对应的数据信息:中文名称--%>
                                    <th>联系电话</th>
                                    <th>地址</th>
                                        <%--供应商名称:从map里面去去key对应的数据信息:中文名称--%>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#result.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td> 
                            <td><s:property value="name"/> </td>
                              
                                <td><s:property value="phone"/> </td>
                                  <td><s:property value="address"/> </td>
                               
                            <td>
                                <s:a namespace="" action="supplier_input">
                                    <s:param name="supplier.id" value="id"></s:param>
                                    编辑</s:a>
                                <s:url namespace="" action="supplier_delete" var="deleteUrl">
                                    <s:param name="supplier.id" value="id"></s:param>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">
                                    删除
                                </a>
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
