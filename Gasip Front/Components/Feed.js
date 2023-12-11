import React, {useState} from 'react';
import { View, Text, Dimensions, StyleSheet, Pressable, Animated } from 'react-native';
import { AntDesign, Feather, Octicons } from '@expo/vector-icons';

const {width:SCREEN_WIDTH} = Dimensions.get("window");

const Feed = ({style, item}) => {
    const {postId, title, content, clickCount, likeCount, professor} = item;

    const [likes, setLikes] = useState(likeCount);
    const [islikes, setIsLikes] = useState(likeCount !== 0 ? true : false);


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
            {/* 댓글 섹션 */}
            

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