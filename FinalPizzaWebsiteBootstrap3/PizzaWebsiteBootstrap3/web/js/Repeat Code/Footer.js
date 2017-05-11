$( document ).ready(function() {
        
        //<!-- Footer -->
        footerHtml = '<footer class="hiddenFooter">';
            footerHtml += '<div class="container">';
                footerHtml += '<div class="row" >';
                    
                    //<!-- Address -->
                    footerHtml += '<div class="col-lg-3 col-md-4 col-xs-4" id="location">';
                        footerHtml += '<b>Store Location</b>';
                        footerHtml += '<br/>';
                        footerHtml += '<p>Wood Fire <br>';
                            footerHtml += '13 Mutley Plain <br>';
                            footerHtml += 'Plymouth <br>';
                            footerHtml += 'PL4 8HY';
                        footerHtml += '</p>';
                    footerHtml += '</div>';
                    
                    //<!-- Navigation -->
                    footerHtml += '<div class="col-md-2" id="nav">';
                        footerHtml += '<b>Navigation</b>';
                        footerHtml += '<p>               ';
                           footerHtml += ' <a href="pizza.html">Pizza</a></br>';
                            footerHtml += '<a href="#">Sides</a></br>';
                            footerHtml += '<a href="#">Desserts</a></br>';
                            footerHtml += '<a href="#">Drinks</a></br>';
                        footerHtml += '</p>';
                    footerHtml += '</div>';
                    
                    //<!-- Paypal -->
                    footerHtml += '<div class="col-lg-2 col-md-4  col-xs-4" id="paypal">';
                        footerHtml += '<b>We accept</b>';
                      footerHtml += '  <a href="https://www.paypal.co.uk" target="_blank"><img src="Res/PayPal.png" alt=""/></a>';
                   footerHtml += ' </div>';
                    
                    //<!-- Contact -->
                   footerHtml += ' <div class="col-lg-2 col-md-4 col-xs-4" id="contact">';
                    footerHtml += '    <b>Contact Us</b>';
                    footerHtml += '    <br/>';
                    footerHtml += '    <br/>';
                    footerHtml += '    <p>1604 666 222 <br>';
                     footerHtml += '   <a href="#">info@woodfire.com</a> <br>';
                           
                       footerHtml += ' </p>';

                   footerHtml += ' </div>';
                    
                   // <!-- Social -->
                   footerHtml += ' <div class="col-lg-3 col-md-3" id="social">';
                   footerHtml += ' <b>Social Media</b>';
                   footerHtml += '  <br>';
                   footerHtml += ' <br/>';
                   footerHtml += ' <button type="button" class="btn btn-outline-fb" onclick="window.open( "https://en-gb.facebook.com/")" ><i class="fa fa-facebook-square" aria-hidden="true"></i></button>';
                   footerHtml += ' <button type="button" class="btn btn-outline-tw" onclick="window.open("https://twitter.com/?lang=en")"><i class="fa fa-twitter-square" aria-hidden="true"></i></button>';
                   footerHtml += ' <button type="button" class="btn btn-outline-in" onclick="window.open("https://www.instagram.com/?hl=en")"><i class="fa fa-instagram" aria-hidden="true"></i></button>';
                       
                   footerHtml += ' </div> ';
                footerHtml += '</div>';
                
                //<!-- Copyright -->
                footerHtml += '<div class="footer-copyright" id="copyright">';
                        footerHtml += '<div class="container-fluid">© 2017 Copyright: <a href="https://www.woodfire.com"  rel="nofollow"> woodfire.com </a> </div>';
                    footerHtml += '</div>';
                footerHtml += '</div>';
        footerHtml += '</footer>';

$('body').find('.drawFooter').html(footerHtml);
    
});


