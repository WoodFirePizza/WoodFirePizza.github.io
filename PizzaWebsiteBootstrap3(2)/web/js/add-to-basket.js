$('body').on('click', 'a.basket', function() {
            var key = $(this).attr('data-key');
            var name = $(this).attr('data-name');
            var price = $(this).attr('data-price');
            var size = document.getElementById('pizzaSize' + key).value;
            var qty = document.getElementById('quantity' + key).value;
            var img = $(this).attr('data-src');
            
            var basketObj = {
                name: name,
                price: price,
                size: size,
                quantity: qty,
                image: img
            };
            
            if(sessionStorage.basket)
            {
             basket = JSON.parse(sessionStorage.getItem('basket'));
            }else{
             basket = [];
            }
            
            basket.push(basketObj);
            sessionStorage.setItem('basket', JSON.stringify(basket));
               
});

$('body').on('click', 'button.offer', function() {
            
            var name = $(this).attr('data-name');
            var price = $(this).attr('data-price');
            var size = "M";
            var qty = 1;
            var img = $(this).attr('data-src');
            
            var basketObj = {
                name: name,
                price: price,
                size: size,
                quantity: qty,
                image: img
            };
            
            if(sessionStorage.basket)
            {
             basket = JSON.parse(sessionStorage.getItem('basket'));
            }else{
             basket = [];
            }
            
            basket.push(basketObj);
            sessionStorage.setItem('basket', JSON.stringify(basket));
               
});
