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

				'rgba(255, 166, 0, 0.6)', 'rgba(47, 75, 124, 0.6)',
						'rgba(249, 93, 106, 0.6)', 'rgba(67, 133, 245, 0.6)' ]
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
			plugins : {
				labels : {
					render : 'percentage',
					// font size, default is defaultFontSize
					fontSize : 14,

					// font style, default is defaultFontStyle
					fontStyle : 'normal',

					position: 'outside',
					
					// font family, default is defaultFontFamily
					fontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
					// font color, can be color array for each data or
					// function for dynamic color, default is
					// defaultFontColor
					textMargin: 6
				
				}
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
	let chart = new Chart(
			POLARAREA,
			{
				type : 'pie',
				data : {
					datasets : [ {
						data : [ dataList[0], dataList[1], dataList[2],
								dataList[3] ],
						backgroundColor : [ 'rgba(255, 166, 0, 0.6)',
								'rgba(47, 75, 124, 0.6)',
								'rgba(249, 93, 106, 0.6)',
								'rgba(67, 133, 245, 0.6)' ]
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
					plugins : {
						labels : {
							render : 'percentage',
							// font size, default is defaultFontSize
							fontSize : 14,

							// font style, default is defaultFontStyle
							fontStyle : 'normal',

							position: 'outside',
							
							// font family, default is defaultFontFamily
							fontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
							// font color, can be color array for each data or
							// function for dynamic color, default is
							// defaultFontColor
							textMargin: 6
						}
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

/* Creating bar chart */
function BarGraph() {

	const LINEGRAPH = document.getElementById("barGraph");
	let chart = new Chart(
			LINEGRAPH,
			{
				type : 'bar',
				data : {
					datasets : [ {
						label : "Total daily errors",

						data : [ y_axis[8], y_axis[7], y_axis[6], y_axis[5],
								y_axis[4], y_axis[3], y_axis[2], y_axis[1],
								y_axis[0] ],

						backgroundColor : [ 'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)',
								'rgba(249, 93, 106, 1)' ],
					} ],

					labels : [ x_axis[8], x_axis[7], x_axis[6], x_axis[5],
							x_axis[4], x_axis[3], x_axis[2], x_axis[1],
							x_axis[0] ],
					render : null,
				},
				options : {
					title : {
						display : true,
						text : 'Daily Report',
						fontSize : 18

					},
					plugins : {
						labels : {
							render : 'value',
							// font size, default is defaultFontSize
							fontSize : 13,

							// font style, default is defaultFontStyle
							fontStyle : 'normal',

							// font family, default is defaultFontFamily
							fontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

						}
					},
					events : [ '' ],
					onHover : null,
					onClick : null,
					legend : {
						display : true
					},
					hover : {
						mode : false,

					},
					animation : {
						duration : 0,

					},
					scales : {
						yAxes : [ {

							ticks : {
								beginAtZero : true
							}
						} ],

					}
				}
			});
}

// calling and starting animation
BarGraph();
window.setInterval(BarGraph, 2000);