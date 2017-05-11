// Render the PayPal button

paypal.Button.render({

    // Set your environment

    env: 'sandbox', // sandbox | production

     style: {
        size: 'small',
        color: 'blue',
        shape: 'rect',
        label: 'checkout'
    },

    // PayPal Client IDs - replace with your own
    // Create a PayPal app: https://developer.paypal.com/developer/applications/create

    client: {
        sandbox:    'AWLIZB_eHofkty8NsuHNykW1Y-D6WRL5Cz-PGwE8Qt8HSTNrifqRe8RMuNNqa7pfR5ZVRuKQeJTp95LT',
        production: '<insert production client id>'
    },

    // Set to 'Pay Now'

    commit: true,

    // Wait for the PayPal button to be clicked

    payment: function() {
        
        var basket = JSON.parse(sessionStorage.getItem('basket'));
        total = 0.00;
        
        for ( var key in basket ) {
                
                price = basket[key].price * basket[key].quantity;
                total += price;
        }
            
        // Make a client-side call to the REST api to create the payment
        
        
        return paypal.rest.payment.create(this.props.env, this.props.client, {
            transactions: [{amount: { total: total.toPrecision(3), currency: 'GBP'},
            }]
        });
    },

    // Wait for the payment to be authorized by the customer

    onAuthorize: function(data, actions) {

        // Execute the payment

        return actions.payment.execute().then(function() {
            document.querySelector('#paypal-button-container').innerText = 'Payment Complete!';
        });
    }

}, '#paypal-button-container');
