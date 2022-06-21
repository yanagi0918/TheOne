<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tw.team5.bean.Company" %>
<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

                        <h2 class="mb-0">公司資訊</h2>
                        
                        <button type="button" class="btn btn-primary mb-0" onclick="window.location.href='showForm'; return false;">新增公司</button>
                    <div class="table-responsive">
                        <table  id="lee">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col">帳號(統編)</th>
                                    <th scope="col">公司名稱</th>
                                    <th scope="col">產業</th>
                                    <th scope="col">聯絡人</th>
                                    <th scope="col">電話</th>
                                    <th scope="col">動作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${companies}" var="companies">

       						<c:url var="detailLink" value="/company/detail">
        					<c:param name="companyId" value="${companies.comppk}" />
       						</c:url>
                            
                            <c:url var="updateLink" value="/company/showupdateinformation">
        					<c:param name="companyId" value="${companies.comppk}" />
       						</c:url>
       						
       						<c:url var="deleteLink" value="/company/delete">
        					<c:param name="companyId" value="${companies.comppk}" />
       						</c:url>
       						
                                <tr>
                                    <td>${companies.compid}</td>
                                    <td>${companies.corpname}</td>
                                    <td>${companies.industry}</td>
                                    <td>${companies.contact}</td>
                                    <td>${companies.comptele}</td>
                                    <td>
                                   		<a href="${detailLink}">查看</a>
                                    	<a href="${updateLink}">更新</a>
                                    	<a href="${deleteLink}" onclick="if (!(confirm('確定要刪除嗎?'))) return false">刪除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <script src="js/checkCompanyForm.js"></script>
            <script src="lib/chart/chart.min.js"></script>
    		<script src="lib/easing/easing.min.js"></script>
    		<script src="lib/waypoints/waypoints.min.js"></script>
    		<script src="lib/owlcarousel/owl.carousel.min.js"></script>
    		<script src="lib/tempusdominus/js/moment.min.js"></script>
    		<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    		<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
    		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>