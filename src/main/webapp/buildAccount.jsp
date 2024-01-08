<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="container mt-5">
    <h2>開戶</h2>

    <form action="BuildAccountServlet" method="post">
        <label for="accountNumber">帳號：</label>
        <input type="text" id="accountNumber" name="accountNumber" required>

        <label for="currencyType">幣別：</label>
        <select id="currencyType" name="currencyType">
            <option value="TWD">台幣</option>
            <option value="USD">美元</option>
            <option value="JPY">日圓</option>
            <option value="EUR">歐元</option>
            <!-- 添加更多的幣值選項，根據實際需求 -->
        </select>

        <button type="submit" class="btn btn-primary mx-2">開戶</button>
    </form>
</div>

<%@ include file="/WEB-INF/view/footer.jsp" %>
