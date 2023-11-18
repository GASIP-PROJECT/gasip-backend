import React from 'react';
import { Text, View } from 'react-native';

const Header = (props) => {
    const{style={}} = props;

    return (
        <View
            style={style}
        >
            <Text>Hello, world!</Text>
        </View>
    );
};

export default Header;
