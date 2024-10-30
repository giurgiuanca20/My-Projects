import '../../styles/ModalAdd.css';
import { updateUser } from '../../services/user_service';
import { useState, useEffect } from 'react';

function ModalEditUser({ closeModalEdit, updateUserInList, usernameText, nameText, emailText, passwordText, roleText }) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');

    const [nameError, setNameError] = useState('');
    const [emailError, setEmailError] = useState('');

    useEffect(() => {
        setName(nameText);
        setEmail(emailText);
        setRole(roleText);
    }, [ nameText, emailText, roleText]);


    const validateName = (name) => {
        const nameRegex = /^[a-zA-Z0-9]+$/; 
        return nameRegex.test(name);
    };

    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 
        return emailRegex.test(email);
    };


    const handleValidation = () => {
        let valid = true;

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

        return valid;
    };


    async function handleUpdateUser() {
        if (!handleValidation()) {
            return;
        }
        const updatedUser = {
            username: usernameText,
            name,
            email,
            password:passwordText,
            role,
        };
        try {
            const result = await updateUser(usernameText, name, email, passwordText, role.toUpperCase());
            if (result==="User updated!") {
                console.log("User edited successfully!");
                updateUserInList(updatedUser);
                closeModalEdit();
            } else {
                alert(`Error: ${result}`);
            }
        } catch (error) {
            console.error("Failed to edit user:", error);
        }
    }

    return (
        <div className='modal'>
            <span id="close" onClick={closeModalEdit}>&times;</span>
            <div className="modal-content">
                <h2>Edit User</h2>
                <label>Username:</label>
                <input value={usernameText} readOnly />
                <label>Name:</label>
                <input value={name} onChange={(e) => setName(e.target.value)} />
                {nameError && <div style={{ color: 'red' }}>{nameError}</div>}
                <label>Email:</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} />
                {emailError && <div style={{ color: 'red' }}>{emailError}</div>}
                <label>Password:</label>
                <input value={passwordText} readOnly />
                <label>Role:</label>
                <select value={role} onChange={(e) => setRole(e.target.value)}>
                    <option value="client">Client</option>
                    <option value="admin">Admin</option>
                </select>
                <button type='submit' onClick={handleUpdateUser}>Edit</button>
            </div>
        </div>
    );
}

export default ModalEditUser;
