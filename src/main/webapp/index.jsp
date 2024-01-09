<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/view/header.jsp" %>
<style>
    body, html {
        height: 100%;
        margin: 0;
    }

    main {
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        overflow: hidden;
        margin: 0; 
    }

    .main {
        text-align: center;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: rgba(255, 255, 255, 0.5); 
        padding: 20px; 
        border-radius: 10px; 
    }

    img {
        max-height: calc(100vh - 20px);
        width: 100%; 
        display: block;
        margin: -20px auto 0; 
    }

    h2, span {
        font-size: 1.5em;
        color: black; 
    }
    span{
    	height: 100%;
    }
</style>


	<main>
		<img src="./img/card.jpg" alt="">
		<div class='main'>
		<h2>關於我們</h2>
		<span>泰豐銀行，憑藉卓越的金融服務，擁有百年優越的歷史。我們致力於為客戶提供多元化、專業化的金融解決方案，包括個人理財、企業金融和投資服務。以客戶至上的理念，我們持續創新，致力於建立長期穩固的合作關係，滿足客戶不斷變化的需求。在泰豐銀行，我們以卓越品質和責任管理為基石，成為客戶信賴的金融夥伴。</span>
		</div>
	</main>
	
	<session1>
	<h2>最新消息</h2>
		
	
	</session1>

<%@ include file="/WEB-INF/view/footer.jsp" %>