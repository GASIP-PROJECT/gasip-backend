import React, {useContext} from 'react';
import { View, Text, TextInput, Button } from 'react-native';
import * as SecureStore from 'expo-secure-store';

import ThemeStyle from './ThemeStyle';

const SignUpPage = ({navigation, route}) => {
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const { signUp } = route.params.authContext;
  
  const storeToken = async (token) => {
        try {
        await SecureStore.setItemAsync('userToken', token);
        } catch (e) {
        console.log("storeToken Error: ", e);
        }
    };

  const handleSignUp = () => {
    // Perform sign up logic here
    console.log('Signing up...');
    console.log('Email: ', email, 'Password: ', password);
    storeToken("USER_TOKEN");
    signUp("USER_TOKEN");
};

  return (
    <View style={{
        ...ThemeStyle.basicContainer,
        justifyContent: 'center',
        padding: 50,
    }}>
      <Text>Email:</Text>
      <TextInput
        value={email}
        onChangeText={setEmail}
        placeholder="이메일을 입력해주세요."
      />

      <Text>Password:</Text>
      <TextInput
        value={password}
        onChangeText={setPassword}
        placeholder="비밀번호를 입력해주세요."
        secureTextEntry
      />

      <Button title="Sign Up" onPress={handleSignUp} />
    </View>
  );
};

export default SignUpPage;
