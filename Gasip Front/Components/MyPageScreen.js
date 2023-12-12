import { StyleSheet, Text, View, Button } from 'react-native';
import ThemeStyle from './ThemeStyle';
import * as SecureStore from 'expo-secure-store';



function MyPageScreen({navigation, route}) {
    const { signOut } = route.params.authContext;
    const removeToken = async () => {
        try {
            await SecureStore.deleteItemAsync('userToken');
        } catch (e) {
            console.log("removeToken Error: ", e);
        }
    };
    const handleSignOut = () => {
        // Perform sign out logic here
        console.log('Signing out...');
        removeToken();
        signOut();

    };
    return (
        <View style={ThemeStyle.basicContainer}>
            <View>
                <Text>MyPageScreen</Text>
                <Button title="Sign Out" onPress={handleSignOut} />
            </View>
        </View>
    );
}


export default MyPageScreen;