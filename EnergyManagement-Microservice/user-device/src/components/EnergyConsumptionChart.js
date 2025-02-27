import '../styles/SimulatorComponet.css';
import React, { useState, useEffect } from 'react';
import { chart } from '../services/monitoring_service';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
} from 'chart.js';


ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);

const EnergyConsumptionChart = ({deviceId}) => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [chartData, setChartData] = useState({ labels: [], datasets: [] });


    useEffect(() => {

        const fetchData = async () => {
          const formattedDate = selectedDate.toISOString().split('T')[0];
    
          const data = await chart(deviceId,formattedDate);
          console.log("Response data:", data);
    
          const labels = data.map(item => new Date(item.timestamp).getHours() + ':00');
          const values = data.map(item => item.hourlyConsumption);
    
          setChartData({
            labels,
            datasets: [
              {
                label: 'Consum [kWh]',
                data: values,
                backgroundColor: 'rgba(255,255,255,1)',
                borderColor: 'rgba(255,255,255,1)',
                fill: false,
                pointRadius: 0,
                borderWidth: 2
              }
            ]
          });
    
        };
    
        fetchData();
    
        }, [selectedDate,deviceId]);

    return (
        <div className='simulator-componet'>
            <h2>Consum Zilnic de Energie</h2>
            <DatePicker selected={selectedDate} onChange={(date) => setSelectedDate(date)} />

            <Line
                data={chartData}
                options={{
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Oră',
                                color: 'white',
                                font: {
                                    size: 18,
                                    family: 'Arial, sans-serif',
                                    weight: 'bold'
                                }
                            },
                            ticks: {
                                color: 'white',
                                font: {
                                    size: 20,
                                    family: 'Arial, sans-serif',
                                    weight: 'normal'
                                },
                                padding: 20
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Consum [kWh]',
                                color: 'white',
                                font: {
                                    size: 14,
                                    family: 'Arial, sans-serif',
                                    weight: 'bold'
                                }
                            },
                            ticks: {
                                color: 'white',
                                font: {
                                    size: 20,
                                    family: 'Arial, sans-serif',
                                    weight: 'normal'
                                },
                                padding: 20
                            },
                            beginAtZero: true
                        }
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                        tooltip: {
                            callbacks: {
                                label: function (tooltipItem) {
                                    return tooltipItem.raw + ' kWh';
                                }
                            },
                            backgroundColor: 'rgba(0, 0, 0, 0.7)',
                            titleColor: 'white',
                            bodyColor: 'white',
                            bodyFont: {
                                size: 14,
                                family: 'Arial, sans-serif'
                            },
                            titleFont: {
                                size: 16,
                                family: 'Arial, sans-serif'
                            },
                            padding: 10
                        }
                    },
                    title: {
                        display: true,
                        text: 'Consum Zilnic de Energie',
                        color: 'white',
                        font: {
                            size: 22,
                            family: 'Arial, sans-serif',
                            weight: 'bold'
                        },
                        padding: 20
                    }
                }}
                className='chart'
            />

        </div>
    );
};

export default EnergyConsumptionChart;
