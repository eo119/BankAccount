<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="container mt-5">
    <h2>外幣帳戶總覽</h2>

    <%-- 下拉式列表，選擇不同幣值 --%>
    <label for="currencyType">選擇幣值：</label>
    <select id="currencyType" onchange="showAccountOverview(event)">
        <option value="USD" ${ selected == 'USD' ? 'selected': '' }>美元</option>
        <option value="EUR" ${ selected == 'EUR' ? 'selected': '' }>歐元</option>
        <option value="JPY" ${ selected == 'JPY' ? 'selected': '' }>日圓</option>
    </select>

    <%-- 假設otAccountList是後端提供的外幣帳戶清單 --%>
   <c:if test="${not empty otAccount}">
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
                    <td>${otAccount.accountNumber}</td>
                    <td>${otAccount.balance}</td>
                    <td>
                        <%-- 添加操作按鈕或連結，例如查看詳細信息或進行交易 --%>
                        <a href="otDetail.jsp?accountNumber=${account.accountNumber}" class="btn btn-info">查看詳細</a>
                    </td>
                </tr>
        </tbody>
    </table>
</c:if>

    <c:if test="${empty otAccount}">
     
    </c:if>
</div>

<script type="text/javascript">

function showAccountOverview(event) {
	let currency = event.target.value;
	window.location.href = '<%= request.getContextPath() %>' + "/ViewOtServlet?currencyType=" + currency;
}

</script>

<%@ include file="/WEB-INF/view/footer.jsp" %>

