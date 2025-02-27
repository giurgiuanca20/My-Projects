import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import React, { useState } from 'react';
import LoginPage from './components/LoginPage';
import Client from './components/Client';
import Admin from './components/Admin';
import { setCookie, getCookie } from './services/login_service';

function App() {
 
  const [user, setUser] = useState(null);

  const handleLogin = (user) => {
    setCookie(JSON.stringify(user));
    setUser(user);

  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage onLogin={handleLogin} />} />
        <Route path="/client" element={<Client />} />
        <Route path="/admin" element={<Admin /> } />

      </Routes>
    </Router>
  );
}

export default App;
