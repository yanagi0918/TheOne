<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Bean.Company" %>
<%@ page import="java.util.List" %>

<%@include file="DashBoardHeader.jspf" %>

            <!-- Content Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="col-sm-12 col-xl-10">
                    <div class="bg-light rounded h-100 p-4">
                        <h6 class="mb-4">新增公司</h6>
                        <form action="./CompanyServlet" method="post" onsubmit="return checkCompanyForm()">
                        <div class="row mb-3">
                                    <input type="hidden"  class="form-control" name="comppk" id="comppk">
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">帳號(統編)</label>
                                <div class="col-sm-8">
                                    <input type="text"  class="form-control"  maxlength="8" name="compid" required id="compid">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">密碼</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="15" name="compwd" required id="compwd" placeholder="(15個英文字元內)">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">公司名稱</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="15" name="corpname" required id="corpname">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">負責人</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="15" name="owner" id="owner">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">產業</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="10" name="industry" required id="industry">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">聯絡人</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="5" name="contact" required id="contact">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">聯絡電話</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="11" name="comptele" id="comptele" placeholder="(02-87654321)">
                                </div>
                            </div>
                        
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">傳真號碼</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="11" name="fax" id="fax" placeholder="(02-12345678)">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">公司地址</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="30" name="compaddress" required id="compaddress">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">員工人數</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" maxlength="7" name="empnumber" id="empnumber" placeholder="(請輸入阿拉伯整數，如2500)">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">公司網站</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="website" id="website">
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label">資本額</label>
                                <div class="col-sm-8 text-center">
                                <input type="text" class="form-control" maxlength="10"  name="capital" id="capital" placeholder="(請輸入單位，如1250萬)">  
                                </div>
                            </div>
                            
                            <button type="submit" class="btn btn-primary" name="create" value="confirm">確認新增</button>
                            <button type="button" class="btn btn-primary" onclick="location.href='./CompanyServlet'">取消</button>
                      	    <button type="button" class="btn btn-primary" id="wrongInput">輸入錯誤範例</button>
                            <button type="button" class="btn btn-primary" id="correctInput">一鍵輸入</button>        
                        </form>
                    </div>
                </div>
            </div>
            <!-- Content End -->

<%@include file="DashBoardFooter.jspf" %>