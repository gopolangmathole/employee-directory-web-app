// hiding and showing my model..
$('.table .delBtn').on('click',function(event){
 	 
  	event.preventDefault();
  	var href = $(this).attr('href');
  	
  	$('#myModel #delRef').attr('href',href);
  	$('#myModel').modal();
  	
  });
