import React, {useState, useRef} from 'react';
import { SafeAreaView, View, StyleSheet } from 'react-native';
import Home from '../Components/HomeScreen';
import About from '../Components/About';
import Prof from '../Components/Prof';
import WritePage from '../Components/WritePage';
import HomeStack from '../Components/HomeStack';
import MyPageStack from '../Components/MyPageStack';

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import { Foundation } from '@expo/vector-icons';

const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

function Layout({navigation}) {
    return (
        <NavigationContainer>
            <Tab.Navigator
                screenOptions={({route}) => ({headerShown: false})}
                initialRouteName='HomeStack'
                >
                <Tab.Screen name="HomeStack" component={HomeStack}
                    options={{
                        tabBarShowLabel: true,
                        tabBarIcon: () => (
                            <Foundation name="home" size={24} color="black" />
                        ),
                        unmountOnBlur: true,
                        tabBarLabel: "홈"
                    }}
                />
                <Tab.Screen name="MyPageStack" component={MyPageStack}
                    options={{
                        tabBarShowLabel: true,
                        tabBarIcon: () => (
                            <Foundation name="torsos-all" size={24} color="black" />
                        ),
                        unmountOnBlur: true,
                        tabBarLabel: "내 정보"
                    }}

                /> 
            </Tab.Navigator>
            {/* <Stack.Navigator>
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
            </Stack.Navigator> */}
        </NavigationContainer>
    );
}

export default Layout;
