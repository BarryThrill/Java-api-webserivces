function callAjax(input, isSeries) {
  var url = "http://localhost:1337/search/" + (isSeries ? '/tv/' : '') + input;

  $.ajax({
    type: 'GET',
    url: url,
    success: function(data) {
      if (data) {
        console.log('SUCCESS');
		$('#title_text').html("Title: "),
        $('#title').html(data.title);
		
		
		$('#release_text').html("Release: "),
        $('#release').html(data.release);
		
		
		$('#vote_text').html("Grade: "),
        $('#vote').html(data.vote);
		
		$('#overview_text').html("Description: "),
        $('#overview').html(data.overview);
		
		
        $('#poster').html('<img src="' + data.poster + '" width=250     height=450 />');
        $('#trailer').html("<iframe width='420' height='315' src='https://www.youtube.com/embed/" + data.trailer + "' frameborder='0' allowfullscreen>");

        $("#lblerror").addClass("hide");

      } else {
        $("#lblerror").text("No movies/series were found!");
        $("#lblerror").removeClass("hide");
      }
    },
    error: function(request, status, err) {
      console.log('ERROR');
    }
  });
}


$(document).ready(function() {

  $('#submitButton').on('click', function(e) {
    e.preventDefault();

    var input = $('#s').val();
    var isSeries = $('#SearchSeries').prop('checked');
    callAjax(input, isSeries);
  });

});