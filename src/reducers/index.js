import { combineReducers } from "redux";

import { products, productsHasErrored, productsIsLoading} from './products';

export default combineReducers({
    products,
    productsHasErrored,
    productsIsLoading
})