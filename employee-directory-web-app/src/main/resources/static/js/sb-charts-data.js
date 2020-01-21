
var dataList = [];

function restApiCall(){
	
	// fetching actuator status
	fetch("http://localhost:8080/api/v1/health/combined_count")
			.then(function(response) {

				return response.json();
			})
			.then(
					function(data) {
						
						dataList = JSON.parse(JSON.stringify(data));

					});
}

function polarArea() {
 
		//call our api method
        restApiCall();
        
        const POLARAREA = document.getElementById("polarArea");
        let chart = new Chart(POLARAREA, {
            type: 'polarArea',
            data: {
                datasets: [{
                    label: 'Polar Area',
                    data: [dataList[0],dataList[1],dataList[2],dataList[3]],
                    backgroundColor: [
                    	  'rgba(255, 195, 0, 0.2)',
                          'rgba(229, 45, 53, 0.2)',
                          'rgba(127, 15, 63, 0.2)',
                          'rgba(67, 133, 245, 0.2)'
                    ]
                }],
                labels: ['400', '404', '500',"other"]
            },
            zoomEnabled: true,
            options: {
                events: [''],
                onHover: null,
                onClick: null,
                hover: {
                    mode: false
                },
                animation: {
                    duration: 0
                }

            }

        });
    }

    // calling and starting animation
    polarArea();
    window.setInterval(polarArea, 2000);
    
    function Pie() {

        const POLARAREA = document.getElementById("pie");
        let chart = new Chart(POLARAREA, {
            type: 'pie',
            data: {
                datasets: [{
                    data: [dataList[0],dataList[1],dataList[2],dataList[3]],
                    backgroundColor: [
                        'rgba(255, 195, 0, 0.8)',
                        'rgba(229, 45, 53, 0.8)',
                        'rgba(127, 15, 63, 0.8)',
                        'rgba(67, 133, 245, 0.8)'
                    ]
                }],
                labels: ['400', '404', '500',"other"]
            },
            zoomEnabled: true,
            options: {
                events: [''],
                onHover: null,
                onClick: null,
                hover: {
                    mode: false
                },
                animation: {
                    duration: 0
                }

            }

        });
    }

    // calling and starting animation
    Pie();
    window.setInterval(Pie, 2000);
    
   