<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	<s:if test="hasActionMessages()">
		$(function(){
			$.dialog({
				title:"温馨提示",
				content:"<s:property value="actionMessages[0]"/>",
				ok:true
			})
		})	
	</s:if>
	<s:if test="hasActionErrors()">
		$(function(){
			$.dialog({
				title:"温馨提示",
				content:"<s:property value="actionErrors[0]"/>",
				ok:true
			})
		})	
	</s:if>


</script>