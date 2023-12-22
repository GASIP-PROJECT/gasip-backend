import { useDispatch } from "react-redux";
import { useMemo } from "react";
import { restoreToken, postSignUp, postSignIn, storeToken } from "./AuthFunc";


/*
Authentication Dispatch를 이용한 특정 Action들을 수행하는 Hook.
AuthDispatch는 다음과 같은 Action들을 수행한다.
    - signIn: 로그인을 수행한다. (현재는 dummy token을 발급한다.)
    - signOut: 로그아웃을 수행한다.
    - signUp: 회원가입을 수행한다. (현재는 dummy token을 발급한다.)
    - restoreToken: token을 복구한다. (현재는 dummy token을 발급한다.)
*/

const useAuthDispatch = () => {
    const dispatch = useDispatch();

    
    const authDispatch = useMemo(
        () => ({
            signIn: async (token) =>{
                await storeToken(token);
                dispatch({ 
                    type: "SIGN_IN",
                    token: token
                });
            },
            signOut: () => dispatch({type: "SIGN_OUT"}),
            signUp: async (token) => {
                await storeToken(token);
                dispatch({
                    type: "SIGN_IN",
                    token: token
                });

                
            },
            restoreToken: async () => {
                restoreToken().then((res) => {
                    dispatch({
                        type: "RESTORE_TOKEN",
                        token: res
                    });
                });
            }
        }),
        []
    );

    return authDispatch;
}

export default useAuthDispatch;