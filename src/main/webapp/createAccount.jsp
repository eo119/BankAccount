<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

<div class="mx-5 mt-5">
    <form action="CreateAccountServlet" method="post">
        <div class="mb-3 row">
            <label for="newName" class="col-sm-2 col-form-label">First Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="newName" name="name" required>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="newName" class="col-sm-2 col-form-label">Last Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="newName" name="eglName" required>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="newEmail" class="col-sm-2 col-form-label">電子郵件</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="newEmail" name="email" required>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="newPassword" class="col-sm-2 col-form-label">密碼</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" id="newPassword" name="password" required>
                    <button type="button" class="btn btn-outline-secondary" id="togglePassword">顯示密碼</button>
                </div>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="confirmPassword" class="col-sm-2 col-form-label">確認密碼</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <button type="button" class="btn btn-outline-secondary" id="toggleConfirmPassword">顯示密碼</button>
                </div>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="newPhone" class="col-sm-2 col-form-label">電話</label>
            <div class="col-sm-10">
                <input type="tel" class="form-control" id="newPhone" name="phone" required>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="newBirthdate" class="col-sm-2 col-form-label">出生年月日</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="newBirthdate" name="birth" required>
            </div>
        </div>
        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">性別</label>
            <div class="col-sm-10">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="genderMale" value="male" required>
                    <label class="form-check-label" for="genderMale">男性</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="female" required>
                    <label class="form-check-label" for="genderFemale">女性</label>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-primary mx-2">建立帳戶</button>
            <button type="reset" class="btn btn-danger mx-2">清除</button>
        </div>
    </form>
</div>


<script>
    // JavaScript to toggle password visibility
    const togglePassword = document.getElementById('togglePassword');
    const newPassword = document.getElementById('newPassword');

    togglePassword.addEventListener('click', () => {
        newPassword.type = newPassword.type === 'password' ? 'text' : 'password';
        togglePassword.innerText = newPassword.type === 'password' ? '顯示密碼' : '隱藏密碼';
    });

    // Similar logic for confirmPassword
    const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');
    const confirmPassword = document.getElementById('confirmPassword');

    toggleConfirmPassword.addEventListener('click', () => {
        confirmPassword.type = confirmPassword.type === 'password' ? 'text' : 'password';
        toggleConfirmPassword.innerText = confirmPassword.type === 'password' ? '顯示密碼' : '隱藏密碼';
    });
</script>

<%@ include file="/WEB-INF/view/footer.jsp" %>

