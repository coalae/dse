<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
	<% String message = (String) request.getAttribute("message") ;%>    
 	<% if (session.getAttribute("username") == null){
 		request.getRequestDispatcher("userlogin.jsp").forward(request, response);
 		} else { %>
    <head>
        <!-- Basic Page Needs
        ================================================== -->
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>BusyBee To Do List Planner</title>
        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <!-- Mobile Specific Metas
        ================================================== -->
        <meta name="format-detection" content="telephone=no">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Template CSS Files
        ================================================== -->
        <!-- Twitter Bootstrs CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Ionicons Fonts Css -->
        <link rel="stylesheet" href="css/ionicons.min.css">
        <!-- animate css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Hero area slider css-->
        <link rel="stylesheet" href="css/slider.css">
        <!-- owl craousel css -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/owl.theme.css">
        <link rel="stylesheet" href="css/jquery.fancybox.css">
        <!-- template main css file -->
        <link rel="stylesheet" href="css/main.css">
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css">
        
        <!-- Template Javascript Files
        ================================================== -->
        <!-- modernizr js -->
        <script src="js/vendor/modernizr-2.6.2.min.js"></script>
        <!-- jquery -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <!-- owl carouserl js -->
        <script src="js/owl.carousel.min.js"></script>
        <!-- bootstrap js -->

        <script src="js/bootstrap.min.js"></script>
        <!-- wow js -->
        <script src="js/wow.min.js"></script>
        <!-- slider js -->
        <script src="js/slider.js"></script>
        <script src="js/jquery.fancybox.js"></script>
        <!-- template main js -->
        <script src="js/main.js"></script>
    </head>
    <body>
        <!--
        ==================================================
        Header Section Start
        ================================================== -->
        <header id="top-bar" class="navbar-fixed-top animated-header">
            <div class="container">
                <div class="navbar-header">
                    <!-- responsive nav button -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <!-- /responsive nav button -->
                    
                    <!-- logo -->
                    <div class="navbar-brand">
                        <a href="index.jsp" >
                            <img src="images/BusyBee1.4.PNG" alt="" align="right">
                            <br>
                        </a>
                    </div>
                    <!-- /logo -->
                </div>
                <br><br><br>
                <!-- main menu -->
                <nav class="collapse navbar-collapse navbar-right" role="navigation">
                    <div class="main-menu active">
                        <ul class="nav navbar-nav navbar-right">
                        <br><br><br>< 
                            <li>
                                <a href="indexLoggedIn.jsp" >Home</a>
                            </li> 
                                                        
                            <li><a href="meinprofil.jsp">Mein Profil</a></li>

                            <li><a href="ShowUserToDoListServlet">Meine ToDoListe</a></li>
                          
                            <li><a href="toDoHinzufuegen.jsp">ToDo hinzufuegen</a></li>

                            <li><a href="LogoutServlet">Logout</a></li>
							
							                            
                        </ul>
                    </div>
                </nav>
                <!-- /main nav -->
            </div>
        </header>
        


  <!-- NEUES TODO HIER ENTGEGENNEHMEN UND ANLEGEN -->			 
  <!--
        ==================================================
        Slider Section Start - USER-ACCOUNT ANLEGEN 
        ================================================== -->
        <section id="hero-area" >
            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div class="block wow fadeInUp" data-wow-delay=".3s">
                            
                            <!-- Slider -->
                            <section class="cd-intro">
                                <h1 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                                <span>Neues ToDo erfassen:</span><br><br>

                                </h1>
                                </section> <!-- cd-intro -->
                                <!-- /.slider -->


			 
					<form action="AddToDoServlet" Method="POST" >
					
 						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>Kategorie:</span><br>
						  <input type="text" name="category" required>
						  <br> </h2>						  
						
						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
                          <span>To Do Name:</span><br>
						  <input type="text" name="toDoName" required>
						  <br> </h2>

						  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
						  <span>	  <input type="submit" value="ToDo anlegen"> </span> 
						  </h2>
						  
					</form>	
	  
						  <br>				
					
					 
						<% if (message != null){%>
							  <h2 class="wow fadeInUp animated cd-headline slide" data-wow-delay=".4s" >
	                          <span> 
							<% 	out.println(message); %>
								</span><br>
						<%	} %>
							
						

						  <br>
						                                
                            </div>
                        </div>
                    </div>
                </div>
            </section><!--/#main-slider-->     
            
        </body>
        <%}%>
</html>