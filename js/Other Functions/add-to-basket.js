$('body').on('click', 'a.basket', function() {
    
            var key = $(this).attr('data-key');
            var menuID = $(this).attr('data-menuID');
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
                image: img,
                menuID: menuID
            };
            
            var notInBasket = false;
            
            if(sessionStorage.basket)
            {
                basket = JSON.parse(sessionStorage.getItem('basket'));
                
                
                
                for(var i in basket)
                {
                    if(basket[i].name === basketObj.name && basket[i].size === basketObj.size)
                    {
                        var total = parseInt(basket[i].quantity) + parseInt(basketObj.quantity);
                        basket[i].quantity = total;
                    }
                    else
                    {
                        notInBasket = true;
                    }
                }
                
                if(basket.length === 0)
                {
                    basket = [];
                    notInBasket = true;
                }
            }
            else
            {
                basket = [];
                notInBasket = true;
            }
            
            
            
            if(notInBasket === true)
            {
                basket.push(basketObj);
            }
            
            sessionStorage.setItem('basket', JSON.stringify(basket));
               
});

$('body').on('click', 'a.modalBasket', function() {
    
            var key = $(this).attr('data-key');
            var menuID = $(this).attr('data-menuID');
            var name = $(this).attr('data-name');
            var price = $(this).attr('data-price');
            var size = document.getElementById('modalPizzaSize').value;
            var qty = document.getElementById('quantity' + key).value;
            var img = $(this).attr('data-src');
            
            
            var basketObj = {
                name: name,
                price: price,
                size: size,
                quantity: qty,
                image: img,
                menuID: menuID
            };
            
            var notInBasket = false;
            
            if(sessionStorage.basket)
            {
                basket = JSON.parse(sessionStorage.getItem('basket'));
                
                
                
                for(var i in basket)
                {
                    if(basket[i].name === basketObj.name && basket[i].size === basketObj.size)
                    {
                        var total = parseInt(basket[i].quantity) + parseInt(basketObj.quantity);
                        basket[i].quantity = total;
                    }
                    else
                    {
                        notInBasket = true;
                    }
                }
                
                if(basket.length === 0)
                {
                    basket = [];
                    notInBasket = true;
                }
            }
            else
            {
                basket = [];
                notInBasket = true;
            }
            
            
            
            if(notInBasket === true)
            {
                basket.push(basketObj);
            }
            
            sessionStorage.setItem('basket', JSON.stringify(basket));
               
});

$('body').on('click', 'a.offer', function() {

            var name = $(this).attr('data-name');
            var price = $(this).attr('data-price');
            var size = "S";
            var qty = 1;
            var img = $(this).attr('data-src');
            
            var basketObj = {
                name: name,
                price: price,
                size: size,
                quantity: qty,
                image: img
            };

            var notInBasket = false;
            
            if(sessionStorage.basket)
            {
                basket = JSON.parse(sessionStorage.getItem('basket'));
                
                
                
                for(var i in basket)
                {
                    if(basket[i].name === basketObj.name && basket[i].size === basketObj.size)
                    {
                        basket[i].quantity = basket[i].quantity + basketObj.quantity;
                    }
                    else
                    {
                        notInBasket = true;
                    }
                }
                
                if(basket.length === 0)
                {
                    basket = [];
                    notInBasket = true;
                }
            }
            else
            {
                basket = [];
                notInBasket = true;
            }
            
            if(notInBasket === true)
            {
                basket.push(basketObj);
            }
            
            sessionStorage.setItem('basket', JSON.stringify(basket));
               
});


