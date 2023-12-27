import React, { useEffect } from 'react';
import { View, Text, StyleSheet } from 'react-native';

function Prof({navigation, route}){
    const {id, name} = route.params;
    
    useEffect(() => {
        navigation.setOptions({
            title: `Prof ${name}`,
        });
    }, []);
    
    return (
        <View
            style={{
                flex: 1,
                justifyContent: "center",
                alignItems: "center",
            }}
        >
            <Text>{id} {name}</Text>
        </View>
    );

};

export default Prof;
