
// using try and catch
try {

	// calling the method on-load
	applicationHelath();

	// calling the method and sleeping for 1 seconds
	window.setInterval(applicationHelath, 3000);

	// logging the error onto the console
} catch (err) {

	console.log(err);
}

function applicationHelath() {

	// fetching actuator status
	fetch("http://localhost:8080/actuator/health")
			.then(function(response) {

				return response.json();
			})
			.then(
					function(data) {
						// actuator status
						document.getElementById("actuator-status").innerText = data.status;

						// database status
						document.getElementById("mysql-status").innerText = data.details.db.status;

					});

	// fetch error counts
	fetch("http://localhost:8080/api/v1/health/error_count").then(
			function(response) {

				return response.json();
			}).then(function(data) {

		if (data >= 0) {
			// actuator status
			document.getElementById("total-errors").innerText = data;

		} else {

			document.getElementById("total-errors").innerText = "Disconnected";
		}

	});

	// getting free disk space
	fetch("http://localhost:8080/actuator/health").then(function(response) {

		return response.json();
	}).then(function(data) {

		bytes = data.details.diskSpace.details.free;
		document.getElementById("disk-space").innerText = bytesToSize(bytes);

	});

}

// convert bytes to respective size
function bytesToSize(bytes) {
	var sizes = [ 'Bytes', 'KB', 'MB', 'GB', 'TB' ];
	if (bytes == 0)
		return '0 Byte';
	var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	
	return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
}