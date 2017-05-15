(function() {
  var removeSuccess;

  removeSuccess = function() {
    return $('.button').removeClass('success');
  };

  $('body').on('click', 'a.button', function() {
      $(this).addClass('success');
      return setTimeout(removeSuccess, 1500);
    });

}).call(this);