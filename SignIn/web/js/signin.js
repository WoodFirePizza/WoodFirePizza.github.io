$( document ).ready(function() {
    var request = new XMLHttpRequest();
        
        request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/userlogin");
        request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");
        
         request.onload = function(){
             data = JSON.parse(request.responseText);
             console.log(JSON.stringify(data));
             
             for ( var key in data ) {

                    console.log(data[key].Username);
                    console.log(data[key].Password);

                    
                    
                }

            
        };

        
        
    request.send();
    
 });