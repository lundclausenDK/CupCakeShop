<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>

        <%@include file="inc/head.jsp" %>
    </head>
    <body class="shop">
        
        <c:import url="/inc/header.jsp"/>

        <div class="container transparent shop-padding">
            <div class="col-sm-6">

            </div>

            <div class="col-sm-6">
                <h1>Create your own CupCakes</h1>
                <form action="FrontController" method="POST">
                    <div class="form-group">
                        <select class="form-control"">
                            <option>Select topping...</option>
                            <c:forEach items="${list1}" var="item">
                                <option><c:out value="${item}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <select class="form-control">
                            <option>Select bottom...</option>
                            <c:forEach items="${list2}" var="item">
                                <option><c:out value="${item}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="row">
                        <div class="col-sm-6"><input type="text" placeholder="Type amount..." class="form-control"></div>
                        <div class="col-sm-6 text-right"><input type="submit" value="Add to basket" class="btn btn-primary"></div>
                    </div>
                    
                    
                </form>
            </div>

        </div>

        <c:import url="/inc/footer.jsp"/>
    </body>
</html>
