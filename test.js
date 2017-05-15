if (data[key].Type === "Pizza"){
                        sideHtml += '<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">';
                        sideHtml += '<div class="thumbnail">';
                            
                            sideHtml += '<img src="Res/Pizza/bbqchicken.jpg" class="img-responsive" alt="' + data[key].MENU["Name"] + '">';
                                
                            
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
                                
                            sideHtml += '<a  class="modal btn btn-info btn-lg  customiseButton" data-src="Res/Pizza/bbqchicken.jpg" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'">Customise</a>';
                            
                            
                            sideHtml += '<a class="button basket btn btn-lg" data-src="Res/Pizza/bbqchicken.jpg" data-name="' + data[key].MENU["Name"] + '" data-price="' + data[key].MENU["Price"] + '" data-description="delicious" data-key="'+ key +'" data-menuID="' + data[key].Menu_ID + '">';
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
