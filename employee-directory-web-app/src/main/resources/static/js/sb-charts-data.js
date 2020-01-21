
//a global variable
var dataList = [];

//api function
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
        
        //getting the canvas id and setting up the charts data
        const POLARAREA = document.getElementById("polarArea");
        let chart = new Chart(POLARAREA, {
            type: 'polarArea',
            data: {
                datasets: [{
                    label: 'Polar Area',
                    data: [dataList[0],dataList[1],dataList[2],dataList[3]],
                    backgroundColor: [
         
                          'rgba(229, 45, 53, 0.6)',
                          'rgba(255, 195, 0, 0.6)',
                          'rgba(170, 0, 0, 0.6)',
                          'rgba(67, 133, 245, 0.6)'
                    ]
                }],
                labels: ['400', '404', '500',"other"]
                	    
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

    // calling the method and creating a interval
    polarArea();
    window.setInterval(polarArea, 2000);
    
    
    function Pie() {
        //getting the canvas id and setting up the charts data
        const POLARAREA = document.getElementById("pie");
        let chart = new Chart(POLARAREA, {
            type: 'pie',
            data: {
                datasets: [{
                    data: [dataList[0],dataList[1],dataList[2],dataList[3]],
                    backgroundColor: [
                    	'rgba(229, 45, 53, 0.8)',
                        'rgba(255, 195, 0, 0.8)',
                        'rgba(170, 0, 0, 0.8)',
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
    
    
    
    
   