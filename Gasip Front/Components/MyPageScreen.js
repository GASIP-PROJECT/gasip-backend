import React, { useEffect } from 'react';
import { StyleSheet, Text, View, Button, Pressable, Image } from 'react-native';
import { useSelector } from 'react-redux';
import * as SecureStore from 'expo-secure-store';

import ThemeStyle from './ThemeStyle';

import useAuthDispatch from '../Utils/AuthHooks';
import { removeToken, getUserProfile } from '../Utils/AuthFunc';

function MyPageScreen({navigation, route}) {
    const authDispatch = useAuthDispatch();
    const {userToken} = useSelector(state => state.authReducer);
    const [userProfile, setUserProfile] = React.useState(null);
    
    const handleSignOut = () => {
        // Perform sign out logic here
        console.log('Signing out...');
        removeToken();
        authDispatch.signOut();
    };

    useEffect(() => {
        console.log("userToken : ", userToken);
        getUserProfile(userToken).then((data) => {
            setUserProfile(data.response);
            console.log(data.response);
        });
    }, []);
        
    return (
        <View style={ThemeStyle.basicHeaderContainer}>
            <View>
                <View>
                    
                    <Text>Email: {userProfile ? userProfile.email : null}</Text>
                    <Text>Name: {userProfile ? userProfile.name : null}</Text>
                </View>
                <View
                    style={{
                        ...ThemeStyle.basicButtonContainer
                    }}
                >
                    <Pressable
                        style={{
                            ...ThemeStyle.basicButton
                        }}
                        onPress={handleSignOut}>
                            <Text>Sign Out</Text>
                    </Pressable>
                </View>
            </View>
        </View>
    );
}


export default MyPageScreen;