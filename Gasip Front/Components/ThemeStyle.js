import { StyleSheet, StatusBar } from "react-native";

const ThemeStyle = StyleSheet.create({
    basicContainer: {
        flex: 1,
        backgroundColor: '#fff',
        paddingTop: StatusBar.currentHeight,
        padding: 10,
    },
    basicHeaderContainer: {
        flex: 1,
        backgroundColor: '#fff',
        padding: 10,
    },
    basicButtonContainer:{
        flexDirection: 'row',
        justifyContent: 'space-around',
        alignItems: 'center',
    },
    basicButton: {
        flex: 1,
        flexGrow: 1,
        alignItems: 'center',
        borderRadius: 5,
        margin: 5,
        paddingVertical: 10,
        backgroundColor: 'skyblue',
    },
    focusedTabLabel: {
        fontSize: 15,
        textAlign: 'center',
        color: 'dodgerblue',
    },
    unfocusedTabLabel: {
        fontSize: 15,
        textAlign: 'center',
        color: 'gray',
    },
});

export default ThemeStyle;