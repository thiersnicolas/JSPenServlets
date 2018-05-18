<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Pizza tussen Prijzen' />
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp" />
	<h1>Pizza's tussen prijzen</h1>
	<form method="get" action='<c:url value="/pizzas/tussenprijzen.htm"/>'>
		<label>Van prijs<span>${fouten.van}</span> <input name="van"
			autofocus type="number" value="${param.van}" min='0' required></label> <label>Tot prijs<span>${fouten.tot}</span>
			<input name="tot" type="number" value="${param.tot}" min="0" required></label> <input type="submit" value="zoeken">
	</form>
	<c:if test='${not empty pizzas}'>
		<ul class='zebra'>
			<c:forEach var='pizza' items='${pizzas}'>
				<li><c:out value='${pizza.naam}'/> ${pizza.prijs}&euro;</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test='${not empty param and empty fouten and empty pizzas}'>
		<div class='fout'>geen Pizza's gevonden</div>
	</c:if>
</body>
</html>