import '../styles/Client.css';
import Device from './device-component/Device';
import ClientChat from './chat-component/ClientChat';
import { getDevices, findId, setApiUrl } from './../services/client_service';
import React, { useState, useEffect } from 'react';
import { getCookie } from './../services/login_service';
import { useNavigate } from 'react-router-dom';
import { startSim, setMaxConsumption } from '../services/monitoring_service';
import { setApi } from '../services/device_service';
import WebSocketNotifications from '../hooks/WebSocketNotifications';
import EnergyConsumptionChart from './EnergyConsumptionChart';

function Client() {

  const [devices, setDevices] = useState([]);
  const navigate = useNavigate();
  const [selectedDevices, setSelectedDevices] = useState([]);
  const [selectedDeviceId, setSelectedDeviceId] = useState(null);
  const [selectedSimulation, setSelectedSimulation] = useState(null);

  useEffect(() => {
    const user = getCookie();

    const verify = async () => {
      if (user.roles[0] === 'ADMIN')
        navigate('/');
    };

    verify();

    const loadDevices = async () => {
      try {
        const fetchedDevices = await getDevices(getCookie().id);
        setDevices(fetchedDevices);
      } catch (error) {
        console.error("Failed to fetch devices:", error);
      }
    };

    loadDevices();

  }, []);

  const handleSelectDevice = (id) => {
    if (selectedDevices.includes(id)) {
      setSelectedDevices(selectedDevices.filter(deviceId => deviceId !== id));
    } else {
      setSelectedDevices([...selectedDevices, id]);
    }
  };

  const findSelectDevice = async () => {
    for (const deviceId of selectedDevices) {
      const device = devices.find(d => d.id === deviceId);
      if (device) {
        try {
          const id = await findId(selectedSimulation,device.description, device.address, device.maxHourlyConsumption);
          console.log(`Find id: ${id}`);
          setMaxConsumption(device.maxHourlyConsumption);
          setSelectedDeviceId(id);

        } catch (error) {
          console.error(`Failed to select device: ${device.description}`, error);
        }
      }
    }
  };

  const renderDevices = () => {
    return devices.map((device, index) => (
      <Device
        key={index}
        descriptionText={`${device.description}`}
        addressText={`${device.address}`}
        maxHourlyConsumptionText={`${device.maxHourlyConsumption}`}
        onSelect={handleSelectDevice}
        isSelected={selectedDevices.includes(device.id)}
        id={device.id}
      />
    ));
  };



  const startSimulator = async () => {
    try {
      await startSim(selectedSimulation);
    } catch (error) {
      console.error("Starting simulator failed:", error);
      alert("An error occurred during simulation. Please try again.");
    }

  };

  const handleDeviceChange = (event) => {
    const selectedValue = event.target.value;
    setApiUrl(selectedValue);
    setApi(selectedValue);
    setSelectedSimulation(selectedValue);
  };


  return (
    <div className='client-container'>
      <h1>Energy Management </h1>

      <label>Your devices:</label>
      <div>
        <div className="empty-continer"></div>
        {renderDevices()}
      </div>
      <select id="deviceSelector" onChange={handleDeviceChange}>
        <option value="1">Simulare 1</option>
        <option value="2">Simulare 2</option>
      </select>
      <button onClick={findSelectDevice}>Select Device</button>
      <button onClick={startSimulator}>Start Simulator</button>

      <div className='simulator'>
        <EnergyConsumptionChart  deviceId={selectedDeviceId}/>
        
        {selectedDeviceId && <WebSocketNotifications selectedDevice={selectedDeviceId} />}
      </div>


        <ClientChat/>

    </div >


  );
}

export default Client;