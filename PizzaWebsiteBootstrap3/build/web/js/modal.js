/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
		$('.pop').on('click', function() {
                    var title = $(".modal-title");
                    var alt = $(".modal-alt");
                    
			$('.imagepreview').attr('src', $(this).find('img').attr('src'));
                        title.html($(this).find("img").attr("title"));
                        alt.html($(this).find("img").attr("alt"));
			$('#pizzaModal').modal('show');   
                        
		});		
});
