import React, {useState, useRef} from 'react';
import HomeStack from '../Components/HomeStack';
import MyPageStack from '../Components/MyPageStack';

import { Text } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Provider } from "react-redux";

import { Foundation } from '@expo/vector-icons';

import authStore from '../reducers/AuthStore';
import ThemeStyle from '../Components/ThemeStyle';

const Tab = createBottomTabNavigator();

function Layout({navigation}) {
    return (
        <Provider store={authStore}>

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
                            tabBarLabel: ({focused}) => (
                            <Text
                                style={focused ? ThemeStyle.focusedTabLabel: ThemeStyle.unfocusedTabLabel}
                            >
                                홈
                            </Text>)
                        }}
                    />
                    <Tab.Screen name="MyPageStack" component={MyPageStack}
                        options={{
                            tabBarShowLabel: true,
                            tabBarIcon: () => (
                                <Foundation name="torsos-all" size={24} color="black" />
                            ),
                            unmountOnBlur: true,
                            tabBarLabel: ({focused}) => (
                            <Text
                                style={focused ? ThemeStyle.focusedTabLabel: ThemeStyle.unfocusedTabLabel}>
                                마이페이지
                            </Text>)
                        }}
                    /> 
                </Tab.Navigator>
            </NavigationContainer>
        </Provider>

    );
}

export default Layout;
