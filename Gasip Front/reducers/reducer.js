import { combineReducers } from "redux";

import authReducer from "./Authentication";

const rootReducer = combineReducers({
    authReducer
});

export default rootReducer;