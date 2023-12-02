import React, { useCallback, useEffect, useRef, useState } from 'react';
import { SafeAreaView, View, Text, Animated, ActivityIndicator, Dimensions} from 'react-native';
import { useIsFocused } from '@react-navigation/native';

import Feed from './Feed';
import ProfList from './ProfList';
import ThemeStyle from './ThemeStyle';
import { fetchBoards } from '../Utils/FetchFunc';


const {width:SCREEN_WIDTH, height:SCREEN_HEIGHT} = Dimensions.get("window");

function Home({navigation}) {
    const isFocused = useIsFocused(); // 화면이 focus되었는지 확인하는 hook -> 글쓰기 후 화면이 focus되면 새로고침을 하기 위한 용도.
    const [isloading, setIsLoading] = useState(false);
    const [islessFeedNum, setIsLessFeedNum] = useState(false);    
    const [feedData, setFeedData] = useState([]);

    const scrollY = useRef(new Animated.Value(0)).current;
    const diffClamp = Animated.diffClamp(scrollY, 0, SCREEN_HEIGHT/3);
    const headerY = diffClamp.interpolate({
        inputRange: [0, SCREEN_HEIGHT/3],
        outputRange: [0, -SCREEN_HEIGHT/3],
    });
    const handleScroll = Animated.event([
            {
                nativeEvent: {
                    contentOffset: {y: scrollY}
                }
            }],
            {useNativeDriver: true},
    );

    const renderFeed = ({item}) => <Feed style={{flex: 1, marginVertical: 10}} item={item} />;

    // FakeAPI를 이용한 Pagination 무한 스크롤 테스트용
    const fetchData = async () => {
        if ((feedData.length==0 || feedData.length >= 20) && !islessFeedNum){
            setIsLoading(true);
            const res = await fetch("https://dummyjson.com/posts?limit=20&"+ "skip=" + feedData.length +"&select=title,body");
            const data = await res.json();
            setFeedData([...feedData, ...data.posts]);
            setIsLoading(false);
            if (data.posts.length < 20){
                setIsLessFeedNum(true);
            }
        };
    };

    


    useEffect(() => {
        // fetchData();
        // console.log(isFocused);
        fetchBoards().then((data) => {
            setFeedData(data);
        });
    }, [isFocused]); // useEffect의 의존성 배열로 넣어줘서, 변화가 생길 시 리렌더링하여 새로운 글을 받아온다.

    return (
        <SafeAreaView
            style={ThemeStyle.basicContainer}
        >
            <ProfList 
                navigation={navigation}
                style={{
                    ...ThemeStyle.basicContainer,
                    position: "absolute",
                    backgroundColor: "white",
                    zIndex: 9999,
                    elevation: 9999,
                    left: 0,
                    right: 0,
                    top: 0,
                    height: SCREEN_HEIGHT/3,
                    transform: [{translateY: headerY}],
                }}    
            />
            <View
                style={{flex: 3}}

            >
                {/* FakeAPI를 이용한 무한스크롤 테스트 피드 */}
                {/* <Animated.FlatList
                    style={{
                        flex: 1,
                        paddingTop: SCREEN_HEIGHT/3,
                    }}
                    contentContainerStyle={{
                        paddingBottom: SCREEN_HEIGHT/3,
                    }}
                    data={feedData}
                    keyExtractor={(_) => _.id}
                    renderItem={renderFeed}
                    onEndReached={() => {
                        if(!isloading){
                            fetchData();
                        }
                    }}
                    onEndReachedThreshold={0.6}
                    ListFooterComponent={isloading && <ActivityIndicator/>}
                    bounces={false}
                    onScroll={handleScroll}
                    scrollEventThrottle={16}
                /> */}

                <Animated.FlatList
                    style={{
                        flex: 1,
                        paddingTop: SCREEN_HEIGHT/3,
                    }}
                    contentContainerStyle={{
                        paddingBottom: SCREEN_HEIGHT/3,
                    }}
                    data={feedData}
                    keyExtractor={(_) => _.postId}
                    renderItem={renderFeed}
             
                />
            </View>
                
        </SafeAreaView>
    );
};

export default Home;
