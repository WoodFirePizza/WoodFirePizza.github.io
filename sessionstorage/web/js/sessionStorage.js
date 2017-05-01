/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$( document ).ready(function() {
    console.log(5 + 6);
    $.PizzaShop = function( element ) {
		this.$element = $( element );
		this.init();
	};
        
    $.PizzaShop.prototype = {
		init: function() {  
                    //Properties
                    this.basketName = "myBasket"; // Cart name in the session storage
                    this.storage = sessionStorage; // shortcut to the sessionStorage object
                    this.$formAddToBasket = this.$element.find( "form.add-to-basket" ); // Forms for adding items to the cart
                    
                    // Method invocation
                    this.handleAddToBasketForm();
                    this.emptyBasket();
                    this.updateBasket();
                    this.displayBasket();
                    this.deleteProduct();
                    console.log("Hello1");
                    },
                    
                    
    
                // Adds items to the Basket
		handleAddToBasketForm: function() {
                    this.$formAddToBasket.each(function() {
                            var $form = $( this );
                            var $product = $form.parent();
                            var price = this._convertString( $product.data( "price" ) );
                            var name =  $product.data( "name" );
                            var image = $product.data("img");

                            $form.on( "submit", function() {
                                console.log("Hello");
                                    var qty = this._convertString( $form.find( ".qty" ).val() );
                                    var size = this._convertNumber( $form.find( ".size" ).val() );
                                    var subTotal = qty * price;
                                    var total = this._convertString( this.storage.getItem( this.total ) );
                                    var sTotal = total + subTotal;
                                    this.storage.setItem( this.total, sTotal );
                                    this._addToBasket({
                                            product: name,
                                            size: size,
                                            price: price,
                                            qty: qty,
                                            image: image
                                    }); 
                            });
                    });
                },
    
                _addToBasket: function( values ) {
                    
                    
                        var cart = this.storage.getItem( this.cartName );

                        var cartObject = this._toJSONObject( cart );
                        var cartCopy = cartObject;
                        var items = cartCopy.items;
                        items.push( values );

                        this.storage.setItem( this.cartName, this._toJSONString( cartCopy ) );
                        
                        for (var i = 0; i < this.storage.length; i++){
                            // do something with localStorage.getItem(localStorage.key(i));
                           window.alert(this.storage.getItem(this.storage.key(i)));
                           
                        }
                        
                },
    };
    
    $(function() {
		var pizzaShop = new $.PizzaShop( "#site" );
	});
    
    
});