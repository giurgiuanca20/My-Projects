import '../styles/Mapping.css';
import { useState, useEffect } from 'react';
import { getUserId } from './../services/user_service';
import { createMapping,deleteMapping } from './../services/mapping_service';
function Mapping() {

    const [username, setUsername] = useState('');
    const [description, setDescription] = useState('');

    async function handleMapping() {
        try {
            const userId = await getUserId(username);
            if (userId!==null) {
                console.log("User exists!");
            } else {
                alert('Error: user not found');
            }

            const response = await createMapping(userId,description);
            if (response==="Device mapped to user!") {
                console.log("Succesful mapping!");
            } else {
                alert(`Error: ${response}`);
            }

        } catch (error) {
            console.error("Failed to map:", error);
        }
    }
    
    async function handleDeleteMapping() {
        try {
            const userId = await getUserId(username);
            if (userId!==null) {
                console.log("User exists!");
            } else {
                alert('Error: user not found');
            }

            const response = await deleteMapping(userId,description);
            if (response==="Mapping deleted!") {
                console.log("Deleted mapping!");
            } else {
                alert(`Error: ${response}`);
            }

        } catch (error) {
            console.error("Failed to delete map:", error);
        }
    }

    return (
        <div className='mapping-component'>
                <h2>Mapping</h2>
                <label>Select User:</label>
                <input value={username} onChange={(e) => setUsername(e.target.value)} />
                <label>Select Device:</label>
                <input value={description} onChange={(e) => setDescription(e.target.value)} />

                <button type='submit' onClick={handleMapping}>Create</button>
                <button type='submit' onClick={handleDeleteMapping}>Delete</button>
        </div>
    );
}

export default Mapping;
