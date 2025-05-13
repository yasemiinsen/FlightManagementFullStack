import React from 'react';
import './App.css';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainLayout from './layout/MainLayout';
import Dashboard from './pages/Dashboard';
import Flights from './pages/Flights';
import Airports from "./pages/Airports";
import Airlines from "./pages/Airlines";
import DailyFlights from "./pages/DailyFlights";
import FlightsByDateRange from './pages/FlightsByDateRange';

import AirportFlights from './pages/AirportFlights';


function App() {
  return (
    <Router>
      <MainLayout>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/flights" element={<Flights />} />
          <Route path="/airlines" element={<Airlines />} />
          <Route path="/airports" element={<Airports />} />
          <Route path="/daily-flights" element={<DailyFlights />} />
          <Route path="/flights-range" element={<FlightsByDateRange />} />
          <Route path="/airports/:airportId/flights" element={<AirportFlights />} />

        </Routes>
      </MainLayout>
    </Router>
  );
}

export default App;
