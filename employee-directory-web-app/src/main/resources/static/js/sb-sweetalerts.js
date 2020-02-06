//get elements 
document.getElementById("pdf").addEventListener("click", showPopUp);
document.getElementById("html").addEventListener("click", showPopUp);

// create my alert function
function showPopUp() {	
	let timerInterval
	Swal.fire({
	  title: 'Generating Report',
	  html: 'Please wait as we prepare your file!.',
	  timer: 90000,
	  timerProgressBar: true,
	  onBeforeOpen: () => {
	    Swal.showLoading()
	    timerInterval = setInterval(() => {
	      const content = Swal.getContent()
	      if (content) {
	        const b = content.querySelector('b')
	        if (b) {
	          b.textContent = Swal.getTimerLeft()
	        }
	      }
	    }, 100)
	  },
	  onClose: () => {
	    clearInterval(timerInterval)
	  }
	}).then((result) => {
	  /* Read more about handling dismissals below */
	  if (result.dismiss === Swal.DismissReason.timer) {
	    console.log('I was closed by the timer')
	  }
	})
	
}