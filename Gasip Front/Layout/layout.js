import React, {useState, useRef} from 'react';
import { SafeAreaView, View, StyleSheet } from 'react-native';
import Home from '../Components/Home';
import About from '../Components/About';
import Prof from '../Components/Prof';
import WritePage from '../Components/WritePage';

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

function Layout({navigation}) {
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Home" component={Home} 
                    options={{
                        headerShown: false,
                    }}
                />
                <Stack.Screen name="About" component={About} 
                    options={{
                        headerShown: false,
                    }}
                />
                <Stack.Screen name="Prof" component={Prof} 
                    options={{
                        headerShown: true,
                    }}
                />
                <Stack.Screen name="Write" component={WritePage}
                    options={{
                        headerShown: false,
                    }}
                />
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default Layout;
