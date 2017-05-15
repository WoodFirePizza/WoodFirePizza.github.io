/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//(function ($) {
//    
//  $('.spinner .btn:first-of-type').on('click', function() {
//    $('.spinner input').val( parseInt($('.spinner input').val(), 10) + 1);
//  });
//  $('.spinner .btn:last-of-type').on('click', function() {
//    $('.spinner input').val( parseInt($('.spinner input').val(), 10) - 1);
//  });
//})(jQuery);


$('body').on('click', 'a.up', function() {
    var key = $(this).attr('data-key');
    $('.spinner'+ key +' input').val( parseInt($('.spinner'+ key +' input').val(), 10) + 1);
});

$('body').on('click', 'a.down', function() {
    var key = $(this).attr('data-key');
    $('.spinner'+ key +' input').val( parseInt($('.spinner'+ key +' input').val(), 10) - 1);
});