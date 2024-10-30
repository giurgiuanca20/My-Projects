import '../../styles/ModalAdd.css';
import { updateDevice } from '../../services/device_service';
import { useState, useEffect } from 'react';

function ModalEditDevice({ closeModalEdit, updateDeviceInList, descriptionText, addressText, maxHourlyConsumptionText}) {

    const [address, setAddress] = useState('');
    const [maxHourlyConsumption, setMaxHourlyConsumption] = useState('');


    const [addressError, setAddressError] = useState('');

    useEffect(() => {
        setAddress(addressText);
        setMaxHourlyConsumption(maxHourlyConsumptionText);
    }, [descriptionText, addressText, maxHourlyConsumptionText]);


    const validateAddress = (addr) => {
        const addressRegex = /^(?=.*[a-zA-Z])(?=.*\d)/;
        return addressRegex.test(addr);
    };

    const handleValidation = () => {
        let valid = true;

        if (!validateAddress(address)) {
            setAddressError('Address must contain letters and at least one number.');
            valid = false;
        } else {
            setAddressError('');
        }
        return valid;
    };


    async function handleUpdateDevice() {
        if (!handleValidation()) {
            return;
        }
        const updatedDevice = {
                description: descriptionText,
                address,
                maxHourlyConsumption,
            };
        try {
            const result = await updateDevice(descriptionText,address,maxHourlyConsumption);
            if (result==="Device updated!") {
                console.log("Device edited successfully!");
                updateDeviceInList(updatedDevice);
                closeModalEdit(); 
            } else {
                alert(`Error: ${result}`);
            }
        } catch (error) {
            console.error("Failed to edit device:", error);
        }
    }

    return (
        <div className='modal'>
        <span id="close" onClick={closeModalEdit}>&times;</span>
        <div className="modal-content">
        <h2>Edit device</h2>
            <label>Description:</label>
            <input value={descriptionText} readOnly></input>
            <label>Address:</label>
            <input value={address} onChange={(e) => setAddress(e.target.value)}></input>
            {addressError && <div style={{ color: 'red' }}>{addressError}</div>}
            <label>Maximum hourly energy consumption (kW):</label>
            <input type="number" value={maxHourlyConsumption} onChange={(e) => setMaxHourlyConsumption(e.target.value)}></input>
            <button  type='submit' onClick={handleUpdateDevice}>Edit</button>
        </div>
    </div>
    );
}

export default ModalEditDevice;
