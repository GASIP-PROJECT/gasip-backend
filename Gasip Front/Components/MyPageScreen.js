import { StyleSheet, Text, View } from 'react-native';
import ThemeStyle from './ThemeStyle';


function MyPageScreen({navigation}) {
    return (
        <View style={ThemeStyle.basicContainer}>
            <Text>MyPageScreen</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
    }
});

export default MyPageScreen;