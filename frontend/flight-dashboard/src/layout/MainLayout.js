import React from 'react';
import { Box, Drawer, List, ListItemButton, ListItemIcon, ListItemText, Toolbar, AppBar, Typography } from '@mui/material';
import { Flight, Public, Business } from '@mui/icons-material';
import { FaPlaneDeparture } from 'react-icons/fa';
import { Link, useLocation } from 'react-router-dom';

const drawerWidth = 240;

const menuItems = [
    { text: 'Flights', icon: <Flight />, path: '/flights' },
    { text: 'Airlines', icon: <Business />, path: '/airlines' },
    { text: 'Airports', icon: <Public />, path: '/airports' },
    { text: 'Recent Flights', icon: <FaPlaneDeparture />, path: '/daily-flights' }
];

const MainLayout = ({ children }) => {
    const location = useLocation();

    return (
        <Box sx={{ display: 'flex' }}>
            <Drawer
                variant="permanent"
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                        backgroundColor: '#ffffff',         //  Sidebar beyaz
                        color: '#1b3b82',                   //  Yazılar lacivert
                        borderRight: '4px solid #f7c61b',
                        boxShadow: '2px 0px 16px rgba(27, 59, 130, 0.12)'
                    },
                }}
            >
                <Toolbar />
                <Box sx={{ overflow: 'auto' }}>
                    <List>
                        {menuItems.map(item => (
                            <ListItemButton
                                key={item.text}
                                component={Link}
                                to={item.path}
                                selected={location.pathname === item.path}
                                sx={{
                                    color: '#1b3b82',
                                    '& .MuiListItemIcon-root': { color: '#1b3b82' },    //  Iconlar lacivert
                                    '&:hover': { backgroundColor: '#f5f5f5' },          //  Hover efekti açık gri
                                    '&.Mui-selected': {
                                        backgroundColor: '#e8eaf6',                     //  Aktif item açık mavi ton
                                        fontWeight: 'bold'
                                    }
                                }}
                            >
                                <ListItemIcon>
                                    {item.icon}
                                </ListItemIcon>
                                <ListItemText primary={item.text} />
                            </ListItemButton>
                        ))}
                    </List>
                </Box>
            </Drawer>

            <Box
                component="main"
                sx={{
                    flexGrow: 1,
                    p: 3,
                    backgroundColor: '#f9fafc',
                    minHeight: '100vh'
                }}
            >
                <AppBar
                    position="fixed"
                    sx={{
                        zIndex: (theme) => theme.zIndex.drawer + 1,
                        backgroundColor: '#1b3b82',            // AppBar lacivert
                        borderBottom: '4px solid #f7c61b'
                    }}
                >
                    <Toolbar>
                        <Typography variant="h6" noWrap component="div">
                            Flight Management System
                        </Typography>
                    </Toolbar>
                </AppBar>
                <Toolbar />
                {children}
            </Box>
        </Box>
    );
};

export default MainLayout;
