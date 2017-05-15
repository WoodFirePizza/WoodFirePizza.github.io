$(function() {
    
    var stickyPoint = ((window.innerHeight/100) * 15);
    
  $(window).scroll(function() {
    if ($(this).scrollTop() >= stickyPoint) {
      $("nav.main-nav").addClass("stickytop");
    } else {
      $("nav.main-nav").removeClass("stickytop");
    }
  });
});