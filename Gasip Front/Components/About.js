import React from 'react';
import { View, Text, Button } from 'react-native';
import ThemeStyle from './ThemeStyle';

function About({navigation}) {
    return (
        <View
            style={ThemeStyle.basicContainer}
        >
            <Text>Welcome to my app!</Text>
            <Button
                title="Go to Home"
                onPress={() => navigation.navigate('Home')}
            />
        </View>
    );
};

export default About;
