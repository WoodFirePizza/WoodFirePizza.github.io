$('body').on('click', 'a.completeCheckoutButton', function() {
    
    validateForm();
    

    
});

function validated(passed){
    
    if (passed === true){
        sendOrder();
    }
}

function sendOrder() {

    
  
    if($('#deliverRadio').is(':checked')) {  
        
        var tempAddress2 = document.getElementById('addressLineTwo').value;
        var tempTownCity = document.getElementById('townCity').value;
        
        
        
        if(tempTownCity === ""){
            tempTownCity = "Plymouth";
        }
        
        if(tempAddress2 === ""){
            tempAddress2 = " ";
        }
        
        var deliveryID = "1";
        var addressLine1 = document.getElementById('addressLineOne').value;
        var addressLine2 = tempAddress2 ;
        var townCity = tempTownCity;
        var county = "Devon";
        var postcode = document.getElementById('postcode').value;

        var tempAddress = {
            T_DELIVERY_ID: deliveryID,
            ADDRESS1: addressLine1,
            ADDRESS2: addressLine2,
            COUNTY: county,
            TOWN_CITY: townCity,
            POSTCODE: postcode
        };
        
         $.ajax({
             url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/tdeliveryaddress',
             type: 'POST',
             dataType: 'json',
             crossDomain: true,
             data: JSON.stringify(tempAddress),
             contentType: "application/json; charset=utf-8",
             beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
             },
             success: function (data, textStatus, xhr) {
                
                 var tempUrl = xhr.getResponseHeader("Location");
                 tempID = tempUrl.split("/");
                 sendToOrderTable(tempID[tempID.length-1], "Y");
              
             },
             error: function (xhr, textStatus, errorThrown) {
                 console.log('status: ' + xhr.responseText);
             }
        });
                 
                 
        

    };
    
    if($('#collectPizzaRadio').is(':checked')) {
        
        sendToOrderTable(null, "N");

    }
    
    

}

//Push to ORDER table
function sendToOrderTable(tempID, isDelivery){
    
    var d = new Date();
    
    var day = d.getDate();
    var monthIndex = d.getMonth();
    var fullYear = String(d.getFullYear());
    
    var year = fullYear.split("0");
    
    var monthNames = [
        "JAN",  "FEB",  "MAR",
         "APR",  "MAY",  "JUN",  "JUL",
         "AUG",  "SEP",  "OCT",
         "NOV",  "DEC"
    ];
    
    if(day < 10){
        day = String("0" + day);
    }
    
  
    var hour = d.getHours(); 
    var minute = d.getMinutes(); 
    var second = d.getSeconds(); 
    
                
    var orderID = "5";
    var memberID = null;
    var driverID = null;
    var totalPrice = parseFloat(JSON.parse(sessionStorage.getItem('total')));
    var deliveryAddressID = null;
    var tempDeliveryAddressID = tempID;
    var paid = "y";
    var date = day + '-' + monthNames[monthIndex] + '-' + year[1];
    var time = hour + ":" + minute + ":" + second;
    var delivery = isDelivery;

    if ($('#collectRadio').is(':checked')){
        paid = "N";
    }

    var sendOrder = {
     Order_ID: orderID,
     Member_ID: memberID,
     Driver_ID: driverID,
     Total_Price: totalPrice,
     DELIVERYADDRESSID: deliveryAddressID,
     TEMP_DELIVERYADDRESSID: tempDeliveryAddressID,
     ISPAID: paid,
     Date: date,
     Time: time,
     IsDelivery: delivery
    };

    $.ajax({
         url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/order',
         type: 'POST',
         dataType: 'json',
         crossDomain: true,
         data: JSON.stringify(sendOrder),
         contentType: "application/json; charset=utf-8",
         beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
        },
         success: function (data, textStatus, xhr) {
             
             var tempUrl = xhr.getResponseHeader("Location");
             tempID = tempUrl.split("/");
             sendOrderItem(tempID[tempID.length-1]);
         },
         error: function (xhr, textStatus, errorThrown) {
            console.log('Second Psot: ' + xhr.responseText);
         }
    });
    
}

function sendOrderItem(passedOrderID){
    
    var orderItemID = "5";
    var orderID = passedOrderID;
   
    order = JSON.parse(sessionStorage.getItem('basket'));
    
    for (var key in order){
        
        
    
    var sendOrderItem = {
         Order_Item_ID: orderItemID,
         Order_ID: orderID,
         Menu_ID: order[key].menuID,
         Quantity: order[key].quantity
    };
        
    $.ajax({
         url: 'http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/orderitem',
         type: 'POST',
         dataType: 'json',
         crossDomain: true,
         data: JSON.stringify(sendOrderItem),
         contentType: "application/json; charset=utf-8",
         beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic UFJDUzoyNTFP");
        },
         success: function (data, textStatus, xhr) {
             alert("Order complete and being prepared.");
             sessionStorage.clear();
             window.location.href = "index.html";
         },
         error: function (xhr, textStatus, errorThrown) {
            console.log('Something Bad has happened: ' + xhr.responseText);
         }
    });
    
    }
}

function validateForm()  
            { 
                var passed = false;
                var addressLineOne = document.getElementById('addressLineOne').value;
                    
                var postCode = document.getElementById('postcode').value;

        
                if (document.getElementById("deliverRadio").checked == true) {
                    
                    if (addressLineOne == null || addressLineOne == ""){  
                        $("#alert").html("Address line one can't be blank");
                        $('#alertModal').modal('show');
                        passed = false;  
                      } else {
                          passed = true;
                      }
                      
                      if (postCode == null || postCode == ""){  
                        $("#alert").html("Post code can't be blank");
                        $('#alertModal').modal('show');
                        passed = false;  
                      } else {
                          passed = true;
                      }
                }
                
                
                sendOrder(passed);
                  
}

