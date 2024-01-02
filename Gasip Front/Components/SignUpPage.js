import React, {useContext, useRef } from 'react';
import { View, Text, TextInput, Button, Pressable } from 'react-native';
import * as SecureStore from 'expo-secure-store';

import useAuthDispatch from '../Utils/AuthHooks';
import { postSignUp, postSignIn } from '../Utils/AuthFunc';

import ThemeStyle from './ThemeStyle';


const SignUpPage = ({navigation, route}) => {
  const [name, setName] = React.useState('');
  const [email, setEmail] = React.useState('');
  const [password_first, setPasswordFirst] = React.useState('');
  const [password_last, setPasswordLast] = React.useState('');
  const [isValid, setIsValid] = React.useState(true);
  const [isResValid, setIsResValid] = React.useState(true);

  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const passwordRef2 = useRef(null);

  const authDispatch = useAuthDispatch();

  const handleSignUp = async () => {
    // Perform sign up logic here
    console.log('Signing up...');

    if (password_first !== password_last){
        setIsValid(false);
        console.log("Password is not valid");
        return;
    }
    const res = await postSignUp({name: name, email: email, password: password_last});

    if (res.statusCode){
        console.log("SignUp Failed");
        setIsResValid(false);
    } else {
        console.log("SignUp Success");
        console.log('Email: ', email, 'Password: ', password_last);
        await postSignIn({email: email, password: password_last}) // 회원가입 후 따로 로그인이 필요하지 않도록, 로그인 처리 진행
        .then(async (res) => {
                                if (res.access_token) {
                                    console.log("SignIn Success");
                                    await  authDispatch.signUp(res.access_token);

                                } else {
                                    console.log("SignIn Failed");
                                }
                            });
    };
};

  return (
    <View style={{
        ...ThemeStyle.basicContainer,
        justifyContent: 'center',
        padding: 50,
    }}>
        <Text>Name</Text>
        <TextInput
            value={name}
            onChangeText={setName}
            placeholder="이름을 입력해주세요."
            autoFocus
            blurOnSubmit={false}
            onSubmitEditing={() => emailRef.current.focus()}
        />

      <Text>Email</Text>
      <TextInput
        value={email}
        onChangeText={setEmail}
        placeholder="이메일을 입력해주세요."
        blurOnSubmit={false}
        ref={emailRef}
        onSubmitEditing={() => passwordRef.current.focus()}
      />

      <Text>Password</Text>
      <TextInput
        value={password_first}
        onChangeText={setPasswordFirst}
        placeholder="비밀번호를 입력해주세요."
        secureTextEntry
        onSubmitEditing={() => passwordRef2.current.focus()}
        ref={passwordRef}
      />
      <Text>Password Validation</Text>
      <TextInput
        value={password_last}
        onChangeText={text => {
            setPasswordLast(text);
            text === password_first ? setIsValid(true) : setIsValid(false);
        }}
        placeholder="비밀번호를 다시 입력해주세요."
        secureTextEntry
        onSubmitEditing={handleSignUp}
        ref={passwordRef2}
        />

        {!isValid && <Text style={{ color: "red", fontSize: 13 }}>비밀번호가 일치하지 않습니다.</Text>}
        {!isResValid && <Text style={{ color: "red", fontSize: 13 }}>회원가입이 정상적으로 처리되지 않았습니다.</Text>}

        <View
            style={{
                ...ThemeStyle.basicButtonContainer
            }}
        >
            <Pressable
                style={{
                    ...ThemeStyle.basicButton,
                    opacity: isValid ? 1 : 0.5,
                    }}
                onPress={handleSignUp}
                disabled={!isValid}
                >
                    <Text>Sign Up</Text>
            </Pressable>
        </View>
    </View>
  );
};

export default SignUpPage;
