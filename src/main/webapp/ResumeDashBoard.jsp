<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Bean.Resume"%>
<%@ page import="java.util.List"%>
<%@include file="DashBoardHeader.jspf"%>
<head>
<title>Insert Resume</title>
<style>
textarea {
	resize: none;
}
h6{
color:lightgray;
}
.center{
 text-align:center;
}
</style>
</head>
<body>
<div class="container-fluid pt-4 px-4">
<div class="col-sm-12 col-xl-10">
<div class="bg-light rounded h-100 p-4">
		<h5 class="mb-4">新增履歷</h5>
		<h6>*號為必填</h6>
		<div>
			<form action="AllResumes.jsp" method="get">
		<div class="d-flex align-items-center justify-content-between mb-4">
				 <input type="submit" name="" value="觀看所有履歷" class="btn btn-primary mb-0" >
				
		</div>		
			</form>
			<form action="insertResume" method="post" name="form" onsubmit="return checkResumeForm()">
<div class="row mb-3">
						<label class="col-sm-2 col-form-label">*身分證字號:</label>
						<div class="col-sm-8">
						<input type="text" name="USER_ID" maxlength="10" id="USER_ID" class="form-control" required autofocus/>
					</div>
</div>
 <div class="row mb-3">					
						<label class="col-sm-2 col-form-label">*學歷:</label>
						<div class="col-sm-8">
						<select name="EDU" class="form-select">
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
<label class="col-sm-2 col-form-label">*畢業學校:</label>
<div class="col-sm-8">
<input type="text" name="SCHOOL" class="form-control" required />
</div>
</div>
					
<div class="row mb-3">
<label class="col-sm-2 col-form-label">*畢業科系:</label>
<div class="col-sm-8">
<input type="text" name="DEPT" class="form-control" required />
</div>
</div>
<div class="row mb-3">
<label class="col-sm-2 col-form-label">工作經驗:</label>
<div class="col-sm-8">
<textarea name="WORK_EXP"
placeholder="請輸入過往工作,可包含
1.公司名稱
2.職稱
3.工作期間
4.工作內容及成效..."
cols="63" rows="6"></textarea>
</div>
</div>
<div class="row mb-3">
<label class="col-sm-2 col-form-label">取得證照:</label>
<div class="col-sm-8">
<textarea name="SKILLS" placeholder="請輸入已取得證照" cols="63" rows="4"></textarea>
</div>
</div>


<div class="center">			
<button type="submit" class="btn btn-primary">新增</button>
<button type="reset" class="btn btn-primary">清除</button>
</div>
</form>
</div>

	</div>
</div>
</div>
</body>
<%@include file="DashBoardFooter.jspf"%>