function appAndDBStatus() {

	// fetching actuator status
	fetch("http://localhost:8080/actuator/health")
			.then(function(resp) {
				return resp.json();
			})
			.then(
					function(data) {

						if (data.status == "UP") {
							var x = document.getElementById("actuator-status").innerText = data.status;
						} else {
							var x = document.getElementById("actuator-status").innerText = "Down";
						}
					});

	// fetching mysql status
	fetch("http://localhost:8080/actuator/health")
			.then(function(resp) {
				return resp.json();
			})
			.then(
					function(data) {

						if (data.details.db.status == "UP") {

							var x = document.getElementById("mysql-status").innerText = "Connected";
						} else {

							var x = document.getElementById("mysql-status").innerText = "Disconnected";
						}

					});

}

try {

	// calling the method and sleeping for 1 seconds 
	window.setInterval(appAndDBStatus, 3000);
	
	//let us not log the exception and leave it blank
}catch(err){
	
}
	