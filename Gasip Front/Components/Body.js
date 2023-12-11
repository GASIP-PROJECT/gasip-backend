import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import ThemeStyle from './ThemeStyle';
import Home from './Home';
import About from './About';

const Tab = createBottomTabNavigator();

function Body({navigation}){

    return (
        <Tab.Navigator
            screenOptions={{
                tabBarActiveTintColor: '#e91e63',
            }}
        >
            <Tab.Screen name="Home" component={Home} />
            <Tab.Screen name="About" component={About} />

        </Tab.Navigator>
    );
};

export default Body;
