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


val Welcome = FC<WelcomeProps> { props ->

    val lambdaFunctionToAccessTheQueryState = { queryState: QueryState ->
        queryState.currentQuery
    }

    logger.info {
        "It works in the code: ${lambdaFunctionToAccessTheQueryState(QueryState())}"
    }

    val query = useSelector(lambdaFunctionToAccessTheQueryState)

    logger.info { "Use selector works: ${query}" }

//    val dispatch = useDispatch<RAction, dynamic>()

    var name by useState(props.name)
    div {
        css {
            padding = 5.px
            backgroundColor = rgb(8, 97, 22)
            color = rgb(56, 246, 137)
        }
        +"Hello, ${name}: ${query ?: "###"}"
    }
    input {
        css {
            marginTop = 5.px
            marginBottom = 5.px
            fontSize = 14.px
        }
        type = InputType.text
        if (query != null){
            value = query
        }
//        onChange = { event ->
//            dispatch(SetQuery(event.target.value))
//        }
    }
}