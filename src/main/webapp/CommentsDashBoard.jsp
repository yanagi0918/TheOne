<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="DashBoardHeader.jspf"%>

<div class="container-fluid pt-4 px-4">
	<div class="bg-light text-center rounded p-4">
		<div class="d-flex align-items-center justify-content-between mb-4">
			<h6 class="mb-0">公司職位評論</h6>
			<div class="form-control-sm w-75 rounded-pill bg-white">
				<span class="float-start mx-1 my-2 rounded-circle"> <i
					class="fa-solid fa-magnifying-glass"></i>
				</span>
				<div class="">
					<input
						class="form-control float-start bg-transparent border-0 w-75 searchBar"
						type="search" data-table="order-table" placeholder="搜尋...">
				</div>
			</div>

			<button type="button" class="btn btn-primary mb-0"
				onclick="location.href='./CommentNew'">新增評論</button>
		</div>
		<div class="container">
			<table
				class="table align-middle table-bordered order-table mb-0 compact cell-border hover"
				id="commentTable" style="width: 100%">
				<thead>
					<tr class="text-dark">
						<th>編號</th>
						<th>參考時間</th>
						<th>公司名稱</th>
						<th>公司評分</th>
						<th>職位</th>
						<th>職位評分</th>
						<th>心得分享</th>
						<th>動作</th>
					</tr>
<!-- 					<tr id="test"> -->
<!-- 						<th scope="col">編號</th> -->
<!-- 						<th scope="col">參考時間</th> -->
<!-- 						<th scope="col">公司名稱</th> -->
<!-- 						<th scope="col">公司評分</th> -->
<!-- 						<th scope="col">職位</th> -->
<!-- 						<th scope="col">職位評分</th> -->
<!-- 						<th scope="col">心得分享</th> -->
<!-- 						<th scope="col">動作</th> -->
<!-- 					</tr> -->
				</thead>
				<tbody>

					<c:forEach var="comment" items="${listComment}">
						<tr>
							<td><c:out value="${comment.share_id}" /></td>
							<td><fmt:formatDate value="${comment.ref_time}"
									pattern="yyyy-MM" /></td>
							<td><c:out value="${comment.comp_name}" /></td>
							<td class="listComp"
								data-score="<c:out value='${comment.comp_score}' />"></td>
							<td><c:out value="${comment.job_name}" /></td>
							<td class="listJob"
								data-score="<c:out value='${comment.job_score}' />"></td>
							<td><c:out value="${comment.share}" /></td>
							<td>
								<button type="button" class="btn btn-outline-info m-0"
									onclick="location.href='./CommentDetail?id=<c:out value='${comment.share_id}' />'">內容</button>
								<button type="button" class="btn btn-outline-primary m-0"
									onclick="location.href='./CommentEdit?id=<c:out value='${comment.share_id}' />'">修改</button>
								<button type="button"
									class="btn btn-outline-danger m-0 comment-delete" value='${comment.share_id}'>刪除</button>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@include file="DashBoardFooter.jspf"%>