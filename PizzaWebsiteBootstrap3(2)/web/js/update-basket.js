$( "#myBasket" ).click(function() {
            
        drawBasket();
            
});

$('body').on('click', 'a.delete', function() {
    var name = $(this).attr('data-name');
    
    if(sessionStorage.basket)
    {
     basket = JSON.parse(sessionStorage.getItem('basket'));
    }else{
     basket = [];
    }
    
    for( var key in basket ) {
        var menuItem = basket[key];
        var menuItemName = menuItem.name;	
        if( menuItemName === name ) {
                basket.splice( key, 1 );
        }
    }
            
    
    sessionStorage.setItem('basket', JSON.stringify(basket));
    
    drawBasket();   
    
});


$('body').on('click', 'a.price', function() {
    
    var updatedBasket = [];

    $('table#myBasket > tbody > tr').each(function() {
         var $row = $(this).closest('tr'); 
         var tname = $.trim( $row.find( ".pname" ).text() );
         var tsize = $.trim( $row.find( ".psize" ).text() );
         var timg = $.trim( $row.find( ".pimg" ).children('img').attr('src'));
         var tqty =  parseInt($.trim($row.find( ".pqty > .dqty > .qty" ).val()));
         var tprice = $.trim( $row.find( ".pprice" ).data('price'));
         
          var basketObj = {
                name: tname,
                price: tprice,
                size: tsize,
                quantity: tqty,
                image: timg
            };
            
            updatedBasket.push(basketObj);
    }),
     
    sessionStorage.setItem('basket', JSON.stringify(updatedBasket));
    
    drawBasket();
});

function drawBasket(){
    var basket = JSON.parse(sessionStorage.getItem('basket'));
            var total = 0.00;
            var price = 0.00;
            var modalBody = '';
            var modalFooter = '';
            var modalTotal = '';
            
            
            
            if(basket.length > 0)
            {
            
            for ( var key in basket ) {
                
                price = basket[key].price  * basket[key].quantity;
                total += price;
                
                modalBody += '<tr><td class="pimg text-center"><img width="30px" height="30px" src="' + basket[key].image + '"/></td>';
                modalBody += '<td class="pname ">'+ basket[key].name +'</td>';
                modalBody += '<td class="psize ">'+ basket[key].size +'</td>';
                modalBody += '<td class="pqty">';
                            modalBody += '<div class="dqty input-group spinner'+key+'" style="width:50%">';
                                modalBody += '<input type="text" class="qty form-control" value="'+basket[key].quantity+'" min="1" id="quantityBasket' + key +'">';
                                modalBody += '<div class="input-group-btn-vertical">';
                                  modalBody += '<a class="price up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                  modalBody += '<a class="price down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                modalBody += '</div>';
                            modalBody += '</div>';
                modalBody +='</td>';
                
                if (price >= 10){
                modalBody += '<td class="pprice " data-price="'+basket[key].price+'">£'+ price.toPrecision(4) +'</td>';
                } else {
                modalBody += '<td class="pprice " data-price="'+basket[key].price+'">£'+ price.toPrecision(3) +'</td>';
                }
                
                modalBody += '<td class="pdelete text-center"><a class="delete btn" data-key="'+ key +'"  data-name="' + basket[key].name + '"><i class="fa fa-times-circle-o" aria-hidden="true"></i></a></td></tr>';
            
            }
            
            
            
            if(total >= 10){
                modalTotal += '<h4>Total: £'+total.toPrecision(4)+'</h4>';
            } else {
                modalTotal += '<h4>Total: £'+total.toPrecision(3)+'</h4>';
            }
            
            modalFooter += '<div class="btn-group btn-group-justified">';
            modalFooter += '<a  class="btn btn-info btn-lg" data-dismiss="modal" >Continue Shopping</a>';
            modalFooter += '<a  class="btn btn-success btn-lg" href="checkout.html" >CheckOut</a>';
            modalFooter += '</div>';
            
            $('#basketModal').find('.myBasket').html(modalBody);
            $('#basketModal').find('.total').html(modalTotal);
            $('#basketModal').find('.modal-footer').html(modalFooter);
            
            $('body').find('.orderSummary').html(modalBody);
            $('body').find('.total').html(modalTotal);
        } else {
            $('#basketModal').find('.myBasket').html("<div class='alert alert-danger' role='alert' id='idEmptyCartMessage'>Your basket is empty</div>");
            
            modalFooter += '<div class="btn-group btn-group-justified">';
            modalFooter += '<a  class="btn btn-info btn-lg" data-dismiss="modal" >Continue Shopping</a>'; 
            modalFooter += '</div>';
            
            $('#basketModal').find('.total').html('<h4>Total: £0.00</h4>');
            $('#basketModal').find('.modal-footer').html(modalFooter);
        }
}