
export function productsHasErrored(bool) {
    return {
        type: 'PRODUCTS_HAS_ERRORED',
        hasErrored: true
    }
}

export function productsIsLoading(bool){
    return{
        type:'PRODUCTS_IS_LOADING',
        isLoading: bool
    }
}

export function productsFetchDataSuccess(products){
    return{
        type:'PRODUCTS_FETCH_DATA_SUCCESS',
        products
    }
}

export function productsFetchData(url) {
    return (dispatch) => {
        dispatch(productsIsLoading(true))
        fetch(url)
            .then((response) => {
                if (!response.ok){
                    throw Error(response.statusText)
                }
                dispatch(productsIsLoading(false))
                return response
            })
            .then((response) => response.json())
            .then((products) => dispatch(productsFetchDataSuccess(products)))
            .catch(() => dispatch(productsHasErrored(true)))
    }
}

