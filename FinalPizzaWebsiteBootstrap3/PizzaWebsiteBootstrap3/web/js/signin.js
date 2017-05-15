$( document ).ready(function() {
    
    if(localStorage.user){
        user = JSON.parse(localStorage.getItem('user'));
        $("#memberDropdown").html('Hello, ' + user.username + '  ' + '<span class="caret"></span>');
        $("#member").toggle();
        $("#guest").toggle();
    }
    
    
    
});


$( "#loginBtn" ).click(function() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var matchBoolean = false;
    
    
    var request = new XMLHttpRequest();

    request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin");
    request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");

     request.onload = function(){
         data = JSON.parse(request.responseText);

         for ( var key in data ) {

             if(username === data[key].Username  ){
                 if(password === data[key].Password ){
                    personId = data[key].Person_ID;
                    user = data[key].Username;
                    matchBoolean = true;
                 }
            }
        }  
        
        if (matchBoolean === true){
            
             var userObj = {
                personID: personId,
                username: username
            };
            
            localStorage.setItem('user', JSON.stringify(userObj));
            location.reload();

        } else {
            alert("WRONG!");
        }
    };
  
    request.send();
    
    
    
 });

$('body').on('click', 'a.signout', function() { 
     localStorage.clear();
     location.reload();
 
 });