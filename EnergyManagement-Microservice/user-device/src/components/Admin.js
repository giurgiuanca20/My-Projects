import '../styles/Admin.css';
import Device from './device-component/Device';
import User from './user-component/User';
import React, { useState, useEffect } from 'react';
import ModalAddUser from './user-component/ModalAddUser';
import ModalEditUser from './user-component/ModalEditUser';
import ModalAddDevice from './device-component/ModalAddDevice';
import ModalEditDevice from './device-component/ModalEditDevice';
import { getUsers, deleteUser } from './../services/user_service';
import { getDevices, deleteDevice } from './../services/device_service';
import { getCookie } from './../services/login_service';
import Mapping from './Mapping';

function Admin() {

    const [selectedDevices, setSelectedDevices] = useState([]);
    const [selectedUsers, setSelectedUsers] = useState([]);
    const [users, setUsers] = useState([]);
    const [devices, setDevices] = useState([]);
    const [isModalOpen, setModalOpen] = useState(false);
    const [isModalOpenEdit, setIsModalOpenEdit] = useState(false);
    const [selectedUserEdit, setSelectedUserEdit] = useState(null);
    const [selectedDeviceEdit, setSelectedDeviceEdit] = useState(null);
    const [selectedOption, setSelectedOption] = useState('users');

    useEffect(() => {
        const loadUsers = async () => {
            try {
                const fetchedUsers = await getUsers();
                setUsers(fetchedUsers);
            } catch (error) {
                console.error("Failed to fetch users:", error);
            }
        };

        loadUsers();
    }, []);

    useEffect(() => {
        const loadDevices = async () => {
            try {
                const fetchedDevices = await getDevices();
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

    const handleSelectUser = (id) => {
        if (selectedUsers.includes(id)) {
            setSelectedUsers(selectedUsers.filter(userId => userId !== id));
        } else {
            setSelectedUsers([...selectedUsers, id]);
        }
    };

    const deleteDev = async () => {
        for (const deviceId of selectedDevices) {
            const device = devices.find(d => d.id === deviceId);
            if (device) {
                try {
                    await deleteDevice(device.description, device.address, device.maxHourlyConsumption);
                    console.log(`Deleted device: ${device.description}`);
                } catch (error) {
                    console.error(`Failed to delete device: ${device.description}`, error);
                }
            }
        }

        const remainingDevices = devices.filter(device => !selectedDevices.includes(device.id));
        setDevices(remainingDevices);
        setSelectedDevices([]);
    };

    const deleteUs = async () => {
        for (const userId of selectedUsers) {
            const user = users.find(d => d.id === userId);
            if (user) {
                try {
                    await deleteUser(user.username);
                    console.log(`Deleted user: ${user.username}`);
                } catch (error) {
                    console.error(`Failed to delete user: ${user.username}`, error);
                }
            }
        }

        const remainingUsers = users.filter(user => !selectedUsers.includes(user.id));
        setUsers(remainingUsers);
        setSelectedUsers([]);
    };

    const openModal = () => {
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false);
    };

    const openModalEditUser = (user) => {
        setSelectedUserEdit(user);
        setIsModalOpenEdit(true);
    };

    const closeModalEditUser = () => {
        setIsModalOpenEdit(false);
        setSelectedUserEdit(null);
    };

    const openModalEditDevice = (device) => {
        setSelectedDeviceEdit(device);
        setIsModalOpenEdit(true);
    };

    const closeModalEditDevice = () => {
        setIsModalOpenEdit(false);
        setSelectedDeviceEdit(null);
    };

    const updateUs = () => {
        if (selectedUsers.length === 1) {
            const user = users.find(u => u.id === selectedUsers[0]);
            if (user) {
                openModalEditUser(user);
            }
        } else {
            alert("Please select a single user to edit.");
        }
    };

    const updateDev = () => {
        if (selectedDevices.length === 1) {
            const device = devices.find(u => u.id === selectedDevices[0]);
            if (device) {
                openModalEditDevice(device);
            }
        } else {
            alert("Please select a single device to edit.");
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

    const renderUsers = () => {
        return users.map((user, index) => (
            <User
                key={index}
                usernameText={`${user.username}`}
                nameText={`${user.name}`}
                emailText={`${user.email}`}
                passwordText={`${user.password}`}
                roleText={`${user.role}`}
                onSelect={handleSelectUser}
                isSelected={selectedUsers.includes(user.id)}
                id={user.id}
            />
        ));
    };

    const handleSwitchChange = (e) => {
        setSelectedOption(e.target.value);
    };

    const addUserToList = (newUser) => {
        setUsers((prevUsers) => [...prevUsers, newUser]);          
    };

    const addDeviceToList = (newDevice) => {
        setDevices((prevDevices) => [...prevDevices, newDevice]);        
    };

    const updateUserInList = (updatedUser) => {
        setUsers((prevUsers) =>            
            prevUsers.map((user) => (user.username === updatedUser.username ? updatedUser : user))
        );
    };
    
    const updateDeviceInList = (updatedDevice) => {
        setDevices((prevDevices) =>       
            prevDevices.map((device) => (device.description === updatedDevice.description ? updatedDevice : device))
        );
    };

    const render = () => {
        if (selectedOption === 'users') {
            return (
                <div>
                    <div className="empty-continer"></div>
                    {renderUsers()}
                    <div className='buttonsClass'>
                        <button onClick={openModal}>Add User</button>
                        {isModalOpen && (
                            <ModalAddUser closeModal={closeModal} addUserToList={addUserToList}/>   
                        )}
                        <button onClick={updateUs}>Edit User</button>
                        <button onClick={deleteUs}>Delete User</button>
                    </div>
                </div>
            );
        } else if (selectedOption === 'devices') {
            return (
                <div>
                    <div className="empty-continer"></div>
                    {renderDevices()}
                    <div className='buttonsClass'>
                        <button onClick={openModal}>Add Device</button>
                        {isModalOpen && (
                            <ModalAddDevice closeModal={closeModal} addDeviceToList={addDeviceToList} />
                        )}
                        <button onClick={updateDev}>Edit Device</button>
                        <button onClick={deleteDev}>Delete Device</button>
                    </div>
                </div>
            );
        } else if (selectedOption === 'mapping') {
            return (
                <div>
                    <div className="empty-continer"></div>
                    <Mapping/>
                </div>
            );
        }
        return null;
    };

    return (
        <div className='admin-container'>
            <h1>Hi, {getCookie().username}!</h1>
            <link href="https://cdn.jsdelivr.net/css-toggle-switch/latest/toggle-switch.css" rel="stylesheet" />

            <div className="switch">
                <input id="switch-y" name="tripple" type="radio" value="users" className="switch-input" checked={selectedOption === "users"} onChange={handleSwitchChange} />
                <label htmlFor="switch-y" className="switch-label switch-label-y">Users</label>
                <input id="switch-i" name="tripple" type="radio" value="devices" className="switch-input" checked={selectedOption === "devices"} onChange={handleSwitchChange} />
                <label htmlFor="switch-i" className="switch-label switch-label-i">Devices</label>
                <input id="switch-n" name="tripple" type="radio" value="mapping" className="switch-input" checked={selectedOption === "mapping"} onChange={handleSwitchChange} />
                <label htmlFor="switch-n" className="switch-label switch-label-n">Mapping</label>
                <span className="switch-selector"></span>
            </div>
            {render()}

            {isModalOpenEdit && selectedUserEdit && (
                <ModalEditUser
                    closeModalEdit={closeModalEditUser}
                    updateUserInList={updateUserInList}
                    usernameText={selectedUserEdit.username}
                    nameText={selectedUserEdit.name}
                    emailText={selectedUserEdit.email}
                    passwordText={selectedUserEdit.password}
                    roleText={selectedUserEdit.role}
                />
            )}
            {isModalOpenEdit && selectedDeviceEdit && (
                <ModalEditDevice
                    closeModalEdit={closeModalEditDevice}
                    updateDeviceInList={updateDeviceInList}
                    descriptionText={selectedDeviceEdit.description}
                    addressText={selectedDeviceEdit.address}
                    maxHourlyConsumptionText={selectedDeviceEdit.maxHourlyConsumption}
                />
            )}
        </div>
    );
}

export default Admin;
