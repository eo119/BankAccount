<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="container mt-5">
    <h2>台幣帳戶總覽</h2>

    <c:if test="${not empty twdAccount}">
        <table class="table">
            <thead>
                <tr>
                    <th>帳號</th>
                    <th>餘額</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                 <tr>
                     <td>${twdAccount.accountNumber}</td>
                     <td>${twdAccount.balance}</td>
                     <td>
                         <a href="twDetail.jsp?accountNumber=${twdAccount.accountNumber}" class="btn btn-info">查看詳細</a>
                     </td>
                 </tr>                
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty twdAccount}">
    </c:if>
</div>

<%@ include file="/WEB-INF/view/footer.jsp" %>

