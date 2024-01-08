<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Transaction"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="container mt-5">
    <h2>交易明細</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>交易ID</th>
                    <th>幣別</th>
                    <th>金額</th>
                    <th>描述</th>
                    <th>日期</th>
                </tr>
            </thead>
            <tbody>
              <% for(Transaction ts : transactionList) { %>
				    <tr>
				        <td><%=ts.getTransactionId() %></td>
				        <td><%=ts.getCurrency() %></td> 
				        <td><%=ts.getAmount() %></td>
				        <td><%=ts.getDescription() %></td>
				      <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts.getDate()) %></td>
				    </tr>
				<% } %>
            </tbody>
        </table>

</div>

<%@ include file="/WEB-INF/view/footer.jsp" %>