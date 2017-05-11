$( document ).ready(function() {
    
    user = JSON.parse(localStorage.getItem('user'));
    
    var address1 = '';
    var address2 = '';
    var townCity = '';
    var postcode = '';
    
    var request = new XMLHttpRequest();

    request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/person");
    request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");

     request.onload = function(){
         data = JSON.parse(request.responseText);
         
         
         for (var key in data){
              if(user.personID === data[key].Person_ID  ){
                  address1 = data[key].Address1;
                  address2 = data[key].Address2;
                  townCity = data[key].Town_City;
                  postcode = data[key].Postcode;  
              }
         }
         
        
    
        $('body').find('.address1').val(address1);
        $('body').find('.address2').val(address2);
        $('body').find('.townCity').val(townCity);
        $('body').find('.postcode').val(postcode);
        
    };
  
    request.send();
    
    
    
});


