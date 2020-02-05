//get elements 
document.getElementById("pdf").addEventListener("click", showPopUp);
document.getElementById("html").addEventListener("click", showPopUp);

// create my alert function
function showPopUp() {

	Swal.fire({
		position : 'center',
		icon : 'success',
		title : 'Your report was successfully generated!',
		showConfirmButton : false,
		timer : 3200
	});
	
}