import React, { useEffect, useState } from 'react';
import {
    Box, Button, Paper, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, TextField, Typography
} from '@mui/material';
import axios from 'axios';

const Airlines = () => {
    const [airlines, setAirlines] = useState([]);
    const [newAirline, setNewAirline] = useState({ id: null, name: '', code: '' });
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        fetchAirlines();
    }, []);

    const fetchAirlines = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/airlines/all');
            setAirlines(response.data.data);
        } catch (error) {
            console.error('Failed to fetch airline data:', error.response?.data || error.message);
        }
    };

    const handleAddOrUpdate = async () => {
        const cleanCode = newAirline.code.toUpperCase().replace(/[^A-Z0-9]/g, '').substring(0, 3);
        const cleanName = newAirline.name.trimStart();

        if (!cleanName || cleanName.length < 2) {
            alert("Airline name must be at least 2 characters!");
            return;
        }

        if (!cleanCode || cleanCode.length < 2) {
            alert("Airline code must be 2-3 characters and contain only uppercase letters/numbers!");
            return;
        }

        const payload = {
            id: newAirline.id,
            name: cleanName,
            code: cleanCode
        };

        try {
            if (isEditing) {
                await axios.put('http://localhost:8080/api/airlines', payload);
            } else {
                const { id, ...createPayload } = payload;
                await axios.post('http://localhost:8080/api/airlines/create', createPayload);
            }
            resetForm();
            fetchAirlines();
        } catch (error) {
            console.error(isEditing ? 'Failed to update airline:' : 'Failed to add airline:', error.response?.data || error.message);
            alert(error.response?.data?.message || "An unexpected error occurred!");
        }
    };

    const handleEdit = (airline) => {
        setNewAirline({
            id: airline.id,
            name: airline.name,
            code: airline.code
        });
        setIsEditing(true);
    };

    const handleDelete = async (id) => {
        if (window.confirm("Are you sure you want to delete this airline?")) {
            try {
                await axios.delete(`http://localhost:8080/api/airlines/${id}`);
                fetchAirlines();
            } catch (error) {
                console.error('Failed to delete airline:', error.response?.data || error.message);
            }
        }
    };

    const resetForm = () => {
        setNewAirline({ id: null, name: '', code: '' });
        setIsEditing(false);
    };

    return (
        <Box p={3}>
            <Typography variant="h5" gutterBottom>Airlines</Typography>
            <Box display="flex" gap={2} mb={2} flexWrap="wrap">
                <TextField label="Name" value={newAirline.name}
                           onChange={e => setNewAirline({ ...newAirline, name: e.target.value })} />
                <TextField label="Code" value={newAirline.code}
                           onChange={e => setNewAirline({ ...newAirline, code: e.target.value })}
                           helperText="2-3 uppercase letters or numbers, no spaces" />
                <Button variant="contained" onClick={handleAddOrUpdate}>
                    {isEditing ? 'Update' : 'Add'}
                </Button>
                {isEditing && (
                    <Button variant="outlined" color="secondary" onClick={resetForm}>Cancel</Button>
                )}
            </Box>

            <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Code</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {airlines.map((airline) => (
                            <TableRow key={airline.id}>
                                <TableCell>{airline.id}</TableCell>
                                <TableCell>{airline.name}</TableCell>
                                <TableCell>{airline.code}</TableCell>
                                <TableCell>
                                    <Button size="small" variant="outlined" onClick={() => handleEdit(airline)}>Edit</Button>
                                    <Button size="small" color="error" variant="outlined" onClick={() => handleDelete(airline.id)} sx={{ ml: 1 }}>Delete</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default Airlines;
