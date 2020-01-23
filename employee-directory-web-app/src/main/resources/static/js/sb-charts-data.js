//global variables
var dataList = [];
var x_axis = [];
var y_axis = [];
// api function
function restApiCall() {

	// fetching total error counts
	fetch("http://localhost:8080/api/v1/health/combined_count").then(
			function(response) {

				return response.json();
			}).then(function(data) {

		dataList = JSON.parse(JSON.stringify(data));

	});

	// fetching x-axis data
	fetch("http://localhost:8080/api/v1/health/graph_data/x-axis").then(
			function(response) {

				return response.json();
			}).then(function(data) {

		x_axis = JSON.parse(JSON.stringify(data));

	});

	// fetching y-axis data
	fetch("http://localhost:8080/api/v1/health/graph_data/y-axis").then(
			function(response) {

				return response.json();
			}).then(function(data) {

		y_axis = JSON.parse(JSON.stringify(data));

	});
}

function polarArea() {

	// call our api method
	restApiCall();

	// getting the canvas id and setting up the charts data
	const POLARAREA = document.getElementById("polarArea");
	let chart = new Chart(POLARAREA, {
		type : 'polarArea',
		data : {
			datasets : [ {
				label : 'Polar Area',
				data : [ dataList[0], dataList[1], dataList[2], dataList[3] ],
				backgroundColor : [

				'rgba(229, 45, 53, 0.6)', 'rgba(255, 195, 0, 0.6)',
						'rgba(170, 0, 0, 0.6)', 'rgba(67, 133, 245, 0.6)' ]
			} ],
			labels : [ '400', '404', '500', "other" ]

		},
		zoomEnabled : true,
		options : {
			events : [ '' ],
			onHover : null,
			onClick : null,
			hover : {
				mode : false
			},
			animation : {
				duration : 0
			}

		}

	});
}

// calling the method and creating a interval
polarArea();
window.setInterval(polarArea, 2000);

function Pie() {
	// getting the canvas id and setting up the charts data
	const POLARAREA = document.getElementById("pie");
	let chart = new Chart(POLARAREA, {
		type : 'pie',
		data : {
			datasets : [ {
				data : [ dataList[0], dataList[1], dataList[2], dataList[3] ],
				backgroundColor : [ 'rgba(229, 45, 53, 0.8)',
						'rgba(255, 195, 0, 0.8)', 'rgba(170, 0, 0, 0.8)',
						'rgba(67, 133, 245, 0.8)' ]
			} ],
			labels : [ '400', '404', '500', "other" ]
		},
		zoomEnabled : true,
		options : {
			events : [ '' ],
			onHover : null,
			onClick : null,
			hover : {
				mode : false
			},
			animation : {
				duration : 0
			}

		}

	});
}

// calling and starting animation
Pie();
window.setInterval(Pie, 2000);

// line graph
function lineGraph() {

	// Set new default font family and font color to mimic Bootstrap's
	// default styling
	Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	Chart.defaults.global.defaultFontColor = '#292b2c';

	// Area Chart Example
	var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {
		type : 'line',
		data : {
			labels : [ x_axis[8], x_axis[7], x_axis[6], x_axis[5], x_axis[4],
					x_axis[3], x_axis[2], x_axis[1], x_axis[0] ],
			datasets : [ {
				label : "Daily log",
				lineTension : 0.3,
				backgroundColor : "rgba(2,117,216,0.2)",
				borderColor : "rgba(2,117,216,1)",
				pointRadius : 5,
				pointBackgroundColor : "rgba(2,117,216,1)",
				pointBorderColor : "rgba(255,255,255,0.8)",
				pointHoverRadius : 5,
				pointHoverBackgroundColor : "rgba(2,117,216,1)",
				pointHitRadius : 50,
				pointBorderWidth : 2,
				data : [ y_axis[8], y_axis[7], y_axis[6], y_axis[5], y_axis[4],
						y_axis[3], y_axis[2], y_axis[1], y_axis[0] ],
			} ],
		},
		options : {
			scales : {
				xAxes : [ {
					time : {
						unit : 'date'
					},
					gridLines : {
						display : false
					},
					ticks : {
						maxTicksLimit : 9
					}
				} ],
				yAxes : [ {
					ticks : {
						min : 0,

						maxTicksLimit : 10
					},
					gridLines : {
						color : "rgba(0, 0, 0, .125)",
					}
				} ],
			},
			legend : {
				display : false
			},
			animation : {
				duration : 0
			},
			hover: {mode: null},

		}
	});
}

// calling and starting animation
lineGraph();
window.setInterval(lineGraph, 2000);
