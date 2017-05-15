$(function() {
    
    var stickyPoint = ((window.innerHeight/100) * 15);
  $( window ).resize(function() {
      
  if((this).$(window).width() > 1025)
  {
    $("nav.main-nav").removeClass("navbar-fixed-top");
  }
  else
  {
    $("nav.main-nav").addClass("navbar-fixed-top");
  }
    
   });
  $(window).scroll(function() {
    
    if((this).$(window).width() > 1025)
    {
    if ($(this).scrollTop() >= stickyPoint) {
        $("nav.main-nav").removeClass("navbar-fixed-top");
        $("nav.main-nav").addClass("stickytop");
    } else {
      $("nav.main-nav").removeClass("stickytop");
    }
    }
    else
    {
      $("nav.main-nav").addClass("navbar-fixed-top");
    }
  });
});