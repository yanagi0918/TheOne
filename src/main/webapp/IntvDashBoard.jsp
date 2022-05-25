<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Bean.Interview" %>
<%@ page import="java.util.List" %>


<%@include file="DashBoardHeader.jspf" %>

            <!-- Content Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light text-center rounded p-4">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <h6 class="mb-0">面試經驗談</h6>
                        <button type="button" class="btn btn-primary mb-0" onclick="location.href='./IntvCreate.jsp'">新增紀錄</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-middle table-bordered table-hover mb-0">
                            <thead>
                                <tr class="text-dark">
                                    <th scope="col">面試內容編號</th>
                                    <th scope="col">面試公司</th>
                                    <th scope="col">職缺名稱</th>
                                    <th scope="col">面試評分</th>
                                    <th scope="col">公司評分</th>
                                    <th scope="col">修改</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            int pageCount = (int) request.getAttribute("pageCount");
                            List<Interview> interviews = (List<Interview>) request.getAttribute("interviews");
                            for(Interview interview : interviews){
                            %>
                                <tr>
                                    <td><%= interview.getCv_No() %></td>
                                    <td><%= interview.getComp_Name() %></td>
                                    <td><%= interview.getJob_Name() %></td>
                                    <td><%= interview.getInt_Score()%></td>
                                    <td><%= interview.getComp_Score() %></td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary m-0" onclick="location.href='./InterViewServletDS?UpdateId=<%= interview.getCv_No() %> '">Update</button>
                                        <button type="button" class="btn btn-outline-danger m-0" onclick="location.href='./InterViewServletDS?DeleteId=<%= interview.getCv_No() %>'">Delete</button>
                                    </td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                    <div class="btn-group mt-4 me-2" role="group">
                    <% for (int i = 1 ; i <= pageCount ; i++){ %>
                        <button type="button" class="btn btn-primary" onclick="location.href='./InterViewServletDS?page=<%= i %>'"><%= i %></button>
                    <% } %>    
                    </div>
                </div>
            </div>
            <!-- Content End -->

<%@include file="DashBoardFooter.jspf" %>