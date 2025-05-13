import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import { Box, Button, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const AirportFlights = () => {
    const { airportId } = useParams();
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        const fetchFlights = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/airports/${airportId}/flights`);
                setFlights(response.data.data);
            } catch (error) {
                console.error("Uçuşlar alınamadı:", error);
            }
        };
        fetchFlights();
    }, [airportId]);

    return (
        <Box p={3}>
            <Typography variant="h5">Airport ID {airportId} - Uçuşlar</Typography>
            <Button variant="outlined" component={Link} to="/airports" sx={{ mt: 2, mb: 2 }}>
                ← Geri Dön
            </Button>

            <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Flight Number</TableCell>
                            <TableCell>Airline</TableCell>
                            <TableCell>From</TableCell>
                            <TableCell>To</TableCell>
                            <TableCell>DateTime</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {flights.map(flight => (
                            <TableRow key={flight.id}>
                                <TableCell>{flight.id}</TableCell>
                                <TableCell>{flight.number}</TableCell>
                                <TableCell>{flight.airlineName}</TableCell>
                                <TableCell>{flight.sourceAirportName}</TableCell>
                                <TableCell>{flight.destinationAirportName}</TableCell>
                                <TableCell>{flight.dateTime}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default AirportFlights;
