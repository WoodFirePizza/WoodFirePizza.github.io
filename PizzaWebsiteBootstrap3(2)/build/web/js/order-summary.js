$( document ).ready(function() {
           drawOrderSummary();         
});

$('body').on('click', 'a.deleteOrder', function() {
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
    
    drawOrderSummary();   
    
});


$('body').on('click', 'a.priceOrder', function() {
    
    var updatedBasket = [];

    $('table#orderSummary > tbody > tr').each(function() {
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
    
    drawOrderSummary();
});

function drawOrderSummary(){
    var basket = JSON.parse(sessionStorage.getItem('basket'));
            var total = 0.00;
            var price = 0.00;
            var orderSummaryTable = '';
            var buttons = '';
            var totalText = '';
            
            
            
            for ( var key in basket ) {
                
                price = basket[key].price * basket[key].quantity;
                total += price;
                
                
                orderSummaryTable += '<tr><td class="pimg text-center"><img width="30px" height="30px" src="' + basket[key].image + '"/></td>';
                orderSummaryTable += '<td class="pname ">'+ basket[key].name +'</td>';
                orderSummaryTable += '<td class="psize ">'+ basket[key].size +'</td>';
                orderSummaryTable += '<td class="pqty">';
                            orderSummaryTable += '<div class="dqty input-group spinner'+key+'" style="width:50%">';
                                orderSummaryTable += '<input type="text" class="qty form-control" value="'+basket[key].quantity+'" min="1" id="quantityBasket' + key +'">';
                                orderSummaryTable += '<div class="input-group-btn-vertical">';
                                  orderSummaryTable += '<a class="priceOrder up btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-up"></i></a>';
                                  orderSummaryTable += '<a class="priceOrder down btn btn-default" type="button" data-key="'+ key +'"><i class="fa fa-caret-down"></i></a>';
                                orderSummaryTable += '</div>';
                            orderSummaryTable += '</div>';
                orderSummaryTable +='</td>';
                
                if (price >= 10){
                orderSummaryTable += '<td class="pprice " data-price="'+basket[key].price+'">£'+ price.toPrecision(4) +'</td>';
                } else {
                orderSummaryTable += '<td class="pprice " data-price="'+basket[key].price+'">£'+ price.toPrecision(3) +'</td>';
                }
                
                orderSummaryTable += '<td class="pdelete text-center"><a class="delete deleteOrder btn" data-key="'+ key +'"  data-name="' + basket[key].name + '"><i class="fa fa-times-circle-o" aria-hidden="true"></i></a></td></tr>';
            
            }
            
            
            
            if(total >= 10){
                totalText += '<h4>Total: £'+total.toPrecision(4)+'</h4>';
            } else {
                totalText += '<h4>Total: £'+total.toPrecision(3)+'</h4>';
            }
            
            buttons += '<div class="btn-group btn-group-justified">';
            buttons += '<a  class="btn btn-info btn-lg" >Continue Shopping</a>';
            buttons += '</div>';
            
            $('body').find('.orderSummary').html(orderSummaryTable);
            $('body').find('.total').html(totalText);
            $('body').find('.back').html(buttons);
}


