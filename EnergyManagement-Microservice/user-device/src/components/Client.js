import '../styles/Client.css';
import DeviceClient from './DeviceClient';
import { getDevices } from './../services/client_service';
import React, { useState, useEffect } from 'react';
import { getCookie } from './../services/login_service';
function Client() {

    const [devices, setDevices] = useState([]);

    useEffect(() => {
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


    const renderDevices = () => {
        return devices.map((device, index) => (
            <DeviceClient
                key={index}
                descriptionText={`${device.description}`}
                addressText={`${device.address}`}
                maxHourlyConsumptionText={`${device.maxHourlyConsumption}`}
            />
        ));
    };

    return (
        <div className='client-container'>
            <h1>Energy Management </h1>
            <label id="greeting">Hi, {getCookie().username}!</label>
            <label>Your devices:</label>
            <div>
                <div className="empty-continer"></div>
                {renderDevices()}
            </div>
        </div>


    );
}

export default Client;