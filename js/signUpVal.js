$('body').on('focusout', 'input.usernameVal', function() 
{
    //alert("Test");
    
    var regex = /^[A-Za-z0-9_-]{3,16}$/;
    
    if(regex.test($(this).val()))
    {
        $("div.usernameVal").removeClass("has-error");
        $("span.usernameVal").removeClass("glyphicon-remove");
    }

});

$('body').on('focusout', 'input.firstnameVal', function() 
{
    var regex = /^[A-Za-z]{3,16}$/;
    
    if(regex.test($(this).val()))
    {
        $("div.firstnameVal").removeClass("has-error");
        $("span.firstnameVal").removeClass("glyphicon-remove");
    }

});

$('body').on('focusout', 'input.lastnameVal', function() 
{
    var regex = /^[A-Za-z]{3,16}$/;
    
    if(regex.test($(this).val()))
    {
        $("div.lastnameVal").removeClass("has-error");
        $("span.lastnameVal").removeClass("glyphicon-remove");
    }
});

$('body').on('focusout', 'input.emailVal', function() 
{
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    if(regex.test($(this).val()))
    {
        $("div.emailVal").removeClass("has-error");
        $("span.emailVal").removeClass("glyphicon-remove");
    }

});

$('body').on('focusout', 'input.contactNumber', function() 
{
    var regex = /^[0-9]{9,12}$/;
    
    $("div.contactNumber").removeClass("has-error");
    $("span.contactNumber").removeClass("glyphicon-remove");

});

$('body').on('focusout', 'input.password', function() 
{
    alert("Test");
    
    $("div.password").removeClass("has-error");
    $("span.password").removeClass("glyphicon-remove");

});

$('body').on('focusout', 'input.confirmPasswordVal', function() 
{
    alert("Test");
    
    $("div.confirmPasswordVal").removeClass("has-error");
    $("span.confirmPasswordVal").removeClass("glyphicon-remove");

});

$('body').on('focusout', 'input.addressLine1', function() 
{
    var regex = /^[A-Za-z0-9_-]{1,16}$/;
    
    if(regex.test($(this).val()))
    {
        $("div.addressLine1").removeClass("has-error");
        $("span.addressLine1").removeClass("glyphicon-remove");
    }
    

});

$('body').on('focusout', 'input.postcode', function() 
{
    var regex = /^[A-Z]{1,2}[0-9]{1,2} ?[0-9][A-Z]{2}$/i;
    
    if(regex.test($(this).val()))
    {
        $("div.postcode").removeClass("has-error");
        $("span.postcode").removeClass("glyphicon-remove");
    }
});

