<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
    <!-- ===============================================-->
    <!--    Document Title-->
    <!-- ===============================================-->
    <title>둥글개 둥글개</title>


    <!-- ===============================================-->
    <!--    Favicons-->
    <!-- ===============================================-->
    
    <link rel="apple-touch-icon" sizes="180x180" href="assets/img/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/img/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/img/favicons/favicon-16x16.png">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicons/favicon.ico">
    <link rel="manifest" href="assets/img/favicons/manifest.json">
    <meta name="msapplication-TileImage" content="assets/img/favicons/mstile-150x150.png">
    <meta name="theme-color" content="#ffffff">

    <!-- ===============================================-->
    <!--    Stylesheets-->
    <!-- ===============================================-->
    <link href="assets/css/theme.css" rel="stylesheet" />

  </head>


  <body>

    <!-- Main Content-->
    <main class="main" id="top">
   <%@ include file = "views/common/menubar.jsp" %>
      
      <!-- 232 189 85 강아지 배경 컬러 -->
      <section class="pt-0 pb-0" id="home" style="background:rgb(232,189,85)">
        <div class="container">
          <div class="row align-items-center">
          <!-- 메인강아지 이미지 삽입 -->
            <div class="col-md-5 col-lg-6 order-0 order-md-1 text-end">
               <img class="pt-md-0 w-100" src="assets/img/gallery/maindog.jpg" width="470" alt="mainDog"/>
            </div>
            <!-- 좌측 문구 -->
            <div class="col-md-7 col-lg-6 text-md-start text-center py-6">
              <h4 class="fs-3 fw-bold font-sans-serif text-white">WAL WAL</h4>
              <h1 class="fs-7 fw-bold fs-xl-7 mb-1 font-sans-serif">Hello</h1>
              <h1 class="fs-7 fw-bold fs-xl-7 mb-4 font-sans-serif">Dogg World</h1>
              <a class="btn btn-info me-2" href="#!" role="button"><b>입학신청</b></a>
              <a class="btn btn-success" href="#!" role="button"><b>통학버스</b></a>
            </div>
          </div>
        </div>
      </section>

      <!-- ============================================-->
      <!-- <section> begin ============================-->
      <section>

        <div class="container">
        
        
        
        
        
        </div>
        <!-- end of .container-->

      </section>
      <!-- <section> close ============================-->
      <!-- ============================================-->

   <!-- footer -->
   <%@ include file = "views/common/footer.jsp" %>
   

    <!-- ===============================================-->
    <!--    JavaScripts-->
    <!-- ===============================================-->
    <script src="vendors/@popperjs/popper.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.min.js"></script>
    <script src="vendors/is/is.min.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
    <script src="vendors/fontawesome/all.min.js"></script>
    <script src="assets/js/theme.js"></script>

  <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display&amp;family=Rubik:wght@300;400;500;600;700;800&amp;display=swap" rel="stylesheet">
</body>
</html>