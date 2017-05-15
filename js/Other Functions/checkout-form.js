/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function newDeliveryAddress(){
                document.getElementById('newDeliveryAddress').style.display = "block";
            }
            
            function hideDeliveryOptions() {
                document.getElementById('newDeliveryAddress').style.display = "none";
            }
            
//            function showPayWithCard() {
//                document.getElementById('cardPaymentThumbnail').style.display = "block";
//                document.getElementById('paypalThumbnail').style.display = "none";
//            }
            
            function hidePayment() {
//                document.getElementById('cardPaymentThumbnail').style.display = "none";
                document.getElementById('paypalThumbnail').style.display = "none";
            }
            
            function showPayPal() {
                document.getElementById('paypalThumbnail').style.display = "block";
//                document.getElementById('cardPaymentThumbnail').style.display = "none";
            }
            
            function validateForm()  
            { 
                var addressLineOne = document.checkoutForm.addressOne.value;
                var postCode = document.checkoutForm.postCode.value;
                var nameOnCard = document.checkoutForm.cardHolderName.value; 
                var cardNumEntry = document.checkoutForm.cardNumber.value;
                var cvv = document.checkoutForm.cvv.value;
                var email = document.checkoutForm.paypal.value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".");
                /*var exMonth = document.checkoutForm.expiryMonth.value;
                var exYear = document.checkoutForm.expiryYeary.value;*/
                var visaCardNum = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;  
                
                /*today = new Date();
                someday = new Date();
                someday.setFullYear(exYear, exMonth, 1);*/
        
                if (document.getElementById("deliverRadio").checked == true) {
                    
                    if (addressLineOne == null || addressLineOne == ""){  
                        $("#alert").html("Address line one can't be blank");
                        $('#alertModal').modal('show');
                        return false;  
                      }
                      
                      if (postCode == null || postCode == ""){  
                        $("#alert").html("Post code can't be blank");
                        $('#alertModal').modal('show');
                        return false;  
                      }
                }
                
                if (document.getElementById("cardRadio").checked == true) {
                    
                    if (nameOnCard === null || nameOnCard === ""){  
                        $("#alert").html("Name on card can't be blank");
                        $('#alertModal').modal('show');
                        return false;  
                      }
                      
                    /*if (someday < today) {
                        alert("The expiry date is before today's date. Please select a valid expiry date");
                        return false;
                      }*/
                    
                   if(cardNumEntry.match(visaCardNum))  { 
                        return true;  
                      } else {
                        $("#alert").html("Not a valid Visa credit card number");
                        $('#alertModal').modal('show');
                        return false;  
                      }
                      
                     if (cvv == null || cvv == ""){  
                        $("#alert").html("cvv can't be blank");
                        $('#alertModal').modal('show');
                        return false;  
                      }
                }

                if (document.getElementById("paypalRadio").checked == true) {
                    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
                      $("#alert").html("Not a valid e-mail address");
                      $('#alertModal').modal('show');
                      return false;
                  }
                }
            }
                
             
