/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('body').on('click', 'a.modal', function() {
            var key = $(this).attr('data-key');
            var menuID = $(this).attr('data-menuID');
            var name = $(this).attr('data-name');
            var price = $(this).attr('data-price');
            var size = document.getElementById('pizzaSize' + key).value;
            var description = $(this).attr('data-description');
            var img = $(this).attr('data-src');
            
            modalBody = '';
            
            modalBody += '<div class="row">';
                modalBody += '<div class="col-md-6">';
                    modalBody += '<img class="img-responsive" src="'+ img +'" width="320" height="220"/>';
                modalBody += '</div>';
            modalBody += '<div class="col-md-6">';
            modalBody += '<h4>'+ description +'</h4>';
            modalBody += '<div class="row">';
                                                modalBody += '<div class="col-md-7">';
                                                    modalBody += '<div class="form-group">';
                                                        modalBody += '<select class="form-control col-sm-2" id="modalPizzaSize">';
                                                            modalBody += '<option value="M">Size: Medium</option>';
                                                            modalBody += '<option value="L">Size: Large</option>';
                                                            modalBody += '<option value="XL">Size: X-Large</option>';
                                                          modalBody += '</select>';
                                                    modalBody += '</div>';
                                                modalBody += '</div>';
                                                
                                                modalBody += '<div class="col-md-4">';
                                                    modalBody += '<h4>£'+ price +'</h4>';
                                                modalBody += '</div>';
//                                                modalBody += '<div class="row">';
//                                                
//                                                var request = new XMLHttpRequest();
//                                                request.open("get", "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251o/api/ingredient");
//                                                request.setRequestHeader("Authorization", "Basic UFJDUzoyNTFP");
//
//                                                 request.onload = function(){
//                                                     data = JSON.parse(request.responseText);
//                                                     modalBody = '';
//
//                                                     for ( var key in data ) {
//
//                                                         modalBody += '<div class="col-md-6">';
//                                                          modalBody += '<div class="checkbox">';
//                                                          modalBody += '<label><input type="checkbox" value="">'+ data[key].Description +'</label>';
//                                                          modalBody += '</div>';
//                                                         modalBody += '<div/>';
//
//                                                     }
//                                                     
//                                                 };
//
//                                                 request.send();
//                                                
//               modalBody += '</div>';                                 
            modalBody += '</div>';
            modalBody += '</div>';
            modalBody += '</div>';
            
            
            var modalFooter = '';
            modalFooter += '<div class="btn-group btn-group-justified">';
                            modalFooter += '<a  class="btn btn-info btn-lg" data-dismiss="modal" >Continue Shopping</a>';
                            modalFooter += '<a  class="modalBasket btn btn-success btn-lg" data-src="Res/Pizza/bbqchicken.jpg" data-name="' + name + '" data-name="' + name + '" data-price="' + price + '" data-description="delicious" data-key="'+ key +'" data-menuID="'+ menuID +'" data-dismiss="modal">Add To Basket</a>';
            modalFooter += '</div>';
            
            $('#menuModal').find('.modal-title').text(name);
            $('#menuModal').find('.modal-body').html(modalBody);
            $('#menuModal').find('.modal-footer').html(modalFooter);
            $('#menuModal').modal('show');
        });
