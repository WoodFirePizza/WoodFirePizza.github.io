$( document ).ready(function() {
        
        
        var request = new XMLHttpRequest();
        
        request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/food");
        request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");
        
         request.onload = function(){
             data = JSON.parse(request.responseText);
             htmlText = '';
             for ( var key in data ) {

                    name = data[key].MENU["Name"];

                    if (data[key].Type === "Pizza"){
                        htmlText += '<div class="col-md-4">';
                        htmlText += '<div class="thumbnail">';
                            
                            htmlText += '<img src="Res/Images/Pizza/bbqchicken.jpg" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                                
                            
                            htmlText += '<div class="caption">';
                                
                                        htmlText += '<fieldset>';
                                            htmlText += '<legend>' + data[key].MENU["Name"] + '</legend>';
                                            htmlText += '<p class="size">Delicious chicken Pizza</p>';
                                        htmlText += '</fieldset>';

                                                htmlText += '<div class="row">';
                                                   htmlText += '<div class="col-sm-12 col-md-6">';
                                                        htmlText += '<div class="form-group">';
                                                        htmlText += '<select class="form-control col-sm-2 size" id="pizzaSize'+ key +'">';
                                                            htmlText += '<option value="M">Size: Medium</option>';
                                                            htmlText += '<option value="L">Size: Large</option>';
                                                            htmlText += '<option value="XL">Size: X-Large</option>';
                                                          htmlText += '</select>';
                                                    htmlText += '</div>';
                                                    htmlText += '</div>';
                                                    
                                                   
                                                    
                                                    htmlText += '<div class="col-sm-6 col-md-3">';
                                                        htmlText += '<div class="input-group spinner'+key+'">';
                                                        htmlText += '<input type="text" class="form-control" value="1" min="1" id="quantity' + key +'">';
                                                        htmlText += '<div class="input-group-btn-vertical">';
                                                          htmlText += '<a class="up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                                          htmlText += '<a class="down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                                        htmlText += '</div>';
                                                    htmlText += '</div>';
                                                    htmlText += '</div>';
                                                    
                                                    htmlText += '<div class="col-sm-6 col-md-3">';
                                                        htmlText += '<h4>£' + data[key].MENU["Price"] + '</h4>';
                                                    htmlText += '</div>';
                                                htmlText += '</div>';
                                                

                                htmlText += '</div>';
                            htmlText += '<div class="btn-group btn-group-justified">';
                            htmlText += '<a  class="modal btn btn-info btn-lg" data-src="Res/Images/Pizza/bbqchicken.jpg" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'">Customise</a>';
                            htmlText += '<a  class="basket btn btn-success btn-lg" data-src="Res/Images/Pizza/bbqchicken.jpg" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'">Add To Basket</a>';
                            htmlText += '</div>';
                        htmlText += '</div>';
                    htmlText += '</div>';
                    }  
                }

            $('#pizza').append(htmlText);
        };

        
        
    request.send();
    
    
});

 