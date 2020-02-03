//get elements 
document.getElementById("pdf").addEventListener("click", showPopUp);
document.getElementById("html").addEventListener("click", showPopUp);

//create my alert function
function showPopUp(){
	Swal.fire(
	  'Report generated!',
	  'Your report was successfully generated!',
	  'success'
	)
}