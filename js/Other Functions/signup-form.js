/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function validateform(){  
            var username=document.signupForm.Username.value; 
            var firstName=document.signupForm.First_Name.value;  
            var lastName=document.signupForm.Last_Name.value;
            var mobile=document.signupForm.Mobile.value;
            var email=document.signupForm.Email.value;
            var atpos=email.indexOf("@");
            var dotpos=email.lastIndexOf(".");
            var password=document.signupForm.Password.value; 
            var confirmPassword=document.signupForm.Confirm_Password.value; 
            var alert = $(".modal-alert");
            
            if (username === null || username === ""){  
                
                $("#alert").html("Username can't be blank");
                $('#alertModal').modal('show');  
                return false;  
            }

            if (firstName === null || firstName === ""){  
              $("#alert").html("First name can't be blank");
              $('#alertModal').modal('show');  
              return false;  
            }
            
            if (lastName === null || lastName === ""){  
              $("#alert").html("Last name can't be blank");
              $('#alertModal').modal('show');  
              return false;  
            }
            
            if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
            {
                $("#alert").html("Not a valid e-mail address");
                $('#alertModal').modal('show');  
                return false;
            }
            
            if (isNaN(mobile)){   
                $("#alert").html("Not a valid contact number");
                $('#alertModal').modal('show');
                return false;  
            }
            
        
            if(password.length < 6){  
              $("#alert").html("Password must be at least 6 characters long");
              $('#alertModal').modal('show');
              return false;  
              }  
              
              if(password === confirmPassword){  
                return true;  
              } else{  
                $("#alert").html("Password does not match! Please give correct password");
                $('#alertModal').modal('show');
                return false;  
              }  
 }  
            
function showPayPalEntry() {
    document.getElementById('enterPaypalEmail').style.display = "block";
}
            
            
function hidePayplayEntry() {            
    document.getElementById('enterPaypalEmail').style.display = "none";       
}
            
            
function sameAsPreviousEmail() {           
    var email = document.signupForm.Email.value;      
    var atpos = email.indexOf("@");       
    var dotpos = email.lastIndexOf(".");
                     
    document.getElementById('enterPaypalEmail').style.display = "none";
                       
    if (atpos < 1 || dotpos < atpos+2 || dotpos+2 >= email.length)      
    {      
        $("#alert").html("Make sure to first enter a valid email address in the personal info section");
        $('#alertModal').modal('show');
        return false;        
    }    
}


