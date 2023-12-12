import React, { useEffect, useReducer } from 'react';
import { View, ActivityIndicator, Text } from 'react-native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import * as SecureStore from 'expo-secure-store';
import { useSelector, useDispatch } from 'react-redux';

import MyPageScreen from '../Components/MyPageScreen';
import SignUpPage from '../Components/SignUpPage';
import SplashScreen from './SplashScreen';

const Stack = createNativeStackNavigator();

function HomeStack(){ 
    const dispatch = useDispatch();
    const {userToken, isLoading, isSignout} = useSelector(state => state.authReducer);

    useEffect(() => {
        const bootstrapAsync = async () => {
            let restoreToken;
            try {
                restoreToken = await SecureStore.getItemAsync('userToken');
                console.log("restoreToken: ", restoreToken);
            } catch (e) {
                console.log(e);
            }
            dispatch({ type: 'RESTORE_TOKEN', token: restoreToken });
        };
        bootstrapAsync();
    
    }, []);

    const authContext = React.useMemo(
        () => ({
            signIn: async (data) =>{
                dispatch({ 
                    type: "SIGN_IN",
                    token: "dummy-auth-token"
                });
            },
            signOut: () => dispatch({type: "SIGN_OUT"}),
            signUp: async (data) => {
                dispatch({ 
                    type: "SIGN_IN",
                    token: "dummy-auth-token"
                });
            },
        }),
        []
    );


    if (isLoading) {
        // We haven't finished checking for the token yet
        return (
            <SplashScreen />
        );
    }

    return(
        <Stack.Navigator
            screenOptions={({route}) => ({
                headerShown: false
                })}
        >
            {userToken !== null ? 
            (
            <Stack.Screen name="MyPageScreen" component={MyPageScreen}
                options={{
                    headerShown: false,
                }}
                initialParams={{authContext}}
            />
            ) : (
            <Stack.Screen name="SignUpPage" component={SignUpPage}
                options={{
                    headerShown: false,
                }}
                initialParams={{authContext}}
            />
            )
            }
            
        </Stack.Navigator>
    )
}

export default HomeStack;