import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography } from "@mui/material";

const FlightsByDateRange = () => {
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [flights, setFlights] = useState([]);

    const fetchFlights = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/flights/range`, {
                params: { startDate, endDate }
            });
            setFlights(response.data.data);
        } catch (error) {
            console.error("Uçuşlar alınamadı:", error);
        }
    };

    return (
        <div>
            <Typography variant="h5" gutterBottom>Tarih Aralığına Göre Uçuşlar</Typography>
            <TextField type="date" value={startDate} onChange={e => setStartDate(e.target.value)} />
            <TextField type="date" value={endDate} onChange={e => setEndDate(e.target.value)} sx={{ ml: 2 }} />
            <Button variant="contained" onClick={fetchFlights} sx={{ ml: 2 }}>Getir</Button>

            <TableContainer component={Paper} sx={{ mt: 3, overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Havayolu</TableCell>
                            <TableCell>Kalkış Havalimanı</TableCell>
                            <TableCell>Varış Havalimanı</TableCell>
                            <TableCell>Uçuş Zamanı</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {flights.map((flight) => (
                            <TableRow key={flight.id}>
                                <TableCell>{flight.id}</TableCell>
                                <TableCell>{flight.airlineName}</TableCell>
                                <TableCell>{flight.sourceAirportName}</TableCell>
                                <TableCell>{flight.destinationAirportName}</TableCell>
                                <TableCell>{flight.dateTime}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default FlightsByDateRange;
