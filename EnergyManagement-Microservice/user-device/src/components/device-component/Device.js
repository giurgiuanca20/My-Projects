import '../../styles/DisplayUserDevice.css';
import React, { useState } from 'react';

function Device({ descriptionText, addressText, maxHourlyConsumptionText, onSelect, id, isSelected }){

    const toggleBorder = () => { 
        onSelect(id); 
    };

    return(
    <div className={`container ${isSelected ? 'selected' : ''}`}
            onClick={toggleBorder}>
        <label>Description: {descriptionText}</label>
        <label>Address: {addressText}</label>
        <label>Maximum hourly energy consumption: {maxHourlyConsumptionText}</label>
    </div>

    );
}

export default Device;