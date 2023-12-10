import { createNativeStackNavigator } from '@react-navigation/native-stack';

import MyPageScreen from '../Components/MyPageScreen';

const Stack = createNativeStackNavigator();

function HomeStack(){
    return(
        <Stack.Navigator
            screenOptions={({route}) => ({headerShown: false})}
        >
            <Stack.Screen name="MyPageScreen" component={MyPageScreen}
                options={{
                    headerShown: false,
                }}
            />
            
        </Stack.Navigator>
    )
}

export default HomeStack;