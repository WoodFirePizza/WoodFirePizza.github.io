//$('body').on('click', 'a.signUpButton', function() {
//    
//    validateForm();
//    
//    
//
//    
//});

function validated(passed){
    
    if (passed === true){
        signup();
    }
}


function signup(){
    
    var personID = "1";
    var firstName = "Kieran";
    var lastName = "Bass";
    var dob = "13-OCT-93";
    var email = "givemeza@pizzalover.com";
    var mobile = "07956067899";
    var townCity = "townCity";
    var county = "county";
    var postcode = "postcode";
    var addressOne = "addressTwo";
    var addressTwo = "addressOne";
    
    var personDetails = {
        Person_ID: personID,
        Forename: firstName,
        Surname: lastName,
        DOB: dob,
        Email: email,
        Mobile_Number: mobile,
        Town_City: townCity,
        County: county,
        Postcode: postcode,
        Address2: addressTwo,
        Address1: addressOne
    };
    
    $.ajax({
             url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/person',
             type: 'POST',
             dataType: 'json',
             crossDomain: true,
             data: JSON.stringify(personDetails),
             contentType: "application/json; charset=utf-8",
             beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
             },
             success: function (data, textStatus, xhr) {
                 console.log("Into the person table we go");
                
                 var tempUrl = xhr.getResponseHeader("Location");
                 tempID = tempUrl.split("/");
                 sendToMemberTable(tempID[tempID.length-1]);
             },
             error: function (xhr, textStatus, errorThrown) {
                 alert('status: ' + xhr.responseText);
             }
        });
}

function sendToMemberTable(tempID){
    
    var memberID = "1";
    var personID = tempID;
    var rewardPoints = 0;
   
    
    var memberDetails = {
        Member_ID: memberID,
        Person_ID: personID,
        Reward_Points: rewardPoints
    };
    
    $.ajax({
             url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/member',
             type: 'POST',
             dataType: 'json',
             crossDomain: true,
             data: JSON.stringify(memberDetails),
             contentType: "application/json; charset=utf-8",
             beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
             },
             success: function (data, textStatus, xhr) {
                 console.log("Into the member table we go");
                
                userLoginTable(tempID);

             },
             error: function (xhr, textStatus, errorThrown) {
                 alert('status: ' + xhr.responseText);
             }
        });
}

function userLoginTable(tempID){
    var ID = "1";
    var personID = tempID;
    var username = "Kieran";
    var password = "WoodFire1";
   
    
    var userDetails = {
        ID: ID,
        Person_ID: personID,
        Username: username,
        Password: password
    };
    
    $.ajax({
             url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin',
             type: 'POST',
             dataType: 'json',
             crossDomain: true,
             data: JSON.stringify(userDetails),
             contentType: "application/json; charset=utf-8",
             beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
             },
             success: function (data, textStatus, xhr) {
                 console.log("Into the userlogin table we go");
                
                 alert("Signup complete, please login");
                 window.location.href = "index.html";
             },
             error: function (xhr, textStatus, errorThrown) {
                 alert('status: ' + xhr.responseText);
             }
        });
}

function validateform(){  
    
            var passed = false;
            var addressLine1 = document.getElementById('address1').value;
            var postcode = document.getElementById('postcode').value;
            var username=document.getElementById('username').value;; 
            var firstName=document.getElementById('firstname').value;;  
            var lastName=document.getElementById('lastname').value;;
            var mobile=document.getElementById('mobile').value;;
            var email=document.getElementById('email').value;;
            var atpos=email.indexOf("@");
            var dotpos=email.lastIndexOf(".");
            var password=document.getElementById('password').value;; 
            var confirmPassword=document.getElementById('confirmpassword').value;; 
            var alert = $(".modal-alert");
            
            if (username === null || username === ""){  
                
                $("#alert").html("Username can't be blank");
                $('#alertModal').modal('show');  
                passed = false;  
            }
            else {
                          passed = true;
                      }

            if (firstName === null || firstName === ""){  
              $("#alert").html("First name can't be blank");
              $('#alertModal').modal('show');  
              return false;  
            } else {
                          passed = true;
                      }
            
            if (lastName === null || lastName === ""){  
              $("#alert").html("Last name can't be blank");
              $('#alertModal').modal('show');  
              passed = false;  
            } else {
                          passed = true;
                      }
            
            if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
            {
                $("#alert").html("Not a valid e-mail address");
                $('#alertModal').modal('show');  
                passed = false;
            }
            
            if (isNaN(mobile)){   
                $("#alert").html("Not a valid contact number");
                $('#alertModal').modal('show');
                passed = false;  
            } else {
                          passed = true;
                      }
            
        
            if(password.length < 6){  
              $("#alert").html("Password must be at least 6 characters long");
              $('#alertModal').modal('show');
              passed = false;  
              } else {
                          passed = true;
                      }
              
              if(password === confirmPassword){  
                passed = true;  
              } else{  
                $("#alert").html("Password does not match! Please give correct password");
                $('#alertModal').modal('show');
                passed = false;  
              }  
              
              
                
                    
                    if (addressLineOne === null || addressLineOne === ""){  
                        $("#alert").html("Address line one can't be blank");
                        $('#alertModal').modal('show');
                        passed = false;  
                      } else {
                          passed = true;
                      }
                      
                      if (postCode === null || postCode === ""){  
                        $("#alert").html("Post code can't be blank");
                        $('#alertModal').modal('show');
                        passed = false;  
                      } else {
                          passed = true;
                      }
                
                
                
                signup();
 }  

