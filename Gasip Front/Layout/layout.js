import React, {useState, useRef} from 'react';
import HomeStack from '../Components/HomeStack';
import MyPageStack from '../Components/MyPageStack';

import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Provider } from "react-redux";

import { Foundation } from '@expo/vector-icons';

import authStore from '../reducers/AuthStore';

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
            </NavigationContainer>
        </Provider>

    );
}

export default Layout;
