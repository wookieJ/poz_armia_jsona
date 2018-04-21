export function detailsHasErrored(state = false, action) {
    switch (action.type) {
        case 'DETAILS_HAS_ERRORED':
            return action.detailsHasErrored;
        default:
            return state;
    }
}

export function detailsIsLoading(state = false, action){
    switch (action.type) {
        case 'DETAILS_IS_LOADING':
            return action.detailsIsLoading
        default:
            return state;
    }
}

export function details (state = [], action){
    switch (action.type) {
        case 'DETAILS_FETCH_DATA_SUCCESS':
            return action.details
        default:
            return state
    }
} 