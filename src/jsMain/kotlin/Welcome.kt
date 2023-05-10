import react.FC
import react.Props
import emotion.react.css
import mu.KotlinLogging
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.redux.useSelector
import react.useState
import reducers.QueryState
import web.cssom.px
import web.cssom.rgb
import web.html.InputType

external interface WelcomeProps : Props {
    var name: String
}

private val logger = KotlinLogging.logger {  }


fun createFunction(): (QueryState) -> String? {
    return js("function getQueryString(queryResult){ return queryResult.currentQuery;}")
}

val Welcome = FC<WelcomeProps> { props ->
    val accessViaJS = { queryState: QueryState ->
        js("queryState.currentQuery").unsafeCast<String?>()
    }

    logger.info {
        "JS works in the code: ${accessViaJS(QueryState(currentQuery = "Yay, JS!"))}"
    }

    val accessViaKotlin = { queryState: QueryState ->
        queryState.currentQuery
    }


    logger.info {
        "Kotlin works in the code: ${accessViaKotlin(QueryState(currentQuery = "Yay, Kotlin!"))}"
    }


    val jsSelectorResult1 = useSelector(accessViaJS)
    logger.info { "JS selector1 works: ${jsSelectorResult1}" }

    val jsSelectorResult2 = useSelector(createFunction())
    logger.info { "JS selector2 works: ${jsSelectorResult2}" }


    val kotlinSelectorResult = useSelector(accessViaKotlin)
    logger.info { "kotlin selector works: ${kotlinSelectorResult}" }

    var name by useState(props.name)
    div {
        css {
            padding = 5.px
            backgroundColor = rgb(8, 97, 22)
            color = rgb(56, 246, 137)
        }
        +"Hello, ${name}: ${kotlinSelectorResult ?: "###"}"
    }
    input {
        css {
            marginTop = 5.px
            marginBottom = 5.px
            fontSize = 14.px
        }
        type = InputType.text
        if (kotlinSelectorResult != null){
            value = kotlinSelectorResult
        }
//        onChange = { event ->
//            dispatch(SetQuery(event.target.value))
//        }
    }
}