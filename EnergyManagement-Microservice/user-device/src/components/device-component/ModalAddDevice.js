import '../../styles/ModalAdd.css';
import { postDevice } from '../../services/device_service';
import { useState } from 'react';
function ModalAddDevice({closeModal, addDeviceToList}) {


    const [description, setDescription] = useState('');
    const [address, setAddress] = useState('');
    const [maxHourlyConsumption, setMaxHourlyConsumption] = useState('');

    const [descriptionError, setDescriptionError] = useState('');
    const [addressError, setAddressError] = useState('');


    const validateDescription = (desc) => {
        return desc.length <= 100; 
    };

    const validateAddress = (addr) => {
        const addressRegex = /^(?=.*[a-zA-Z])(?=.*\d)/;
        return addressRegex.test(addr);
    };

    const handleValidation = () => {
        let valid = true;

        if (!validateDescription(description)) {
            setDescriptionError('Description must be at most 150 characters.');
            valid = false;
        } else {
            setDescriptionError('');
        }

        if (!validateAddress(address)) {
            setAddressError('Address must contain letters and at least one number.');
            valid = false;
        } else {
            setAddressError('');
        }

        return valid;
    };


    async function handleCreateDevice() {
        if (!handleValidation()) {
            return; 
        }
        try {
            const newDevice = {
                description,
                address,
                maxHourlyConsumption,
            };
            const result = await postDevice(description, address, maxHourlyConsumption);
    
            if (result==="Device created!") {
                console.log("Device created successfully!");
                addDeviceToList(newDevice);
                closeModal();
            } else {
                alert(`Error: ${result}`);
            }                                        
        } catch (error) {
            console.error("Failed to create device:", error);
        }
    }


    return (
        <div className='modal'>
            <span id="close" onClick={closeModal}>&times;</span>
            <div className="modal-content">
            <h2>Add device</h2>
                <label>Description:</label>
                <input placeholder="ex: Solar Panel" value={description} onChange={(e) => setDescription(e.target.value)}></input>
                {descriptionError && <div style={{ color: 'red' }}>{descriptionError}</div>}
                <label>Address:</label>
                <input placeholder="ex: str Green Lane nr 45" value={address} onChange={(e) => setAddress(e.target.value)}></input>
                {addressError && <div style={{ color: 'red' }}>{addressError}</div>}
                <label>Maximum hourly energy consumption (kW):</label>
                <input type="number" placeholder="ex: 50" value={maxHourlyConsumption} onChange={(e) => setMaxHourlyConsumption(e.target.value)}></input>
                <button  type='submit' onClick={handleCreateDevice}>Add</button>
            </div>
        </div>

    );

}

export default ModalAddDevice;