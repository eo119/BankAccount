<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Transaction"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<% List<Transaction> transactionList = (List<Transaction>) request.getAttribute("transactionList");%> 
<html>
<head>
<meta charset="UTF-8">
<title>泰豐銀行</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-warning">
  <div class="container-fluid">
    <a class="navbar-brand text-light" href="#"><i class="bi bi-bank text-light h5"></i> 泰豐銀行</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active text-light" aria-current="page" href="./index.jsp"}>首頁</a>
        </li>
          </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">帳戶總覽</a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item "  href="./ViewTwServlet">台幣活存</a></li>
            <li><a class="dropdown-item "  href="./ViewOtServlet?currencyType=USD">外幣活存</a></li>
          </ul>
        </li>
       <li class="nav-item">
          <a class="nav-link text-light "  href="./trade.jsp">帳戶交易</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light"  href="./ItemServlet">交易紀錄</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-light"  href="./buildAccount.jsp">開戶</a>
        </li>
      </ul>
    </div>
  
    <a href="./login.jsp" class="bi bi-person-square text-light h5 mb-0 d-none d-lg-block" id="login"></a>
  <i class="bi bi-box-arrow-right text-light ms-3 d-none d-lg-block" role="button" onclick="window.location.href='./logout'">Logout</i>
  </div>
</nav>

</body>
</html>