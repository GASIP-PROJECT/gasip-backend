import { StyleSheet, StatusBar } from "react-native";

const ThemeStyle = StyleSheet.create({
    basicContainer: {
        flex: 1,
        backgroundColor: '#fff',
        paddingTop: StatusBar.currentHeight,
    },
});

export default ThemeStyle;