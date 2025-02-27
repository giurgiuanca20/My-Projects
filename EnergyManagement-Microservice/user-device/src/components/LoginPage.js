import '../styles/LoginPage.css';
import React, { useState } from 'react';
import { login } from '../services/user_service';
import { useNavigate } from 'react-router-dom';

function LoginPage({ onLogin }){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const user = await login(username, password);
            if (user) {
                onLogin(user);
                    if (user.roles[0] === 'CLIENT') {
                        navigate('client'); 
                    } else if (user.roles[0] === 'ADMIN') {
                        navigate('admin'); 
                    }

            } else {
                alert('Invalid username or password'); 
            }
        } catch (error) {
            console.error("Login failed:", error);
            alert("An error occurred during login. Please try again.");
        }
    };

    return(
    <div className='login-container'>
        <h1 className='auth'>Authentication</h1>
        <label className='auth'>Username:</label>
        <input className='auth' placeholder="ex: John123" value={username} onChange={(e) => setUsername(e.target.value)}></input>
        <label className='auth'>Password:</label>
        <input className='auth' placeholder="ex: PassworD!56" value={password} onChange={(e) => setPassword(e.target.value)}></input>
        <button className='auth' type="submit" onClick={handleLogin}>Login</button>
    </div>

    );
}

export default LoginPage;