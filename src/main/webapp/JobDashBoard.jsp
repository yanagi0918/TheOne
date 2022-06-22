<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tw.team5.bean.Job"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="DashBoardHeader.jspf"%>

<div class="container-fluid pt-4 px-4">
<div class="bg-light text-center rounded p-4">
	<form action="JobDashBoard.jsp" method="post">
	<div class="d-flex align-items-center justify-content-between mb-4">
	<h2>所有職缺</h2>
	<button type="button" class="btn btn-primary mb-0" onclick="window.location.href='showForm'">新增職缺</button>
    </div>                    
	</form>
	<div class="table-responsive">
	<table class="table align-middle table-bordered table-hover mb-0 order-table" id="lee">
	<thead>
		<tr class="text-dark">
			<th scope="col">職缺名稱</th>
			<th scope="col">職缺性質</th>
			<th scope="col">職缺條件</th>
			<th scope="col">需求人數</th>
			<th scope="col">每月薪資</th>
			<th scope="col">統一編號</th>
			<th scope="col">動作</th>
		</tr>
		</thead>
        <tbody>
        <c:forEach var="jobs" items="${jobs}">
        
        <c:url var="updateLink" value="/job/showupdateinformation">
        <c:param name="jobId" value="${jobs.job_id}" />
       	</c:url>
       	
       	<c:url var="deleteLink" value="/job/delete">
        <c:param name="jobId" value="${jobs.job_id}" />
       	</c:url>
       	
		<tr>
			<td>${jobs.title}</td>
			<td>${jobs.job_description}</td>
			<td>${jobs.qualification}</td>
			<td>${jobs.required_number}</td>
			<td>${jobs.salary}</td>
			<td>${jobs.comp_id}</td>
			<td>
			<a href="${updateLink}">更新</a>
			<a href="${deleteLink}" class="btn btn-outline-danger m-0" onclick="return deleteForm()">刪除</a>
			</td>	
			
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
	</div>
	<%@include file="DashBoardFooter.jspf"%>