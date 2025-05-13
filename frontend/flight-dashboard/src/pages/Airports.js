import React, { useEffect, useState } from 'react';
import {
    Box, Button, Paper, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, TextField, Typography
} from '@mui/material';
import axios from 'axios';

const Airports = () => {
    const [airports, setAirports] = useState([]);
    const [newAirport, setNewAirport] = useState({ id: null, name: '', city: '', country: '' });
    const [isEditing, setIsEditing] = useState(false);
    const [flights, setFlights] = useState([]);
    const [selectedAirportId, setSelectedAirportId] = useState(null);

    useEffect(() => {
        fetchAirports();
    }, []);

    const fetchAirports = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/airports');
            setAirports(response.data.data);
        } catch (error) {
            console.error('Failed to fetch airport data:', error);
        }
    };

    const fetchFlightsByAirport = async (airportId) => {
        if (selectedAirportId === airportId) {
            setSelectedAirportId(null);
            setFlights([]);
            return;
        }
        try {
            const response = await axios.get(`http://localhost:8080/api/airports/${airportId}/flights`);
            setFlights(response.data.data);
            setSelectedAirportId(airportId);
        } catch (error) {
            console.error('Failed to fetch flights:', error);
        }
    };

    const handleAddOrUpdate = async () => {
        try {
            if (isEditing) {
                await axios.put('http://localhost:8080/api/airports', newAirport);
            } else {
                const { id, ...createData } = newAirport;
                await axios.post('http://localhost:8080/api/airports/create', createData);
            }
            resetForm();
            fetchAirports();
        } catch (error) {
            console.error('Failed to save airport:', error);
        }
    };

    const handleEdit = (airport) => {
        setNewAirport(airport);
        setIsEditing(true);
    };

    const handleDelete = async (id) => {
        if (window.confirm("Are you sure you want to delete this airport?")) {
            try {
                await axios.delete(`http://localhost:8080/api/airports/${id}`);
                fetchAirports();
            } catch (error) {
                console.error('Failed to delete airport:', error);
            }
        }
    };

    const resetForm = () => {
        setNewAirport({ id: null, name: '', city: '', country: '' });
        setIsEditing(false);
    };

    return (
        <Box p={3}>
            <Typography variant="h5" gutterBottom>Airports</Typography>
            <Box display="flex" gap={2} mb={2} flexWrap="wrap">
                <TextField label="Name" value={newAirport.name} onChange={e => setNewAirport({ ...newAirport, name: e.target.value })} />
                <TextField label="City" value={newAirport.city} onChange={e => setNewAirport({ ...newAirport, city: e.target.value })} />
                <TextField label="Country" value={newAirport.country} onChange={e => setNewAirport({ ...newAirport, country: e.target.value })} />
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
                            <TableCell>City</TableCell>
                            <TableCell>Country</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {airports.map((airport) => (
                            <React.Fragment key={airport.id}>
                                <TableRow>
                                    <TableCell>{airport.id}</TableCell>
                                    <TableCell>{airport.name}</TableCell>
                                    <TableCell>{airport.city}</TableCell>
                                    <TableCell>{airport.country}</TableCell>
                                    <TableCell>
                                        <Button size="small" variant="outlined" onClick={() => handleEdit(airport)}>Edit</Button>
                                        <Button size="small" color="error" variant="outlined" onClick={() => handleDelete(airport.id)} sx={{ ml: 1 }}>Delete</Button>
                                        <Button size="small" color="primary" variant="outlined" onClick={() => fetchFlightsByAirport(airport.id)} sx={{ ml: 1 }}>
                                            {selectedAirportId === airport.id ? 'Hide Flights' : 'Show Flights'}
                                        </Button>
                                    </TableCell>
                                </TableRow>
                                {selectedAirportId === airport.id && flights.length > 0 && (
                                    <TableRow>
                                        <TableCell colSpan={5}>
                                            <Typography variant="subtitle1">Flights:</Typography>
                                            <ul>
                                                {flights.map(flight => (
                                                    <li key={flight.id}>
                                                        {flight.number} | {flight.sourceAirportName} → {flight.destinationAirportName} | {flight.dateTime}
                                                    </li>
                                                ))}
                                            </ul>
                                        </TableCell>
                                    </TableRow>
                                )}
                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default Airports;
