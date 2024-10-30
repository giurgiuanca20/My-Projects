import './App.css';
import  {BrowserRouter as Router, Routes, Route,Navigate} from 'react-router-dom';
import React, { useState } from 'react';
import LoginPage from './components/LoginPage';
import Client from './components/Client';
import Admin from './components/Admin';
import { setCookie } from './services/login_service';


function App() {

  
  const [user, setUser] = useState(null);
  
  const handleLogin = (user) => {
    setUser(user);
    setCookie(JSON.stringify(user));
  };


  return (
<Router>
  <Routes>
    <Route path="/" element={<LoginPage onLogin={handleLogin}/>} />
    <Route path="/client" element={user && user.roles[0] === 'CLIENT' ? <Client /> : <Navigate to="/"/>} />
    <Route path="/admin" element={user && user.roles[0] === 'ADMIN' ? <Admin /> : <Navigate to="/" />} />

  </Routes>
</Router>
  );

}

export default App;


