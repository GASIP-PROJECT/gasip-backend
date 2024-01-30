import React from "react";
import { View, Text, TextInput } from "react-native";
import { BottomSheetModal } from "@gorhom/bottom-sheet";

const CommentModal = ({bottomSheetModalRef, snapPoints, handleSheetChanges}) => {
    <BottomSheetModal
        ref={bottomSheetModalRef}
        index={0}
        snapPoints={snapPoints}
        onChange={handleSheetChanges}
    >
        <View
            style={{
                flex: 1,
                margin: 20,
                backgroundColor: "white",
            }}
        >
            <View>
                <Text>댓글 창입니다.</Text>
            </View>
            {/* TODO: 댓글 입력창이 모달의 아래 고정되도록 수정 */}
            <TextInput
                placeholder="댓글을 입력하세요."
                style={{
                    height: 40,
                    borderColor: 'gray',
                    borderWidth: 0.5,
                    borderRadius: 5,
                    padding: 10,
                    textAlignVertical: "top",
                    fontSize: 20,
                    position: "absolute",
                    bottom: 0,
                    
                }}
            />
        </View>
    </BottomSheetModal>
};

export default CommentModal;