import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Pressable, Modal, Dimensions, Animated, StyleSheet } from 'react-native';

import { fetchColleges, fetchMajors, fetchProf } from '../Utils/FetchFunc';
import { StatusBar } from 'expo-status-bar';

const {width:SCREEN_WIDTH, height:SCREEN_HEIGHT} = Dimensions.get("window");
const MODAL_HEIGHT = SCREEN_HEIGHT/3;

function ProfList ({navigation, style}){
    const [majors, setMajors] = useState([]);
    const [selectedMajor, setMajor] = useState("");
    const [colleges, setColleges] = useState([]);
    const [prof, setProf] = useState([]);
    const [modalVisible, setModalVisible] = useState(false);
    const [statusBarHidden, setStatusBarHidden] = useState(false);
    const pressAnimated = new Animated.Value(1);
    const pressIn = () => {
        Animated.timing(pressAnimated, {
            toValue: 0.9,
            duration: 100,
            useNativeDriver: true,
        }).start();
    };

    const pressOut = () => {
        Animated.timing(pressAnimated, {
            toValue: 1,
            duration: 100,
            useNativeDriver: true,
        }).start();
    };

    

   
    
    useEffect(() => {
        fetchColleges().then(res => {
            setColleges(res);
            fetchMajors(res[0].college).then(res => {
                setMajors(res);
                setMajor(res[0].name);
                fetchProf(res[0].major_ID).then(res => {
                    setProf(res);
                });
            });
        });
        console.log("Rendering");
    }, []);

    const renderItem = ({ item }) => (
        <Pressable
            onPress={() => {
                navigation.navigate('Prof', {
                    id: item.prof_ID,
                    name: item.prof_name,
                    });
                }
            }
            style={{
                borderBottomWidth: 1,
                padding: 10,
                alignItems: "center",
                justifyContent: "center",
            }}
            
        >
            <Text>{item.prof_name}</Text>

        </Pressable>
    );

    return (
        <Animated.View
            style={style}
        >
            <Modal
                animationType="fade"
                transparent={true}
                visible={modalVisible}
                onRequestClose={() => {
                    setModalVisible(false);
                    setStatusBarHidden(!statusBarHidden);
                }}
            >
                <Pressable
                    onPress={() => {
                        setModalVisible(false);
                    }}
                >
                    <View
                        style={{
                            height: SCREEN_HEIGHT,
                            width: SCREEN_WIDTH,
                            backgroundColor: "rgba(0,0,0,0.4)",
                        }}
                    >
                    </View>
                </Pressable>
                <View
                        style={{
                            flexDirection: "row",
                            height: MODAL_HEIGHT,
                            width: SCREEN_WIDTH,
                            alignItems: "center",
                            backgroundColor: "white",
                            position: "absolute",
                            top: SCREEN_HEIGHT*3/4,
                            borderTopLeftRadius: 20,
                            borderTopRightRadius: 20,
                            shadowColor: "black",
                            shadowOffset: {width: 0, height: 2},
                            shadowOpacity: 0.25,
                            shadowRadius: 4,
                        }}
                    >
                        <View
                            style={styles.collegeContainer}
                        >
                            <Text
                                style={{
                                    fontSize: 15,
                                    fontWeight: "bold",
                                    textAlign: "center",
                                    padding: 4,
                                    margin: 3,
                                }}
                            >단과 대학</Text>
                            <FlatList
                                showsVerticalScrollIndicator={false}
                                data={colleges}
                                renderItem={({item}) => {
                                    return(
                                        <Pressable
                                            onPress={() => {
                                                fetchMajors(item.college).then(res => {
                                                    setMajors(res);
                                                });
                                            }}
                                            style={{
                                                borderBottomWidth: 0.8,
                                                padding: 10,
                                                alignItems: "center",
                                                justifyContent: "center",
                                                borderColor: "gray",
                                            }}
                                        >
                                            <Text>{item.collegeName}</Text>
                                        </Pressable>
                                    );
                                }}
                                key={colleges.college}
                            />
                        </View>
                        
                        <View
                            style={{
                                width: 1.5,
                                height: MODAL_HEIGHT,
                                backgroundColor: "black",
                            }}
                        />
                        <View
                            style={styles.specificMajorContainer}
                        >
                            <Text
                                style={{
                                    fontSize: 15,
                                    fontWeight: "bold",
                                    textAlign: "center",
                                    padding: 4,
                                    margin: 3,
                                }}
                            >
                                세부 전공
                            </Text>
                            <FlatList
                                showsVerticalScrollIndicator={false}
                                data={majors}
                                renderItem={({item}) => {
                                    return(
                                        <Pressable
                                            onPress={() => {
                                                setMajor(item.name);
                                                setModalVisible(false);
                                                fetchProf(item.major_ID).then(res => {
                                                    setProf(res);
                                                });
                                            }}
                                            style={{
                                                borderBottomWidth: 1,
                                                padding: 10,
                                                alignItems: "center",
                                                justifyContent: "center",
                                            }}
                                        >
                                            <Text>{item.name}</Text>
                                        </Pressable>
                                    );
                                }}
                                key={majors.major_ID}    
                            />

                        </View>
                    </View>
            </Modal>
            <View
                style={{
                    flex:1,
                    justifyContent: "space-between",
                    flexDirection: "row",
                }}
            >
                <Text
                    style={{
                        fontSize: 20,
                        fontWeight: "bold",
                        textAlign: "center",
                        padding: 5,
                        margin: 5,
                    }}
                >{selectedMajor}
                </Text>
                <Pressable
                    onPress={() => {
                        setModalVisible(true);
                        setStatusBarHidden(!statusBarHidden);
                    }}
                    onPressIn={pressIn}
                    onPressOut={pressOut}
                >
                    <Animated.View
                        style={{
                            backgroundColor: "white",
                            borderRadius: 10,
                            borderWidth: 1,
                            padding: 5,
                            margin: 5,
                            shadowColor: "black",
                            shadowOffset: {width: 0, height: 2},
                            shadowOpacity: 0.25,
                            shadowRadius: 4,
                            elevation: 5,
                            transform: [
                                {
                                    scale: pressAnimated,
                                }
                            ]
                        }}
                    >
                        <Text
                        style={{
                            fontSize: 18,
                            fontWeight: "bold",
                            textAlign: "center",
                        }}
                        >Change
                        </Text>
                    </Animated.View>
                    
                </Pressable>
            </View>
            <View
                style={{
                    flex:4,
                }}
            >
                <FlatList
                    data={prof}
                    renderItem={renderItem}
                    key={prof.prof_ID}
                    
                />
            </View>
            <View
                style={{
                    flex: 1,
                }}
            >
                <Pressable
                    style={{
                        backgroundColor: "white",
                        borderRadius: 10,
                        borderWidth: 1,
                        padding: 5,
                        margin: 5,
                        shadowColor: "black",
                        shadowOffset: {width: 0, height: 2},
                        shadowOpacity: 0.25,
                        shadowRadius: 4,
                        elevation: 5,
                    }}
                    onPress={() => {
                        navigation.navigate('Write', {
                            major: selectedMajor,
                        });
                    }}
                >
                    <Text>글쓰기</Text>
                </Pressable>
            </View>
        </Animated.View>
    );
};

export default ProfList;


const styles = StyleSheet.create({
    collegeContainer: {
        flex: 2,
        alignItems: 'center',
        justifyContent: 'center',
    },
    specificMajorContainer: {
        flex: 3,
        alignItems: 'center',
        justifyContent: 'center',
    },
});