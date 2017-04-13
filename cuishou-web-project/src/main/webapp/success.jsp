<%@ page language="java" pageEncoding="UTF-8"%>
<html>
 <%
    String msg = (String)request.getAttribute("message");
 %>

<script type="text/javascript">
    
    window.parent.isSuccess('<%=msg%>');
</script>
</html>