<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Bean.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ResumeDao" %>
<%@include file="DashBoardHeader.jspf" %>

<%
int resume_id=Integer.parseInt(request.getParameter("resume_id"));
Resume resume = ResumeDao.getResumeByResumeID(resume_id);
%>
<style>
textarea {
	resize: none;
}
.center{
 text-align:center;
}
</style>
<div class="container-fluid pt-4 px-4">
<div class="col-sm-12 col-xl-10">
<div class="bg-light rounded h-100 p-4">
<h1>更新履歷</h1>
<form action='updateResume' method='post'>

<input type='hidden' name='RESUME_ID' value='<%=resume.getResume_id() %>'/>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">身分證字號:</label>
<div class="col-sm-8">
<input type='text' name='' value='<%=resume.getUser_id() %>' class="form-control" disabled/>
<input type='hidden' name='USER_ID' value='<%=resume.getUser_id() %>'/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">學歷:</label>
<div class="col-sm-8">
<select name='EDU' class="form-select">
<option><%=resume.getEdu() %></option>
<option>大學</option>
<option>科技大學</option>
<option>研究所</option>
<option>博士</option>
<option>高中職</option>
<option>五專</option>
</select>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">畢業學校:</label>
<div class="col-sm-8">
<input type='text' name='SCHOOL' value='<%=resume.getSchool() %>' class="form-control"/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">畢業科系:</label>
<div class="col-sm-8">
<input type='text' name='DEPT' value='<%=resume.getDept() %>' class="form-control"/>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">工作經驗:</label>
<div class="col-sm-8">
<textarea name="WORK_EXP" id="" cols="63" rows="6"><%=resume.getWork_exp() %></textarea>
</div>
</div>

<div class="row mb-3">
<label class="col-sm-2 col-form-label">取得證照:</label>
<div class="col-sm-8">
<textarea name="SKILLS" id="" cols="63" rows="4"><%=resume.getSkills() %></textarea>
</div>
</div>
<div class="center">	
<input type='submit' value='更改並儲存 ' class="btn btn-primary"/>
</div>

</form>
</div>
</div>
</div>


<%@include file="DashBoardFooter.jspf"%>