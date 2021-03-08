台南市開放資料Demo
===========

## 台南市免費公立停車場Demo

<br>

### 畫面規劃
* 首頁(地圖頁) - 顯示停車場地理位置，點擊Mark即可顯示資訊，實作按鈕可透過GoogleMap導航 (可與列表頁切換)
    - 測試
* 列表頁(停車場資訊) - 列出停車場表單，可實作關鍵字搜尋，點擊項目即可跳至GoogleMap導航 (可與地圖頁切換)



<br>

### 運作流程

1. 第一次進入App時會從API下載資料存入SQLite ( 使用Room，並標上更新日期，顯示Loading畫面 )，處理完成進入主畫面
2. App資料會打上更新時間，固定一天更新一次，也可以由使用者強制更新資料
3. 主畫面顯示GoogleMap，顯示停車地點位置 ( 點擊可顯示對應資料 )
4. 主畫面顯示資料更新按鈕，資料更新之後刷新SQLite資料後顯示於Map上

<br>

### 使用框架與技術

*   使用MVVM
*   使用Hilt
*   使用Navigation
*   使用Restful (Retofit2 and RxJava)
*   Map顯示地理位置
*   單元測試
*   使用GoogleMap導航 (附加功能，Intent給GoogleMap處理)

<br>

***
    - 要注意API資料的更新速率，如果是即時的則每十分鐘更新一次，沒有的話就一天更新一次，以00:00為基準點
    - 設計一個Service倒數計時做背景更新的動作 (也可以使用WorkManager)
    - (目前公有停車場沒有即時性的問題)
***

<br>



API位置 [台南市公有免費停車場]

[台南市公有免費停車場]: https://citypark.tainan.gov.tw/App/parking.ashx?verCode=5177E3481D&type=1&ftype=1&exportTo=2