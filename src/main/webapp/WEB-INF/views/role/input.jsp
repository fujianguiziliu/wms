<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/artDialog/jquery.artDialog.js?skin=chrome"></script>
<script type="text/javascript" src="/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery/messages_cn.js"></script>
<%-- <script type="text/javascript" src="/js/commonAll.js"></script> --%>
<script type="text/javascript" src="/js/system/roles.js"></script>
 </head>
<body>
<%@include file="/WEB-INF/views/common/common_message.jsp" %>
	<s:form name="editForm" namespace="/" action="role_saveOrUpdate"
		id="editForm">
		
		<div id="container">
			<div id="nav_links">
				<span style="color: #1A5CC6;">角色编辑</span>
				<div id="page_close">
					<a>
						<img src="/images/common/page_close.png" width="20" height="20"
							style="vertical-align: text-top;" />
					</a>
				</div>
			</div>
			<div class="ui_content">
				<table cellspacing="0" cellpadding="0" width="100%" align="left"
					border="0">
					<s:hidden name="r.id" />
					<tr>
						<td class="ui_text_rt" width="140">角色名称</td>
						<td class="ui_text_lt">
							<s:textfield name="r.name" cssClass="ui_input_txt02" />
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">角色代码</td>
						<td class="ui_text_lt">
							<s:textfield name="r.sn" cssClass="ui_input_txt02" />
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">权限</td>
						<td class="ui_text_lt">
							<table>
								<tr>
									<td>
										<s:select list="#permissions"  listKey="id" listValue="name"
											multiple="true" cssClass="ui_multiselect01 all_permission" />
									</td>
									<td align="center">
										<input type="button" id="select" value="--->"
											class="left2right" />
										<br />
										<input type="button" id="selectAll" value="==>"
											class="left2right" />
										<br />
										<input type="button" id="deselect" value="<---"
											class="left2right" />
										<br />
										<input type="button" id="deselectAll" value="<=="
											class="left2right" />
									</td>
									<td>
										<s:select list="r.permissions" name="r.permissions.id"
											listKey="id" listValue="name" multiple="true"
											cssClass="ui_multiselect01 selected_permission" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="ui_text_lt">
							&nbsp;
							<s:submit value="提交" class="ui_input_btn01"></s:submit>
							&nbsp;
							<input id="cancelbutton" type="button" value="重置"
								class="ui_input_btn01" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>
</html>