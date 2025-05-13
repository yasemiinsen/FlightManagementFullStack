import React, { useEffect, useState } from "react";
import axios from "axios";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography } from "@mui/material";

const DailyFlights = () => {
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        const fetchFlights = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/flights/daily");
                setFlights(response.data.data);
            } catch (error) {
                console.error("recent flights cannot take:", error);
            }
        };
        fetchFlights();
    }, []);

    return (
        <div>
            <Typography variant="h5" gutterBottom>Recent Flights</Typography>
            <TableContainer component={Paper} sx={{ overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Airline</TableCell>
                            <TableCell>Source Airline</TableCell>
                            <TableCell>Destination Airline</TableCell>
                            <TableCell>Flight Time</TableCell>
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

export default DailyFlights;
