<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css"/>
    <link href="/style/common_style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery-validate/jquery.validate.min.js"></script>
</head>
<body>
<!-- =============================================== -->
<%@include file="/WEB-INF/views/common/common_message.jsp"%>
<s:form name="editForm" namespace="/" action="${objectName}_saveOrUpdate" method="post" id="editForm">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">${objectCNName}编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <s:hidden name="${objectName}.id"></s:hidden>
                <#assign h = fieldMap>
                    <#assign keys = h?keys>
                        <#list keys as key>
                            <tr>
                                <td class="ui_text_rt" width="140">${h[key]}</td>
                                <td class="ui_text_lt">
                                    <s:textfield name="${objectName}.${key}" cssClass="ui_input_txt02" ></s:textfield>
                                </td>
                            </tr>
                        </#list>
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