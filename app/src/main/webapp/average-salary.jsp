<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List" %>
<%@ page import="by.academy.web.model.Teacher" %>
<!DOCTYPE>
<html lang="ru">
<head>
    <title>Средняя зарплата</title>
</head>
<body>
<p><span style='color: blue;'>Средняя зарплата следующих преподавателей:
    <%= request.getAttribute("averageSalary") %>
</span></p>
<c:set scope="page" var="teachers" value="${requestScope.teachers}"/>
<%--<p><c:out value=" ${teachers}"/></p>--%>
<p><c:forEach var="teacher" items="${teachers}">
    <p><c:out value="${teacher.name} has ${teacher.salary}"/></p>
</c:forEach> </p>
<%--<p><%--%>
<%--    List<Teacher> teachers = (List<Teacher>)request.getAttribute("teachers");--%>
<%--%></p>--%>

</body>
</html>
