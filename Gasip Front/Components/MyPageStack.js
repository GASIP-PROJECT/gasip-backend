import React, { useEffect, useReducer } from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import * as SecureStore from 'expo-secure-store';
import { useSelector, useDispatch } from 'react-redux';

import MyPageScreen from '../Components/MyPageScreen';
import SignUpPage from '../Components/SignUpPage';
import SignInPage from '../Components/SignInPage';
import SplashScreen from './SplashScreen';

import { checkToken } from '../Utils/AuthFunc';
import useAuthDispatch from '../Utils/AuthHooks';


const Stack = createNativeStackNavigator();

function HomeStack(){ 
    const authDispatch = useAuthDispatch();
    const {userToken, isLoading, isSignout} = useSelector(state => state.authReducer);

    useEffect(() => {
        authDispatch.restoreToken();

        if (userToken !== null) {
            checkToken(userToken).then((res) => {
                if (res === 401) {
                    authDispatch.signOut();
                }
            })
        }
    }, []);


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
                <>
                    <Stack.Screen name="MyPageScreen" component={MyPageScreen}
                        options={{
                            headerShown: true,
                            headerTitle: '마이페이지'
                        }}
                    />
                </>
                ) : (
                <>
                    <Stack.Screen name="SignInPage" component={SignInPage}
                        options={{
                            headerShown: false,
                        }}
                    />
                    <Stack.Screen name="SignUpPage" component={SignUpPage}
                        options={{
                            headerShown: false,
                            animationTypeForReplace: isSignout ? 'pop' : 'push',
                        }}
                    />
                    
                </>
            )
            }
            
        </Stack.Navigator>
    )
}

export default HomeStack;