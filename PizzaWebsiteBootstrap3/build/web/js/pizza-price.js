/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function pizzaPriceUp(){
    var i = 0;
    while ( i++ < 7 ){
        var size = document.getElementById('pizzaSize' + i.toString()).value;
        var quantity = document.getElementById('pizzaQuantity' + i.toString()).value;

        switch(size) {
            case "01":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (7.50 * (parseInt(quantity) + 1)); 
               break;
            case "02":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (10.00 * (parseInt(quantity) + 1)); 
               break;
            case "03":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (12.50 * (parseInt(quantity) + 1)); 
               break;
        }
    }

}

function pizzaPriceDown(){

    var i = 0;
    while ( i++ < 7 ){
        var size = document.getElementById('pizzaSize' + i).value;
        var quantity = document.getElementById('pizzaQuantity' + i).value;

        if (quantity != "1"){
            switch(size) {
                case "01":
                   document.getElementById('pizzaPrice' + i).innerHTML = "£" + (7.50 * (parseInt(quantity) - 1)); 
                   break;
                case "02":
                   document.getElementById('pizzaPrice' + i).innerHTML = "£" + (10.00 * (parseInt(quantity) - 1)); 
                   break;
                case "03":
                   document.getElementById('pizzaPrice' + i).innerHTML = "£" + (12.50 * (parseInt(quantity) - 1)); 
                   break;
            }
        }
   }

}

function pizzaPriceSize(){
    var i = 0;
    while ( i++ < 7 ){
        var size = document.getElementById('pizzaSize' + i).value;
        var quantity = document.getElementById('pizzaQuantity' + i).value;

        switch(size) {
            case "01":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (7.50 * (parseInt(quantity))); 
               break;
            case "02":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (10.00 * (parseInt(quantity))); 
               break;
            case "03":
               document.getElementById('pizzaPrice' + i).innerHTML = "£" + (12.50 * (parseInt(quantity))); 
               break;
        }
    }
}



