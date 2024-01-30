import React, {useCallback, useEffect, useRef, useState} from 'react';
import { View, Text, Dimensions, StyleSheet, Pressable, Animated, TextInput, FlatList, ActivityIndicator } from 'react-native';

import { AntDesign, Feather, Octicons } from '@expo/vector-icons';

import { useSafeAreaInsets } from 'react-native-safe-area-context';

import  { BottomSheetModal, BottomSheetFooter, BottomSheetHandle} from '@gorhom/bottom-sheet';

import { fetchComments, writeComments } from '../Utils/FetchFunc';

const Feed = ({style, item}) => {
    const {postId, content, clickCount, likeCount, profId} = item;
    const {top: topSafeArea, bottom: bottomSafeArea} = useSafeAreaInsets();

    const [likes, setLikes] = useState(likeCount);
    const [islikes, setIsLikes] = useState(likeCount !== 0 ? true : false);
    const [comments, setComments] = useState([]);
    const [isCommentsLoading, setIsCommentsLoading] = useState(true);
    const writerCommentRef = useRef("");

    const handleWriteComment = () => {
        writeComments({boardId: postId, content: writerCommentRef.current}).then((data) => {
            console.log(data);
            setComments([...comments, data]);
        });
        writerCommentRef.current = "";

    }

    const handleCommentChange = (text) => {
        writerCommentRef.current = text;
    }

    const bottomSheetModalRef = useRef(BottomSheetModal);
    const snapPoints = React.useMemo(() => ['75%', '100%'], []);
    const handlePresentModalPress = useCallback(() => {
        bottomSheetModalRef.current ? bottomSheetModalRef.current.present() : bottomSheetModalRef.current;
        fetchComments({boardId: postId}).then((data) => {
            setComments(data);
            setIsCommentsLoading(false);
            console.log(data);
        });
    }, []);

    const handleSheetChanges = useCallback((index) => {
        console.log('handleSheetChanges', index);
    }, []);

    const renderBackground = useCallback((props) => (
        <BottomSheetBackground {...props}/>
    ), []);

    const renderFooter = useCallback((props) => (
        <BottomSheetFooter {...props}>
            <View style={{
                alignItems: 'center',
                flexDirection: 'row',
                backgroundColor: "lightgray"
            }}>
                <TextInput
                    placeholder="댓글을 입력하세요."
                    style={{
                        flex: 1,
                        height: 40,
                        padding: 10,
                        textAlignVertical: "top",
                        fontSize: 15,  
                    }}
                    defaultValue={writerCommentRef.current}
                    onChangeText={handleCommentChange}
                />
                <Pressable
                    style={{
                        width: 50,
                        height: 40,
                        alignItems: "center",
                        justifyContent: "center",
                    }}
                    onPress={() => (writerCommentRef.current ? handleWriteComment() : null)}
                >
                    <Text
                        style={{
                            color: writerCommentRef.current ? "red" : "gray",
                        }}
                    >전송</Text>
                </Pressable>
            </View>
        </BottomSheetFooter>
            
    ), [comments]);

    const BottomSheetBackground = ({style}) => {
        return (
            <View
                style={{
                    backgroundColor: "white",
                    borderRadius: 15,
                    borderWidth: 0.5,
                    borderColor: "gray",  
                    ...style
                }}
            />
        );
    }


    return (
        <View
            style={style}
        >
            {/* 사용자 ID에 의한 사용자 정보 들어갈 예정(지금은 postId로 대체)*/}
            <Text style={{
                flex: 1,
                fontSize: 25,
                fontWeight: "bold"
            }}>
            {postId}
            </Text>

            {/* 본문 섹션 */}
            <View
                style={{
                    flex: 4,
                }}
            >
                <Text>{content}</Text>
            </View>

            {/* 피드 정보 섹션 */}
            <Animated.View
                style={styles.feedInformationContainer}
            >
                <Pressable
                    style={styles.feedInformation}
                    onPress={() => {
                        setIsLikes(!islikes);
                        islikes ? setLikes(likes-1) : setLikes(likes+1);
                    }}
                >   
                    {
                        islikes ? <AntDesign name="heart" size={22} color="red" /> : <AntDesign name="hearto" size={22} color="black" />
                    }    
                    <Text
                        style={styles.feedInformationText}
                    >{likes !== 0 ? likes : ""}</Text>
                </Pressable>
                <Pressable
                    style={styles.feedInformation}
                    onPress={handlePresentModalPress}
                >
                    <Feather name="message-circle" size={22} color="black" />
                    <Text
                        style={styles.feedInformationText}
                    >Comment</Text>
                </Pressable>    
                <Pressable
                    style={styles.feedInformation}
                >
                    <Octicons name="paper-airplane" size={22} color="black" />
                    <Text
                        style={styles.feedInformationText}
                    >Share</Text>
                </Pressable>
            </Animated.View>
            {/* 댓글 모달 섹션 */}
            <BottomSheetModal
                ref={bottomSheetModalRef}
                index={1}
                snapPoints={snapPoints}
                onChange={handleSheetChanges}
                backgroundComponent={renderBackground}
                footerComponent={renderFooter}
                topInset={topSafeArea}
                keyboardBehavior='extend'
            >
                <View
                    style={{
                        flex: 1,
                        margin: 20,
                        backgroundColor: "white",
                    }}
                >
                {/* 댓글 로딩 중일 때 */}
                {isCommentsLoading ? <ActivityIndicator size="large" color="#0000ff" /> :                 
                <FlatList
                    data={comments}
                    renderItem={({item}) => (
                        <View
                            style={{
                                flexDirection: "row",
                                flex: 1,
                                justifyContent: "flex-start",
                                columnGap: 10,
                                padding: 10,
                            }}
                        >
                            <Text>{item.content}</Text>
                        </View>
                    )}
                />
}
               
                    
                </View>
            </BottomSheetModal>
            
        </View>
    
    

    );
};

export default Feed;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "white",
    },
    feedInformationContainer: {
        flexDirection: "row",
        flex: 1,
        justifyContent: "flex-start",
        columnGap: 10
    },
    feedInformation: {
        flexDirection: "row",
        columnGap: 4,
        padding: 5,
        alignItems: "center",
        justifyContent: "center",
    },
    feedInformationText: {
        fontSize: 14,
        fontWeight: "600",
    },
});