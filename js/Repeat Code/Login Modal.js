
$( document ).ready(function() {
        loginHtml = '<div class="modal fade" id="loginModal" role="dialog">';
              loginHtml += '<div class="modal-dialog">';

               // <!-- Modal content-->
                loginHtml += '<div class="modal-content">';
                  loginHtml += '<div class="modal-header" style="padding:35px 50px;">';
                    loginHtml += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
                    loginHtml += '<h4><span class="glyphicon glyphicon-lock"></span> Login</h4>';
                  loginHtml += '</div>';
                  loginHtml += '<div class="modal-body" style="padding:40px 50px;">';

                      loginHtml += '<div class="form-group">';
                        loginHtml += '<label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>';
                        loginHtml += '<input type="text" class="form-control" id="username" placeholder="Enter username">';
                      loginHtml += '</div>';
                      loginHtml += '<div class="form-group">';
                        loginHtml += '<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>';
                        loginHtml += '<input type="text" class="form-control" id="password" placeholder="Enter password">';
                      loginHtml += '</div>';
                      loginHtml += '<div class="checkbox">';
                        loginHtml += '<label><input type="checkbox" value="" checked>Remember me</label>';
                      loginHtml += '</div>';
                        loginHtml += '<button  id="loginBtn" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>';

                  loginHtml += '</div>';
                  loginHtml += '<div class="modal-footer">';
                    loginHtml += '<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>';
                    loginHtml += '<p>Not a member? <a href="signup.html">Sign Up</a></p>';
                    loginHtml += '<p>Forgot <a href="#">Password?</a></p>';
                  loginHtml += '</div>';
                loginHtml += '</div>';

              loginHtml += '</div>';
            loginHtml += '</div>'; 
            
            $('body').find('.drawLoginModal').html(loginHtml);

});
