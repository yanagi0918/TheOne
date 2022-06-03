<%@page import="java.security.Provider.Service"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Bean.Job"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="DashBoardHeader.jspf"%>
<title>All Jobs</title>
<style>

</style>

<div class="container-fluid pt-4 px-4">
<div class="bg-light text-center rounded p-4">
	<form action="JobDashBoard.jsp" method="post">
	<div class="d-flex align-items-center justify-content-between mb-4">
	<h1>所有職缺</h1>
		 <input type="submit" value="新增職缺" class="btn btn-primary mb-0">
                    </div>                    
	</form>
	<form action="SelectJob.jsp" method="get">
	<div class="d-flex align-items-center justify-content-between mb-4">
		 <input type="text" name="TITLE" class="form-control" autofocus>
		<input type="submit" value="以職缺名稱快速查詢" class="btn btn-primary mb-0" >
		</div>
	</form>
	<div class="table-responsive">
	<table class="table align-middle table-bordered table-hover mb-0">
	<thead>
		<tr class="text-dark">
			<th scope="col">職缺編號</th>
			<th scope="col">職缺名稱</th>
			<th scope="col">職缺性質</th>
			<th scope="col">職缺條件</th>
			<th scope="col">需求人數</th>
			<th scope="col">每月薪資</th>
			<th scope="col">統一編號</th>
			<th scope="col">更新</th>
			<th scope="col">刪除</th>
		</tr>
		</thead>
        <tbody>
		
		<c:forEach items="${jobs}" var="job">
		<tr>
			<td>${job.Job_id}</td>
			<td>${job.Title}</td>
			<td>${job.Job_description}</td>
			<td>${job.Qualification}</td>
			<td>${job.Required_number}</td>
			<td>${job.Salary}</td>
			<td>${job.Comp_id}</td>
			<td><a href='UpdateJob.jsp?job_id=${job.Job_id}' class="btn btn-outline-primary m-0">更新</a></td>
			<td><a href='delete?job_id=${job.Job_id}' class="btn btn-outline-danger m-0" onclick="return deleteForm()">刪除</a></td>	
			
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
	</div>
	<%@include file="DashBoardFooter.jspf"%>