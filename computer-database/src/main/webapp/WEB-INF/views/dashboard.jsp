<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="com.excilys.formation.cdb.mapper.*"%>
<%@page import="com.excilys.formation.cdb.util.Util"%>
<%@page import="com.excilys.formation.cdb.model.Computer"%>
<%@page import="com.excilys.formation.cdb.model.Company"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${nbComputers} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                
                <!-- Browse attribute computers -->
                <tbody id="results">

					<c:forEach items="${lComputers}" var="computer">
                		<tr>
                      	  <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="0">
                      	  </td>
                      	  <td>
                            <a href="editComputer" onclick="">${computer.getName()}</a>
                       	 </td>
                       	 <td>${computer.getIntroduced()}</td>
                       	 <td>${computer.getDiscontinued()}</td>
                      	  <td>${computer.getCompanyName()}</td>
                    </tr>
                	
					</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
				<li <c:if test="${previousPage == curPage}">class="disabled"</c:if>>
              		<a href=
              			<c:choose>
              				<c:when test="${previousPage == curPage}">"#"</c:when>
 							<c:otherwise>"?page=${previousPage}&limit=${curLimit}"</c:otherwise>
                		</c:choose>aria-label="Previous">
                	    <span aria-hidden="true">&laquo;</span>
               		</a>
            	</li>
              <c:forEach items="${lPages}" var="page">
              	<c:choose>
  					<c:when test="${page == curPage}">
   						<li class="disabled"><a href="#">${page}</a></li>
  					</c:when>
 					<c:otherwise>
              			<li><a href="?page=${page}&limit=${curLimit}">${page}</a></li>
 					</c:otherwise>
				</c:choose>
              </c:forEach>
            	<li <c:if test="${nextPage == curPage}">class="disabled"</c:if>>
              		<a href=
              			<c:choose>
              				<c:when test="${nextPage == curPage}">"#"</c:when>
 							<c:otherwise>"?page=${nextPage}&limit=${curLimit}"</c:otherwise>
                		</c:choose>aria-label="Next">
                	    <span aria-hidden="true">&raquo;</span>
               		</a>
            	</li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="?page=${curPage}&limit=10" class="btn btn-default">10</a>
            <a href="?page=${curPage}&limit=50" class="btn btn-default">50</a>
            <a href="?page=${curPage}&limit=100" class="btn btn-default">100</a>
        </div>
    	</div>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>