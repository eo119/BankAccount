
使用者類別（User）：使用者的信息，如姓名、電子郵件、密碼等。 enum Gender
台幣帳戶類別（Account：含帳戶號碼、餘額等資訊
外匯帳戶類別（ForeignCurrencyAccount）：extend Account  創建 enum CurrencyType 表示日圓 歐元 美元
交易類別（Transaction）：交易ID、金额、描述、日期等
建立帳戶請求類別（CreateAccountRequest）：代表建立新帳戶的請求

你是指為什麼需要 User 類別，如果已經有了 CreateAccountRequest 類別。
CreateAccountRequest 類別主要用於封裝建立帳戶的請求資訊，而 User 類別則用於表示一個已存在的使用者，可能包含更多的用戶相關資訊，例如使用者的其他屬性、歷史紀錄、角色等等。

功能(API):
１ 可以選擇幣值種類進行提款或取款
２ 可以選擇台幣帳戶或外幣帳戶進行檢視 其中外幣帳戶裡可以選擇幣值
３ 可以透過交易明細查看每一次提款存款的詳細資料


資料庫
1. In-Memory
2. MySQL


用户认证和授权：

登录Servlet (LoginServlet)：处理用户登录请求，验证用户身份，创建用户会话。
注册Servlet (RegisterServlet)：处理用户注册请求，创建新用户。
账户管理：

创建账户Servlet (CreateAccountServlet)：处理创建新账户的请求。
账户详情Servlet (AccountDetailServlet)：显示特定账户的详细信息。
账户交易Servlet (TransactionServlet)：处理账户交易请求，如存款和提款。
页面导航：

页面导航Servlet (NavigationServlet)：处理页面之间的导航请求，例如从主页到账户详情页面的跳转。
外币账户：

外币账户概览Servlet (ForeignCurrencyAccountOverviewServlet)：显示外币账户的概览信息。
外币账户详情Servlet (ForeignCurrencyAccountDetailServlet)：显示特定外币账户的详细信息。
交易历史：

交易历史Servlet (TransactionHistoryServlet)：显示用户的交易历史记录。
登录状态检查：

登录状态检查Servlet (LoginStatusCheckServlet)：检查用户是否已登录，以决定是否允许访问某些页面。
退出登录：

退出登录Servlet (LogoutServlet)：处理用户退出登录请求，清除会话信息。
异常处理：

异常处理Servlet (ErrorHandlingServlet)：处理应用程序中的异常，提供友好的错误页面。
其他功能：

其他可能需要的Servlet，例如文件上传、邮件发送等，根据项目需求决定。
在设计这些Servlet时，确保它们具有清晰的职责，遵循良好的代码组织原则。你还可以考虑使用Servlet过滤器(Filter)来执行通用的前置或后置处理逻辑，以提高代码的模块化性和可维护性。

请注意，这只是一种可能的组织结构，具体的Servlet设计会取决于你的项目需求和架构。在实际编码之前，建议先仔细规划和设计你的应用程序结构。




BankTransactionServlet 修改： 在 BankTransactionServlet 中，根據交易類型（存款或提款）和幣值（台幣或外幣）執行相應的邏輯。這可能涉及更新賬戶餘額、插入交易記錄等。

更新外幣和台幣帳戶總覽 JSP： 在 otOverview.jsp 和 twOverview.jsp 中，使用 <c:forEach> 遍歷相應的帳戶列表，並根據幣值顯示相應的帳戶信息。

交易明細 JSP： 在 transactionDetails.jsp 中，使用 <c:forEach> 遍歷交易列表，同時顯示幣值、金額、描述、日期等信息。

JavaScript 函數更新： 在 showAccountOverview() 和 showTransactionDetails() 函數中，你需要使用 JavaScript 來處理選擇不同幣值時的操作。你可能需要使用 Ajax 來從後端獲取相應的帳戶和交易明細數據。



1.前端建立帳戶頁面 (createAccount.jsp)： 提供一個表單，允許用戶輸入必要的註冊信息，例如名稱、電子郵件、密碼等。這個表單的提交應該將數據發送到後端以進行處理。

2.後端處理建立帳戶 (CreateAccountServlet)： 在後端，你需要一個Servlet或類來處理建立帳戶的請求。這個Servlet應該獲取從前端提交的數據，並將其傳遞給 DAO 進行存儲。同時，在用戶創建時，還需要在數據庫中為該用戶創建一個對應的台幣帳戶和外幣帳戶。

3.DAO處理 (BankDAO)： 在 BankDAO 中添加方法，該方法接受用於創建新帳戶的數據並將其存儲到數據庫中。同時，在創建帳戶時，需要為該用戶創建相應的台幣帳戶和外幣帳戶。

4.台幣帳戶頁面 (twAccount.jsp) 和外幣帳戶頁面 (otAccount.jsp)： 這兩個頁面分別用於顯示台幣帳戶和外幣帳戶的信息，包括帳號、餘額以及相關操作，如存款和取款。

5.前端操作 (depositWithdraw.jsp)： 提供一個表單，允許用戶輸入存款或取款的金額。提交表單時，將數據發送到後端處理。

6.端處理存款和取款 (DepositWithdrawServlet)： 這個Servlet處理用戶提交的存款或取款請求。根據請求的金額和操作類型（存款或取款），更新相應帳戶的餘額，同時在交易表中添加一條交易記錄。