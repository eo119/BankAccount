<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="container mt-5">
    <h2>帳戶交易</h2>

    <!-- 存款表單 -->
    <form id="currencyForm" action="BankTransactionServlet" method="post">
    
    <div>
    <label for="currencyType">選擇幣值：</label>
    <select id="currencyType" name="currencyType">
        <option value="TWD">台幣</option>
        <option value="JPY">日圓</option>
        <option value="USD">美元</option>
        <option value="EUR">歐元</option>
    </select>
    </div>
    
        <label for="transactionType">選擇交易類型：</label>
        <select id="transactionType" name="transactionType">
            <option value="deposit">存款</option>
            <option value="withdraw">提款</option>
        </select>

        <label for="amount">金額：</label>
        <input type="number" id="amount" name="amount" required>

        <button type="submit" class="btn btn-primary mx-2">執行交易</button>
    </form>
</div>


<%@ include file="/WEB-INF/view/footer.jsp" %>