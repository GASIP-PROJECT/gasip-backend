import React, { useState } from 'react';
import { View, TextInput, Button, Text, Pressable } from 'react-native';
import ThemeStyle from './ThemeStyle';
import { writeBoard } from '../Utils/FetchFunc';

function WritePage({navigation}){
    // const [title, setTitle] = useState('');
    const [text, setText] = useState('');


    // const handleTitleChange = (newTitle) => {
    //     setTitle(newTitle);
    // };
    const handleTextChange = (newText) => {
        setText(newText);
    };

    const handleButtonPress = () => {
        console.log(text);
        // Add code to save the text to a database or file
        writeBoard({content: text, profId: 2}).then((res) => {console.log(res);});
        navigation.goBack();
    };

    return (
        <View
            style={{
                ...ThemeStyle.basicContainer,
                justifyContent: "flex-start",
            }}
        >
        
            <View
                style={{
                    flexDirection: "row",
                    justifyContent: "space-between",
                    alignItems: "center",
                }}
            >
                <Text
                    style={{
                        fontSize: 20,
                        fontWeight: "bold",
                        padding: 5,
                        textAlign: "center",
                    }}
                >
                    내용
                </Text>
                <Text
                    style={{
                        fontSize: 15,
                        fontWeight: text.length <= 500 ? "normal" : "bold",
                        padding: 5,
                        textAlign: "center",
                        color: text.length <= 500 ? "gray" : "red",
                    }}
                >
                    {text.length <= 500 ? text.length + "/500" :  text.length + "/500 ※500자 이내로 입력해주세요."}
                </Text>
            </View>

            <TextInput
                style={{
                    height: 300,
                    borderColor: 'gray',
                    borderWidth: 0.5,
                    borderRadius: 5,
                    padding: 10,
                    textAlignVertical: "top",
                    fontSize: 20,
                }}
                placeholder="내용을 입력하세요."
                onChangeText={handleTextChange}
                value={text}
                multiline={true}
                numberOfLines={13}
            />
            
            <Pressable
                onPress={handleButtonPress}
                style={{
                    backgroundColor: (0 < text.length && text.length <= 500) ? "gray" : "lightgray",
                    margin: 5,
                    borderWidth: 0.5,
                    borderRadius: 5,
                    borderColor: "gray",
                    height: 35,
                }}
                justifyContent="center"
                disabled={(0 < text.length && text.length <= 500) ? false : true}
            >
                <Text
                    style={{
                        textAlign: "center",
                        color: "white",
                    }}
                >
                    글쓰기
                </Text>
            </Pressable>
        </View>
      );
};

export default WritePage;
