<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="mx-5 mt-5">
    <form action="LoginServlet" method="post">
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="staticEmail" value="email@example.com" name="email"
                    autocomplete="off">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" name="password" autocomplete="off">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputCaptcha" class="col-sm-2 col-form-label">驗證碼</label>
            <div class="col-sm-5 d-flex align-items-center">
                <input type="text" class="form-control" id="inputCaptcha" name="captcha" autocomplete="off" required>
                <div class="ml-3"><%= generateRandomCaptcha() %></div>
            </div>
        </div>

        <div class="d-flex justify-content-center ">
            <button type="submit" class="btn btn-primary mx-2">登入</button>
            <button type="reset" class="btn btn-danger mx-2">清除</button>
            <a href="createAccount.jsp" class="btn btn-info mx-2">建立帳戶</a>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/view/footer.jsp" %>

<%!
    String generateRandomCaptcha() {
        return String.format("%04d", new java.util.Random().nextInt(10000));
    }
%>


