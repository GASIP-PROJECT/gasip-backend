import HomeScreen from '../Components/HomeScreen';
import Prof from '../Components/Prof';
import WritePage from '../Components/WritePage';


import { createNativeStackNavigator } from '@react-navigation/native-stack';


const Stack = createNativeStackNavigator();

function HomeStack(){
    return(
        <Stack.Navigator
            screenOptions={({route}) => ({headerShown: false})}
        >
            <Stack.Screen name="HomeScreen" component={HomeScreen}
                options={{
                    headerShown: false,
                }}
            />
            <Stack.Screen name="Prof" component={Prof} 
                    options={{
                        headerShown: true,
                    }}
            />
            <Stack.Screen name="Write" component={WritePage}
                options={{
                    headerShown: false,
                }}
            />
        </Stack.Navigator>
    )
}

export default HomeStack;