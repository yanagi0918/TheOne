<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Bean.Interview"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="DashBoardHeader.jspf"%>

<!-- Content Start -->
<div class="container-fluid pt-4 px-4">
	<div class="col-sm-12 col-xl-10">
		<div class="bg-light rounded h-100 p-4">
			<h2 class="mb-4">內容</h2>
			<form action="./InterViewServletDS" method="post"
				onsubmit="return checkForm2()">
				<%
				Interview intvForUpdate = (Interview) request.getAttribute("intvForUpdate");
				%>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">面試內容編號</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="cvno"
							value=<%=intvForUpdate.getCvNo()%> readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">求職者帳號</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="userId"
							value=<%=intvForUpdate.getUserId()%> required id="userId"
							readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">面試時間</label>
					<div class="col-sm-8">
						<input type="date" class="form-control" name="intTime" required
							id="intTime" value=<%=intvForUpdate.getIntTime()%> readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">修改時間</label>
					<div class="col-sm-8"><%=intvForUpdate.getCreatedTime()%>
						
					</div>
				</div>

				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">面試公司</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="compName" size="10"
							value=<%=intvForUpdate.getCompName()%> readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">面試職缺</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="jobName"
							maxlength="8" required id="jobName" size="10"
							value=<%=intvForUpdate.getJobName()%> readonly>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">是否錄取</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="offer"
							value=<%=intvForUpdate.getOffer()%> readonly>
					</div>
				</div>



				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">筆試或口試</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="test"
							value=<%=intvForUpdate.getTest()%> readonly>
					</div>
				</div>


				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">給該公司評分</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="compScore"
							value=<%=intvForUpdate.getCompScore()%> readonly>
					</div>
				</div>







				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">考官問題</label>
					<div class="col-sm-8">
						<textarea readonly="readonly" class="form-control"
							placeholder="備註最多50字" id="floatingTextarea"
							style="height: 150px;" name="qA" maxlength="50"><%=intvForUpdate.getQA()%></textarea>
					</div>
				</div>
				<div class="row mb-3">
					<label class="col-sm-2 col-form-label">心得分享</label>
					<div class="col-sm-8">
						<textarea readonly="readonly" class="form-control"
							placeholder="備註最多200字" id="floatingTextarea"
							style="height: 150px;" name="share" maxlength="200"><%=intvForUpdate.getShare()%></textarea>
					</div>
				</div>


				<button type="button" class="btn btn-primary"
					onclick="location.href='./InterViewServletDS'">返回</button>

			</form>
		</div>
	</div>
</div>
<!-- Content End -->
<%@include file="DashBoardFooter.jspf"%>