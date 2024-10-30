import '../../styles/ModalAdd.css';
import { postUser } from '../../services/user_service';
import { useState } from 'react';

function ModalAddUser({closeModal, addUserToList}) {
    const [username, setUsername] = useState('');
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('client');

    const [usernameError, setUsernameError] = useState('');
    const [nameError, setNameError] = useState('');
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const validateUsername = (username) => {
        const usernameRegex = /^[a-zA-Z0-9]+$/;
        return usernameRegex.test(username);
    };
    
    const validateName = (name) => {
        const nameRegex = /^[a-zA-Z0-9]+$/;
        return nameRegex.test(name);
    };

    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validatePassword = (password) => {
        const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
        return passwordRegex.test(password);
    };

    const handleValidation = () => {
        let valid = true;

        if (!validateUsername(username)) {
            setUsernameError('Username must not have empty space');
            valid = false;
        } else {
            setUsernameError('');
        }

        if (!validateName(name)) {
            setNameError('Name must not have empty space');
            valid = false;
        } else {
            setNameError('');
        }

        if (!validateEmail(email)) {
            setEmailError('Invalid email format.');
            valid = false;
        } else {
            setEmailError('');
        }

        if (!validatePassword(password)) {
            setPasswordError('Password must be strong.');
            valid = false;
        } else {
            setPasswordError('');
        }

        return valid;
    };

    async function handleCreateUser() {
        try {
            if (!handleValidation()) {
                return;
            }
            const newUser = {
                username,
                name,
                email,
                password,
                role: role.toUpperCase(),
            };
            const result = await postUser(username, name, email, password, role.toUpperCase());
    
            if (result==="User registered successfully!") {
                console.log("User created successfully!");
                addUserToList(newUser);            
                closeModal();
            } else {
                alert(`Error: ${result}`);
            }                                       
        } catch (error) {
            console.error("Failed to create user:", error);
        }
    }

    return (
        <div className='modal'>
            <span id="close" onClick={closeModal}>&times;</span>
            <div className="modal-content">
            <h2>Add user</h2>
                <label>Username:</label>
                <input placeholder="ex: John123" value={username} onChange={(e) => setUsername(e.target.value)}></input>
                {usernameError && <div style={{ color: 'red' }}>{usernameError}</div>}
                <label>Name:</label>
                <input placeholder="ex: John" value={name} onChange={(e) => setName(e.target.value)}></input>
                {nameError && <div style={{ color: 'red' }}>{nameError}</div>}
                <label>Email:</label>
                <input placeholder="ex: john@gmail.com" value={email} onChange={(e) => setEmail(e.target.value)}></input>
                {emailError && <div style={{ color: 'red' }}>{emailError}</div>}
                <label>Password:</label>
                <input placeholder="ex: PassworD!56" value={password} onChange={(e) => setPassword(e.target.value)} ></input> 
                {passwordError && <div style={{ color: 'red' }}>{passwordError}</div>}
                <label>Role:</label>
                <select value={role} onChange={(e) => setRole(e.target.value)}>
                    <option value="client">Client</option>
                    <option value="admin">Admin</option>
                </select>
                <button type='submit' onClick={handleCreateUser}>Add</button>
            </div>
        </div>

    );
}

export default ModalAddUser;