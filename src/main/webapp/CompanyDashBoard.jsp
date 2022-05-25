<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Bean.Company" %>
<%@ page import="java.util.List" %>


<%@include file="DashBoardHeader.jspf" %>

            <!-- Content Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">公司資訊</h6>
                        <button type="button" class="btn btn-primary mb-0" onclick="location.href='./CompanyCreate.jsp'">新增公司</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col">帳號(統編)</th>
                                    <th scope="col">公司名稱</th>
                                    <th scope="col">產業</th>
                                    <th scope="col">聯絡人</th>
                                    <th scope="col">電話</th>
                                    <th scope="col">修改</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            List<Company> company = (List<Company>) request.getAttribute("company");
                            for(Company c : company) {
                            %>
                                <tr>
                                    <td><%= c.getCompid() %></td>
                                    <td><%= c.getCorpname() %></td>
                                    <td><%= c.getIndustry() %></td>
                                    <td><%= c.getContact() %></td>
                                    <td><%= c.getComptele() %></td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary m-0" onclick="location.href='./CompanyServlet?UpdateId=<%= c.getCompid() %>'">修改公司</button>
                                        <button type="button" class="btn btn-outline-danger m-0" onclick="javascript:if(confirm('確定要刪除嗎?'))location.href='./CompanyServlet?DeleteId=<%= c.getCompid() %>'">刪除公司</button>
                                    </td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
            <!-- Content End -->

<%@include file="DashBoardFooter.jspf" %>