import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Box, Button, Paper, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, TextField, Typography, Select, MenuItem, FormControl, InputLabel, Dialog, DialogTitle, DialogContent, DialogActions
} from '@mui/material';

const Flights = () => {
    const [flights, setFlights] = useState([]);
    const [airports, setAirports] = useState([]);
    const [airlines, setAirlines] = useState([]);
    const [newFlight, setNewFlight] = useState({
        id: '',
        number: '',
        dateTime: '',
        airlineId: '',
        sourceAirportId: '',
        destinationAirportId: ''
    });
    const [popupMessage, setPopupMessage] = useState('');
    const [popupOpen, setPopupOpen] = useState(false);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const flightsRes = await axios.get('http://localhost:8080/api/flights');
            setFlights(flightsRes.data.data);
            const airportsRes = await axios.get('http://localhost:8080/api/airports');
            setAirports(airportsRes.data.data);
            const airlinesRes = await axios.get('http://localhost:8080/api/airlines/all');
            setAirlines(airlinesRes.data.data);
        } catch (err) {
            console.error("API call error:", err);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewFlight(prev => ({
            ...prev,
            [name]: name === 'number' ? value.toUpperCase().replace(/\s/g, '') : value
        }));
    };

    const handleAddOrUpdate = async () => {
        try {
            const formatDateTime = (input) => input ? new Date(input).toISOString().slice(0, 19) : null;
            const payload = {
                number: newFlight.number,
                dateTime: formatDateTime(newFlight.dateTime),
                airlineId: Number(newFlight.airlineId),
                sourceAirportId: Number(newFlight.sourceAirportId),
                destinationAirportId: Number(newFlight.destinationAirportId)
            };

            if (!newFlight.id) {
                const flightDate = new Date(newFlight.dateTime).toISOString().split('T')[0];
                const sourceAirport = airports.find(a => a.id === payload.sourceAirportId);
                const destAirport = airports.find(a => a.id === payload.destinationAirportId);
                const sameDayFlights = flights.filter(flight => {
                    const flightDateStr = new Date(flight.dateTime).toISOString().split('T')[0];
                    return flightDateStr === flightDate &&
                        flight.sourceAirportName === sourceAirport?.name &&
                        flight.destinationAirportName === destAirport?.name;
                });

                if (sameDayFlights.length >= 3) {
                    setPopupMessage('Maximum 3 flights per day are allowed between these airports.');
                    setPopupOpen(true);
                    return;
                }
            }

            if (newFlight.id) {
                payload.id = Number(newFlight.id);
                await axios.put('http://localhost:8080/api/flights', payload);
            } else {
                await axios.post('http://localhost:8080/api/flights/create', payload);
            }

            setNewFlight({ id: '', number: '', dateTime: '', airlineId: '', sourceAirportId: '', destinationAirportId: '' });
            fetchData();
        } catch (err) {
            console.error("Error:", err);
            setPopupMessage('Error adding flight: ' + (err.response?.data?.message || err.message));
            setPopupOpen(true);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/flights/${id}`);
            fetchData();
        } catch (err) {
            console.error("Delete error:", err);
        }
    };

    const today = new Date().toISOString().slice(0, 16);

    return (
        <Box p={3}>
            <Typography variant="h5">Flights</Typography>
            <Dialog open={popupOpen} onClose={() => setPopupOpen(false)}>
                <DialogTitle>Information</DialogTitle>
                <DialogContent>
                    <Typography>{popupMessage}</Typography>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setPopupOpen(false)} variant="contained">OK</Button>
                </DialogActions>
            </Dialog>

            <Box display="flex" gap={2} mt={2} mb={2} flexWrap="wrap">
                <TextField sx={{ minWidth: 150 }} label="ID (update only)" name="id" value={newFlight.id} onChange={handleChange} />
                <TextField sx={{ minWidth: 150 }} label="Number" name="number" value={newFlight.number} onChange={handleChange} />
                <TextField sx={{ minWidth: 150 }} label="DateTime" name="dateTime" type="datetime-local" InputLabelProps={{ shrink: true }}
                           value={newFlight.dateTime} inputProps={{ min: today }} onChange={handleChange} />
                <FormControl sx={{ minWidth: 150 }}>
                    <InputLabel>Airline</InputLabel>
                    <Select name="airlineId" value={newFlight.airlineId} onChange={handleChange}>
                        {airlines.map(a => <MenuItem key={a.id} value={a.id}>{a.name}</MenuItem>)}
                    </Select>
                </FormControl>
                <FormControl sx={{ minWidth: 150 }}>
                    <InputLabel>Source Airport</InputLabel>
                    <Select name="sourceAirportId" value={newFlight.sourceAirportId} onChange={handleChange}>
                        {airports.map(a => <MenuItem key={a.id} value={a.id}>{a.name}</MenuItem>)}
                    </Select>
                </FormControl>
                <FormControl sx={{ minWidth: 150 }}>
                    <InputLabel>Destination Airport</InputLabel>
                    <Select name="destinationAirportId" value={newFlight.destinationAirportId} onChange={handleChange}>
                        {airports.map(a => <MenuItem key={a.id} value={a.id}>{a.name}</MenuItem>)}
                    </Select>
                </FormControl>
                <Button variant="contained" onClick={handleAddOrUpdate}>Add / Update</Button>
            </Box>

            <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Number</TableCell>
                            <TableCell>Airline</TableCell>
                            <TableCell>From</TableCell>
                            <TableCell>To</TableCell>
                            <TableCell>DateTime</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {flights.map(f => (
                            <TableRow key={f.id}>
                                <TableCell>{f.id}</TableCell>
                                <TableCell>{f.number}</TableCell>
                                <TableCell>{f.airlineName}</TableCell>
                                <TableCell>{f.sourceAirportName}</TableCell>
                                <TableCell>{f.destinationAirportName}</TableCell>
                                <TableCell>{f.dateTime}</TableCell>
                                <TableCell>
                                    <Button onClick={() => setNewFlight({
                                        id: f.id,
                                        number: f.number,
                                        dateTime: f.dateTime.substring(0, 16),
                                        airlineId: airlines.find(a => a.name === f.airlineName)?.id || '',
                                        sourceAirportId: airports.find(a => a.name === f.sourceAirportName)?.id || '',
                                        destinationAirportId: airports.find(a => a.name === f.destinationAirportName)?.id || ''
                                    })}>Edit</Button>
                                    <Button color="error" onClick={() => handleDelete(f.id)}>Delete</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default Flights;
