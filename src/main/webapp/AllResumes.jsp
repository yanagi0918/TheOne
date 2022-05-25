<%@page import="DAO.ResumeDao"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Bean.Resume"%>
<%@ page import="java.util.List"%>

<%@include file="DashBoardHeader.jspf"%>
<title>All Resumes</title>
<style>
</style>
<div class="container-fluid pt-4 px-4">
<div class="bg-light text-center rounded p-4">
<div class="d-flex align-items-center justify-content-between mb-4">
<h1 class="mb-0">所有履歷</h1>
<button type="button" class="btn btn-primary mb-0 " onclick="location.href='ResumeDashBoard.jsp'">新增履歷</button>
</div>
<%
List<Resume> list = DAO.ResumeDao.getAllResumes();
%>
<table class="table align-middle table-bordered table-hover mb-0">
	<tr>
		<th>履歷編號</th>
		<th>學歷</th>
		<th>畢業學校</th>
		<th>畢業科系</th>
		<th>工作經驗</th>
		<th>取得證照</th>
		<th>身分證字號</th>
		<th>更新</th>
		<th>刪除</th>
	</tr>
	<%
	for (Resume resume : list) {
	%>
	<tr>
		<td><%=resume.getResume_id()%></td>
		<td><%=resume.getEdu()%></td>
		<td><%=resume.getSchool()%></td>
		<td><%=resume.getDept()%></td>
		<td><%=resume.getWork_exp()%></td>
		<td><%=resume.getSkills()%></td>
		<td><%=resume.getUser_id()%></td>
		<td><a
			href='UpdateResume.jsp?resume_id=<%=resume.getResume_id()%>' class="btn btn-outline-primary m-0">更新</a></td>
		<td>
		<a href='deleteResume?resume_id=<%=resume.getResume_id()%>' class="btn btn-outline-danger m-0" onclick="return deleteForm()">刪除</a></td>
	</tr>
	<%
	}
	%>
</table>
</div>
</div>
<%@include file="DashBoardFooter.jspf"%>