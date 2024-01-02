import { createStore } from "redux";
import rootReducer from "./reducer";

const authstore = createStore(rootReducer);

export default authstore;