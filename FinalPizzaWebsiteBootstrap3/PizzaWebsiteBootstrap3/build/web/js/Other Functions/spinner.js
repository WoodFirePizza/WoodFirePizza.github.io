
$('body').on('click', 'a.up', function() 
{
    
    var key = $(this).attr('data-key');
    
    $('.spinner'+ key +' input').val( parseInt($('.spinner'+ key +' input').val(), 10) + 1);
    

     $('.priceUpdate' + key).trigger('click');
    
});

$('body').on('click', 'a.down', function() 
{
    
    var key = $(this).attr('data-key');
    
    var current = $('.spinner'+ key +' input').val();
    
    
    if(current > 1)
    {
        $('.spinner'+ key +' input').val( parseInt($('.spinner'+ key +' input').val(), 10) - 1);
    }
    
    $('.priceUpdate' + key).trigger('click');
    
});
