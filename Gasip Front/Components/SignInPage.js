import React, { useRef } from 'react';
import { View, Text, TextInput, Button, Pressable } from 'react-native';
import * as SecureStore from 'expo-secure-store';

import ThemeStyle from './ThemeStyle';

import useAuthDispatch from '../Utils/AuthHooks';
import { storeToken, postSignIn } from '../Utils/AuthFunc';


const SignInPage = ({navigation, route}) => {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [isValid, setIsValid] = React.useState(true);
    // const { signIn } = route.params.authContext;
    const passwordRef = useRef(null);

    const authDispatch = useAuthDispatch();

    const handleSignIn = async () =>{
        console.log('Signing in...');
        console.log('Email: ', email, 'Password: ', password);
        const res = await postSignIn({email: email, password: password});
        console.log("res : ", res);
        if (res.access_token) {
            console.log("Sign In Success");
            authDispatch.signIn(res.access_token);
        } else {
            setIsValid(false);
            console.log("Sign In Failed");
        }
        // storeToken("USER_TOKEN");
        // authDispatch.signIn("USER_TOKEN");
    };

  return (
    <View style={{
        ...ThemeStyle.basicContainer,
        justifyContent: 'center',
        padding: 50,
        flex: 1,
    }}>
      <Text>Email:</Text>
        <TextInput
            style={{
                height: 40,
                borderBottomColor: !isValid ? "red" : "gray",
                borderBottomWidth: !isValid ? 1 : 0,
            }}
            value={email}
            onChangeText={setEmail}
            placeholder="이메일을 입력해주세요."
            enterKeyHint='next'
            autoFocus
            blurOnSubmit={false}
            onSubmitEditing={() => passwordRef.current.focus()}
        />

        <Text>Password:</Text>
        <TextInput
            style={{
                height: 40,
                borderBottomColor: !isValid ? "red" : "gray",
                borderBottomWidth: !isValid ? 1 : 0,
            }}
            value={password}
            onChangeText={setPassword}
            placeholder="비밀번호를 입력해주세요."
            enterKeyHint='done'
            secureTextEntry
            onSubmitEditing={handleSignIn}
            ref={passwordRef}
        />
        {!isValid && <Text style={{ color: "red", fontSize: 13 }}>이메일 또는 비밀번호가 일치하지 않습니다.</Text>}
        <View
            style={{
                ...ThemeStyle.basicButtonContainer
            }}
        >
            <Pressable 
                style={{
                    ...ThemeStyle.basicButton
                }}
                onPress={handleSignIn} >
                <Text>Sign In</Text>
            </Pressable>
            <Pressable
                style={{
                    flex: 1,
                    flexGrow: 1,
                    alignItems: 'center',
                    borderRadius: 5,
                    margin: 5,
                    paddingVertical: 10,
                    backgroundColor: 'skyblue',
                }}
                onPress={() => navigation.navigate('SignUpPage')} >
                <Text>Sign Up</Text>
                </Pressable>
        </View>
    </View>
  );
};

export default SignInPage;
