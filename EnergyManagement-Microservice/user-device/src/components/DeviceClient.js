import '../styles/DisplayUserDevice.css';

function DeviceClient({descriptionText, addressText, maxHourlyConsumptionText}){

    return(
    <div className='container'>
        <label>Description: {descriptionText}</label>
        <label>Address: {addressText}</label>
        <label>Maximum hourly energy consumption: {maxHourlyConsumptionText}</label>
    </div>

    );
}

export default DeviceClient;