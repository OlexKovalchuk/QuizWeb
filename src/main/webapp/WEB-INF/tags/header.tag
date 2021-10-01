<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template>
    <jsp:attribute name="header">
              <jsp:include page="/WEB-INF/pages/template/header.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:template>