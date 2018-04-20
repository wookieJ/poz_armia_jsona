
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
        fetch(url, {
            headers: {
                "User-Agent": "hackaton2017 (Client-Id 656cbe47-b17d-46c2-bae1-3222c8777d5b) Platform",
                'Api-Key': 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1MjQyOTEyMzMsImp0aSI6IjMyYmQzOTNjLWRhN2ItNDhiOS1hYzlkLTFjNjljZDg1OTBkNCIsImNsaWVudF9pZCI6ImE0MWY1YjJhLThlODctNGI4Yi1iNmZlLTc0Y2M3NjM3MjBkNyJ9.bVHSZCQ-_4DA_bR7UiIaXFXbZJKflePKkuUMYn0wSNcrrBGKZBaZDddxuyU5PF4nKKkuiGg8RBFY69tC4EjeNx2TExug6qwVQiAD5pfL-czDMwI9L1A5N7lc8-LIWbpffmTEXomyd6ezGWPehOpqRUYTGu3AImJJvptDgiXUmeBPgCs6zlm3gV856C--VuSO7AFtIoG6DbLtq_IOEPQt6Zdn-FFMxA-zu2BjeMDKUjuW_rFFEm0i5NGh72dYNPRmOObKa59Fvzbhl2uPwsBUPRgm5rFI8x8WeWdDzwY5feOPZuZJkYXQ6oeiLViOhnqIa6F763Zutk-hVeOr-PbN-Q',
                'Accept': 'application/vnd.allegro.public.v1+json'
            },
            mode: 'no-cors'
        })
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

