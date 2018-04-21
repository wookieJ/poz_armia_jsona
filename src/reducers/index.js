import { combineReducers } from "redux";

import { products, productsHasErrored, productsIsLoading} from './products';
import { details, detailsHasErrored, detailsIsLoading } from "./details";

export default combineReducers({
    products,
    productsHasErrored,
    productsIsLoading,
    details,
    detailsHasErrored,
    detailsIsLoading
})