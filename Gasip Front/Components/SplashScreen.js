import {View, ActivityIndicator} from 'react-native';
import ThemeStyle from './ThemeStyle';



export default SplashScreen = () => {
    return (
        <View style={ThemeStyle.basicContainer}>
            <ActivityIndicator size="large" color="#00ff00" />
        </View>
    );
};