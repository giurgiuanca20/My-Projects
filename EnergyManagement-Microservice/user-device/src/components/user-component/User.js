import '../../styles/DisplayUserDevice.css';
import React, { useState } from 'react';

function User({ usernameText, nameText, emailText, passwordText, roleText, onSelect, id, isSelected }){
      
    const toggleBorder = () => { 
        onSelect(id); 
    };

    return(
    <div id='user' className={`container ${isSelected ? 'selected' : ''}`} 
    onClick={toggleBorder}>
        <label>Username: {usernameText}</label>
        <label>Name: {nameText}</label>
        <label>Email: {emailText}</label>
        <label>Password: {passwordText}</label>
        <label>Role: {roleText}</label>
    </div>
    );
}

export default User;