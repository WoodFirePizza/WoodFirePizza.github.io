$( document ).ready(function() {
    
    navHtml = '<div class="nav-wrapper">';
    navHtml += '<nav class="navbar navbar-default main-nav">';
        navHtml += '<div class="container-fluid">';
          //<!-- Brand and toggle get grouped for better mobile display -->
          navHtml += '<div class="navbar-header">';
             
            navHtml += '<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">';
              navHtml += '<span class="sr-only">Toggle navigation</span>';
              navHtml += '<span class="icon-bar"></span>';
              navHtml += '<span class="icon-bar"></span>';
              navHtml += '<span class="icon-bar"></span>';
            navHtml += '</button>';
            
            navHtml += '<a href="#" id = "mobileBasket" class ="nav-link mobileBasket" data-toggle="modal" data-target="#basketModal" id="myBasket"><i class="fa fa-shopping-basket" aria-hidden="true"></i></a>';
            navHtml += '<a class="navbar-brand" id="desktopHide" href="index.html">Wood Fire</a>';
    
            
          navHtml += '</div>';

          //<!-- Collect the nav links, forms, and other content for toggling -->
          navHtml += '<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">';
            navHtml += '<ul class="nav navbar-nav">';
                
                
                navHtml += '<li><a class="navItem" href="pizza.html"><img src="Res/Icons/004-food-1.png" alt=""/>    Pizza</a></li>';
                navHtml += '<li><a class="navItem" href="sides.html"><img src="Res/Icons/005-food.png" alt=""/>  Sides</a></li>';
                navHtml += '<li><a class="navItem" href="desserts.html"><img src="Res/Icons/002-food-2.png" alt=""/>  Desserts</a></li>';
                navHtml += '<li><a class="navItem" href="drinks.html"><img src="Res/Icons/003-drink.png" alt=""/>   Drinks</a></li>';
                
                //navHtml += '<li><a href="#" id="guest desktopHide" data-toggle="modal" data-target="#loginModal"><img src="Res/Icons/002-person.png" alt=""/>Sign-In / Sign-Up</a></li>';
                
            navHtml += '</ul>';
              
          navHtml += '</div>';
        //<!-- /.navbar-collapse -->
        navHtml += '</div>';
        //<!-- /.container-fluid -->
    navHtml += '</nav>';
    navHtml += '</div>';

    
    
    $('body').find('.drawNav').html(navHtml);
    
});
