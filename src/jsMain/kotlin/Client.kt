import reducers.QueryState
import reducers.combinedReducers

import react.create
import react.dom.client.createRoot
import react.redux.Provider
import redux.*
import web.dom.document

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    val appStore = createStore<QueryState, RAction, dynamic>(
        combinedReducers(),
        QueryState(),
        rEnhancer()
//    compose(
//        rEnhancer(),
//        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__();else(function(f){return f;});")
//    )
    )

    val welcome =  Provider.create {
        store = appStore
        Welcome {
            name = "Test Application."
        }
    }


    createRoot(container).render(welcome)
}