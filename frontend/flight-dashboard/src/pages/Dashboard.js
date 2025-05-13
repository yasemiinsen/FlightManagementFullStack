import React, { useEffect, useState } from 'react';
import { Box, Grid, Paper, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { Flight, Business, LocationOn } from '@mui/icons-material';
import axios from 'axios';

const Dashboard = () => {
    const navigate = useNavigate();
    const [stats, setStats] = useState({ totalFlights: 0, totalAirlines: 0, totalAirports: 0 });
    const [recentFlights, setRecentFlights] = useState([]);

    useEffect(() => {
        fetchStats();
        fetchRecentFlights();
    }, []);

    const fetchStats = async () => {
        try {
            const [flights, airlines, airports] = await Promise.all([
                axios.get('http://localhost:8080/api/flights'),
                axios.get('http://localhost:8080/api/airlines/all'),
                axios.get('http://localhost:8080/api/airports')
            ]);

            setStats({
                totalFlights: flights.data.data.length,
                totalAirlines: airlines.data.data.length,
                totalAirports: airports.data.data.length
            });
        } catch (error) {
            console.error('Failed to fetch statistics:', error);
        }
    };

    const fetchRecentFlights = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/flights');
            setRecentFlights(response.data.data.slice(0, 5));
        } catch (error) {
            console.error('Failed to fetch recent flights:', error);
        }
    };

    return (
        <Box sx={{ p: 3 }}>
            <Paper elevation={3} sx={{ p: 4, mb: 4, textAlign: 'center', background: '#fff3cd', border: '2px solid #f7c61b', borderRadius: '12px' }}>
                <Typography variant="h3" sx={{ color: '#1b3b82', fontWeight: 'bold' }}>Welcome to Flight Management System</Typography>
            </Paper>

            <Typography variant="h4" sx={{ color: '#1b3b82', mb: 3, fontWeight: 'bold' }}>System Statistics</Typography>
            <Box sx={{ px: { xs: 2, sm: 4, md: 6 } }}>
                <Grid container spacing={3} sx={{ mb: 4, maxWidth: '1200px' }}>
                    <Grid item xs={12} sm={6} md={4}>
                        <Paper onClick={() => navigate('/flights')} sx={paperStyle('#4CAF50', '#66BB6A')}>
                            <Flight sx={{ fontSize: 40, mb: 2, opacity: 0.8 }} />
                            <Typography variant="h6">Total Flights</Typography>
                            <Typography variant="h3">{stats.totalFlights}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs={12} sm={6} md={4}>
                        <Paper onClick={() => navigate('/airlines')} sx={paperStyle('#2196F3', '#42A5F5')}>
                            <Business sx={{ fontSize: 40, mb: 2, opacity: 0.8 }} />
                            <Typography variant="h6">Total Airlines</Typography>
                            <Typography variant="h3">{stats.totalAirlines}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs={12} sm={6} md={4}>
                        <Paper onClick={() => navigate('/airports')} sx={paperStyle('#FF9800', '#FFA726')}>
                            <LocationOn sx={{ fontSize: 40, mb: 2, opacity: 0.8 }} />
                            <Typography variant="h6">Total Airports</Typography>
                            <Typography variant="h3">{stats.totalAirports}</Typography>
                        </Paper>
                    </Grid>
                </Grid>
            </Box>

            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                <Typography variant="h4" sx={{ color: '#1b3b82', fontWeight: 'bold' }}>Recently Added Flights</Typography>
                <Button variant="contained" onClick={() => navigate('/flights')} sx={buttonStyle}>View All Flights</Button>
            </Box>

            <TableContainer component={Paper} sx={{ borderRadius: '12px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)', overflowX: 'auto' }}>
                <Table>
                    <TableHead>
                        <TableRow sx={{ background: 'linear-gradient(90deg, #1b3b82 60%, #f7c61b 100%)' }}>
                            {['Flight No', 'Airline', 'Departure', 'Arrival', 'Date'].map(header => (
                                <TableCell key={header} sx={{ fontWeight: 'bold', color: 'white' }}>{header}</TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {recentFlights.map((flight) => (
                            <TableRow key={flight.id} sx={{ '&:hover': { background: '#f8f9fa' } }}>
                                <TableCell>{flight.number}</TableCell>
                                <TableCell>{flight.airlineName}</TableCell>
                                <TableCell>{flight.sourceAirportName}</TableCell>
                                <TableCell>{flight.destinationAirportName}</TableCell>
                                <TableCell>{new Date(flight.dateTime).toLocaleString()}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

const paperStyle = (color, hoverColor) => ({
    p: 4, textAlign: 'center', background: `linear-gradient(135deg, ${color} 0%, ${color} 100%)`,
    color: 'white', borderRadius: '16px', boxShadow: `0 8px 16px ${color}33`,
    cursor: 'pointer', transition: 'all 0.3s ease',
    '&:hover': { transform: 'translateY(-5px)', boxShadow: `0 12px 20px ${color}66`, background: `linear-gradient(135deg, ${hoverColor} 0%, ${color} 100%)` }
});

const buttonStyle = {
    background: 'linear-gradient(135deg, #1b3b82 0%, #16275a 100%)',
    color: 'white', px: 3, py: 1.5, borderRadius: 2, '&:hover': {
        background: 'linear-gradient(135deg, #16275a 0%, #1b3b82 100%)',
        transform: 'translateY(-2px)', boxShadow: '0 4px 8px rgba(0,0,0,0.2)'
    }
};

export default Dashboard;
