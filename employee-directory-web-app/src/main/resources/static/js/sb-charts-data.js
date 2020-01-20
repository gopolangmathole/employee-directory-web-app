 function polarArea() {
        var random1 = Math.floor((Math.random() * 100) + 1);
        var random2 = Math.floor((Math.random() * 100) + 1);
        var random3 = Math.floor((Math.random() * 100) + 1);
        var random4 = Math.floor((Math.random() * 100) + 1);

        const POLARAREA = document.getElementById("polarArea");
        let chart = new Chart(POLARAREA, {
            type: 'polarArea',
            data: {
                datasets: [{
                    label: 'Failing Transactions',
                    data: [random1, random2, random3, random4, random1, random3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.4)',
                        'rgba(54, 162, 235, 0.4)',
                        'rgba(255, 206, 86, 0.4)',
                        'rgba(75, 192, 192, 0.4)',
                        'rgba(153, 102, 255, 0.4)',
                        'rgba(255, 159, 64, 0.4)'
                    ]
                }],
                labels: ['Uganda', 'Mozambique', 'Lesotho', 'Botswana', 'Malawi', 'Ghana']
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
                    duration: 900
                }

            }

        });
    }

    //calling and starting animation
    polarArea();
    window.setInterval(polarArea, 2000);
    
    function Pie() {
        var random1 = Math.floor((Math.random() * 100) + 1);
        var random2 = Math.floor((Math.random() * 100) + 1);
        var random3 = Math.floor((Math.random() * 100) + 1);
        var random4 = Math.floor((Math.random() * 100) + 1);

        const POLARAREA = document.getElementById("pie");
        let chart = new Chart(POLARAREA, {
            type: 'pie',
            data: {
                datasets: [{
                    label: 'Failing Transactions',
                    data: [random1, random2, random3, random4, random1, random3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.4)',
                        'rgba(54, 162, 235, 0.4)',
                        'rgba(255, 206, 86, 0.4)',
                        'rgba(75, 192, 192, 0.4)',
                        'rgba(153, 102, 255, 0.4)',
                        'rgba(255, 159, 64, 0.4)'
                    ]
                }],
                labels: ['Uganda', 'Mozambique', 'Lesotho', 'Botswana', 'Malawi', 'Ghana']
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
                    duration: 900
                }

            }

        });
    }

    //calling and starting animation
    Pie();
    window.setInterval(Pie, 2000);
    
   