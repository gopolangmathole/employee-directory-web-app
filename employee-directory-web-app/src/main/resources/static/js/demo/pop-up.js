//for all list employees with images 
$('.carde-footer .delBtn').on('click',function(event){
	 
  	event.preventDefault();
  	var href = $(this).attr('href');
  	
  	$('#myModel #delRef').attr('href',href);
  	$('#myModel').modal();
  	
  });