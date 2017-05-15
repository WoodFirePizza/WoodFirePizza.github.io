$( document ).ready(function() {
        
        
        var request = new XMLHttpRequest();
        
        request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/food");
        request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");
        
         request.onload = function(){
             data = JSON.parse(request.responseText);
             htmlText = '';
             sideHtml = '';
             dessertHtml = '';
             for ( var key in data ) {

                    name = data[key].MENU["Name"];

                    if (data[key].Type === "Pizza"){
                        htmlText += '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">';
                        htmlText += '<div class="thumbnail">';
                        
                            //htmlText += '<div class="overlay">';
                            htmlText += '<img src="' + data[key].MENU["img_source"] + '" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                            //htmlText += '</div>';   
                            
                            htmlText += '<div class="caption">';
                                
                                        htmlText += '<fieldset>';
                                            htmlText += '<legend class="itemName">' + data[key].MENU["Name"] + '</legend>';
                                            htmlText += '<p class="size itemDescription">'+ data[key].MENU["Description"]+'</p>';
                                        htmlText += '</fieldset>';

                                                htmlText += '<div class="row">';
                                                
                                                    htmlText += '<div class="padding">'
                                                    
                                                   htmlText += '<div class="col-xs-12 col-sm-12 col-md-12" col-lg-5">';
                                                        htmlText += '<div class="form-group">';
                                                        htmlText += '<select class="form-control itemSize" id="pizzaSize'+ key +'">';
                                                            htmlText += '<option value="M">Size: Medium</option>';
                                                            htmlText += '<option value="L">Size: Large</option>';
                                                            htmlText += '<option value="XL">Size: X-Large</option>';
                                                          htmlText += '</select>';
                                                    htmlText += '</div>';
                                                    htmlText += '</div>';
                                                    
                                                   
                                                    
                                                    htmlText += '<div class="col-xs-6 col-sm-6 col-md-6" col-lg-3">';
                                                        htmlText += '<div class=" input-group spinner'+key+'">';
                                                            htmlText += '<input type="text" class="form-control" value="1" min="1" id="quantity' + key +'">';
                                                            htmlText += '<div class="input-group-btn-vertical">';
                                                                htmlText += '<a class="up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                                                htmlText += '<a class="down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                                            htmlText += '</div>';
                                                        htmlText += '</div>';
                                                    htmlText += '</div>';
   
                                                    htmlText += '</div'
                                                    
                                                    htmlText += '<div class=" itemPrice col-xs-6 col-sm-6 col-md-6" col-lg-4>';
                                                        htmlText += '<h5>£' + data[key].MENU["Price"] + '</h5>';
                                                    htmlText += '</div>';
                                                    
                                                 htmlText += '</div>';
                                                

                                htmlText += '</div>';
                            htmlText += '<div class="btn-group btn-group-justified">';
                                
                            htmlText += '<a  class="modal btn btn-info btn-lg  customiseButton" data-src="' + data[key].MENU["img_source"] + '" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">Customise</a>';
                            
                            
                            htmlText += '<a class="button basket btn btn-lg" data-src="' + data[key].MENU["img_source"] + '" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">';
                            htmlText += '<div class="table">';
                            htmlText += '<div class="cell">';
                                htmlText += '<span class="cartText">Add to Basket</span>';
                                htmlText += '<div class="icon cartText">';
                                        htmlText += '<i class="fa fa-shopping-basket cartText"></i>';
                                        htmlText += '<i class="fa fa-check cartText"></i>';
                                htmlText += '</div>';
                                htmlText += '</div>';
                                htmlText += '</div>';
                            htmlText += '</a>';
        
                          

                            
        
        
                            htmlText += '</div>';
                        htmlText += '</div>';
                    htmlText += '</div>';
                    }  
                    
                    if (data[key].Type === "Side"){
                        sideHtml += '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">';
                        sideHtml += '<div class="thumbnail">';
                            
                            sideHtml += '<img src="' + data[key].MENU["img_source"] + '" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                                
                            
                            sideHtml += '<div class="caption">';
                                
                                        sideHtml += '<fieldset>';
                                            sideHtml += '<legend class="itemName">' + data[key].MENU["Name"] + '</legend>';
                                            sideHtml += '<p class="size itemDescription">Delicious chicken Pizza</p>';
                                        sideHtml += '</fieldset>';

                                                sideHtml += '<div class="row">';
                                                
                                                    sideHtml += '<div class="padding">'
                                                    
                                                   sideHtml += '<div class="col-xs-12 col-sm-12 col-md-12" col-lg-5">';
                                                        sideHtml += '<div class="form-group">';
                                                        sideHtml += '<select class="form-control itemSize" id="pizzaSize'+ key +'">';
                                                            sideHtml += '<option value="S">Size: Single</option>';
                                                            sideHtml += '<option value="D">Size: Double (Sharing)</option>';
                                                          sideHtml += '</select>';
                                                    sideHtml += '</div>';
                                                    sideHtml += '</div>';
                                                    
                                                   
                                                    
                                                    sideHtml += '<div class="col-xs-6 col-sm-6 col-md-6" col-lg-3">';
                                                        sideHtml += '<div class=" input-group spinner'+key+'">';
                                                            sideHtml += '<input type="text" class="form-control" value="1" min="1" id="quantity' + key +'">';
                                                            sideHtml += '<div class="input-group-btn-vertical">';
                                                                sideHtml += '<a class="up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                                                sideHtml += '<a class="down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                                            sideHtml += '</div>';
                                                        sideHtml += '</div>';
                                                    sideHtml += '</div>';
   
                                                    sideHtml += '</div'
                                                    
                                                    sideHtml += '<div class=" itemPrice col-xs-6 col-sm-6 col-md-6" col-lg-4>';
                                                        sideHtml += '<h5>£' + data[key].MENU["Price"] + '</h5>';
                                                    sideHtml += '</div>';
                                                    
                                                 sideHtml += '</div>';
                                                

                                sideHtml += '</div>';
                            sideHtml += '<div class="btn-group btn-group-justified">';
                                
                            
                            
                            
                            sideHtml += '<a class="button basket btn btn-lg" data-src="' + data[key].MENU["img_source"] + '" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">';
                            sideHtml += '<div class="table">';
                            sideHtml += '<div class="cell">';
                                sideHtml += '<span class="cartText">Add to Basket</span>';
                                sideHtml += '<div class="icon cartText">';
                                        sideHtml += '<i class="fa fa-shopping-basket cartText"></i>';
                                        sideHtml += '<i class="fa fa-check cartText"></i>';
                                sideHtml += '</div>';
                                sideHtml += '</div>';
                                sideHtml += '</div>';
                            sideHtml += '</a>';
        
                          

                            
        
        
                            sideHtml += '</div>';
                        sideHtml += '</div>';
                    sideHtml += '</div>';
                    }
                    
                     if (data[key].Type === "Dessert"){
                        dessertHtml += '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">';
                        dessertHtml += '<div class="thumbnail">';
                            
                            dessertHtml += '<img src="' + data[key].MENU["img_source"] + '" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                                
                            
                            dessertHtml += '<div class="caption">';
                                
                                        dessertHtml += '<fieldset>';
                                            dessertHtml += '<legend class="itemName">' + data[key].MENU["Name"] + '</legend>';
                                            dessertHtml += '<p class="size itemDescription">Delicious chicken Pizza</p>';
                                        dessertHtml += '</fieldset>';

                                                dessertHtml += '<div class="row">';
                                                
                                                    dessertHtml += '<div class="padding">'
                                                    
                                                   dessertHtml += '<div class="col-xs-12 col-sm-12 col-md-12" col-lg-5">';
                                                        dessertHtml += '<div class="form-group">';
                                                        dessertHtml += '<select class="form-control itemSize" id="pizzaSize'+ key +'">';
                                                            dessertHtml += '<option value="S">Size: Single</option>';
                                                            dessertHtml += '<option value="D">Size: Double (Sharing)</option>';
                                                          dessertHtml += '</select>';
                                                    dessertHtml += '</div>';
                                                    dessertHtml += '</div>';
                                                    
                                                   
                                                    
                                                    dessertHtml += '<div class="col-xs-6 col-sm-6 col-md-6" col-lg-3">';
                                                        dessertHtml += '<div class=" input-group spinner'+key+'">';
                                                           dessertHtml += '<input type="text" class="form-control" value="1" min="1" id="quantity' + key +'">';
                                                            dessertHtml += '<div class="input-group-btn-vertical">';
                                                                dessertHtml += '<a class="up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                                                dessertHtml += '<a class="down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                                            dessertHtml += '</div>';
                                                        dessertHtml += '</div>';
                                                    dessertHtml += '</div>';
   
                                                    dessertHtml += '</div'
                                                    
                                                    dessertHtml += '<div class=" itemPrice col-xs-6 col-sm-6 col-md-6" col-lg-4>';
                                                        dessertHtml += '<h5>£' + data[key].MENU["Price"] + '</h5>';
                                                    dessertHtml += '</div>';
                                                    
                                                 dessertHtml += '</div>';
                                                

                                dessertHtml += '</div>';
                            dessertHtml += '<div class="btn-group btn-group-justified">';
                                
                            
                            
                            
                            dessertHtml += '<a class="button basket btn btn-lg" data-src="' + data[key].MENU["img_source"] + '" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">';
                            dessertHtml += '<div class="table">';
                            dessertHtml += '<div class="cell">';
                                dessertHtml += '<span class="cartText">Add to Basket</span>';
                                dessertHtml += '<div class="icon cartText">';
                                        dessertHtml += '<i class="fa fa-shopping-basket cartText"></i>';
                                        dessertHtml += '<i class="fa fa-check cartText"></i>';
                                dessertHtml += '</div>';
                                dessertHtml += '</div>';
                                dessertHtml += '</div>';
                            dessertHtml += '</a>';
        
                          

                            
        
        
                            dessertHtml += '</div>';
                        dessertHtml += '</div>';
                    dessertHtml += '</div>';
                    }

                    
                }

            $('#pizza').append(htmlText);
            $('#side').append(sideHtml);
            $('#dessert').append(dessertHtml);
        };

        
     request.send();   
    
    
    var requestDrink = new XMLHttpRequest();
        
        requestDrink.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/drink");
        requestDrink.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");
        
        requestDrink.onload = function(){
             data = JSON.parse(requestDrink.responseText);
             drinkHtml = '';
             for ( var key in data ) {
                 drinkHtml += '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">';
                        drinkHtml += '<div class="thumbnail">';
                            
                            drinkHtml += '<img src="' + data[key].MENU["img_source"] + '" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                                
                            
                            drinkHtml += '<div class="caption">';
                                
                                        drinkHtml += '<fieldset>';
                                            drinkHtml += '<legend class="itemName">' + data[key].MENU["Name"] + '</legend>';
                                            drinkHtml += '<p class="size itemDescription">Delicious chicken Pizza</p>';
                                        drinkHtml += '</fieldset>';

                                                drinkHtml += '<div class="row">';
                                                
                                                    drinkHtml += '<div class="padding">'
                                                    
                                                   drinkHtml += '<div class="col-xs-12 col-sm-12 col-md-12" col-lg-5">';
                                                        drinkHtml += '<div class="form-group">';
                                                        drinkHtml += '<select class="form-control itemSize" id="pizzaSize'+ key +'">';
                                                            drinkHtml += '<option value="500ml">Size: 500ml</option>';
                                                            drinkHtml += '<option value="750ml">Size: 750ml</option>';
                                                            drinkHtml += '<option value="1000ml">Size: 1000ml</option>';
                                                          drinkHtml += '</select>';
                                                    drinkHtml += '</div>';
                                                    drinkHtml += '</div>';
                                                    
                                                   
                                                    
                                                    drinkHtml += '<div class="col-xs-6 col-sm-6 col-md-6" col-lg-3">';
                                                        drinkHtml += '<div class=" input-group spinner'+key+'">';
                                                           drinkHtml += '<input type="text" class="form-control" value="1" min="1" id="quantity' + key +'">';
                                                            drinkHtml += '<div class="input-group-btn-vertical">';
                                                                drinkHtml += '<a class="up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                                                drinkHtml += '<a class="down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                                            drinkHtml += '</div>';
                                                        drinkHtml += '</div>';
                                                    drinkHtml += '</div>';
   
                                                    drinkHtml += '</div'
                                                    
                                                    drinkHtml += '<div class=" itemPrice col-xs-6 col-sm-6 col-md-6" col-lg-4>';
                                                        drinkHtml += '<h5>£' + data[key].MENU["Price"] + '</h5>';
                                                    drinkHtml += '</div>';
                                                    
                                                 drinkHtml += '</div>';
                                                

                                drinkHtml += '</div>';
                            drinkHtml += '<div class="btn-group btn-group-justified">';
                                
                            
                            
                            
                            drinkHtml += '<a class="button basket btn btn-lg" data-src="' + data[key].MENU["img_source"] + '" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">';
                            drinkHtml += '<div class="table">';
                            drinkHtml += '<div class="cell">';
                                drinkHtml += '<span class="cartText">Add to Basket</span>';
                               drinkHtml += '<div class="icon cartText">';
                                        drinkHtml += '<i class="fa fa-shopping-basket cartText"></i>';
                                        drinkHtml += '<i class="fa fa-check cartText"></i>';
                                drinkHtml += '</div>';
                                drinkHtml += '</div>';
                                drinkHtml += '</div>';
                            drinkHtml += '</a>';
        
                          

                            
        
        
                            drinkHtml += '</div>';
                        drinkHtml += '</div>';
                    drinkHtml += '</div>';
             }
             
             $('#drink').append(drinkHtml);
             
         };
    
        requestDrink.send();
});

 