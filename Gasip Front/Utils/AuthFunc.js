import * as SecureStore from 'expo-secure-store';
import { HOSTADDR, PORT } from './Configuration';


const storeToken = async (token) => { // 추후에 id로 저장하도록
    try {
        await SecureStore.setItemAsync('userToken', token);
    } catch (e) {
        console.log("storeToken Error: ", e);
    }
};

const removeToken = async () => { // 추후에 id로 제거하도록
    try {
        await SecureStore.deleteItemAsync('userToken');
    } catch (e) {
        console.log("removeToken Error: ", e);
    }
};

const restoreToken = async () => { // 추후에 id로 얻어오도록
    let restoreToken;  
    try {
        restoreToken = await SecureStore.getItemAsync('userToken');
        console.log("restoreToken: ", restoreToken);
    } catch (e) {
        console.log(e);
    }
    return restoreToken;
};

const postSignIn = async ({email, password}) => {
    const res = await fetch('http://'+HOSTADDR+':'+PORT+'/members/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        "email": email,
                        "password": password,
                    })
                });
    const data = await res.json();
    
    return data;
};

const postSignUp = async({name, email, password}) => {
    const res = await fetch('http://'+HOSTADDR+':'+PORT+'/members/signup', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        "name": name,
                        "email": email,
                        "password": password,
                    })
                });
    const data = await res.json();
    
    return data;
}

const getUserProfile = async (token) => {
    const res = await fetch('http://'+HOSTADDR+':'+PORT+'/members/mypage', {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer '+token,
                    },
                });
    const data = await res.json();
    
    return data;
}

export {storeToken, removeToken, restoreToken, postSignIn, getUserProfile, postSignUp}