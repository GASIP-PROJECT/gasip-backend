import React, {useContext, useRef } from 'react';
import { View, Text, TextInput, Button, Pressable } from 'react-native';
import * as SecureStore from 'expo-secure-store';

import useAuthDispatch from '../Utils/AuthHooks';
import { postSignUp } from '../Utils/AuthFunc';

import ThemeStyle from './ThemeStyle';


const SignUpPage = ({navigation, route}) => {
  const [name, setName] = React.useState('');
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const emailRef = useRef(null);
  const passwordRef = useRef(null);

  const authDispatch = useAuthDispatch();

  const handleSignUp = () => {
    // Perform sign up logic here
    console.log('Signing up...');
    console.log('Email: ', email, 'Password: ', password);
    // storeToken("USER_TOKEN");
    authDispatch.signUp({name: name, email: email, password: password});
    navigation.goBack();
};

  return (
    <View style={{
        ...ThemeStyle.basicContainer,
        justifyContent: 'center',
        padding: 50,
    }}>
        <Text>Name:</Text>
        <TextInput
            value={name}
            onChangeText={setName}
            placeholder="이름을 입력해주세요."
            autoFocus
            blurOnSubmit={false}
            onSubmitEditing={() => emailRef.current.focus()}
        />

      <Text>Email:</Text>
      <TextInput
        value={email}
        onChangeText={setEmail}
        placeholder="이메일을 입력해주세요."
        blurOnSubmit={false}
        ref={emailRef}
        onSubmitEditing={() => passwordRef.current.focus()}
      />

      <Text>Password:</Text>
      <TextInput
        value={password}
        onChangeText={setPassword}
        placeholder="비밀번호를 입력해주세요."
        secureTextEntry
        onSubmitEditing={handleSignUp}
        ref={passwordRef}
      />

        <View
            style={{
                ...ThemeStyle.basicButtonContainer
            }}
        >
            <Pressable
                style={{
                    ...ThemeStyle.basicButton
                }}
                onPress={handleSignUp}>
                    <Text>Sign Up</Text>
            </Pressable>
        </View>
    </View>
  );
};

export default SignUpPage;
