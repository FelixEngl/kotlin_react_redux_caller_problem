package reducers

import Ticket
import WordCloud
import redux.RAction


data class QueryState(
    val currentQuery: String? = null,
    val currentTicket: Ticket = Ticket.NULL,
    val wordClouds: Array<WordCloud?> = emptyArray(),
)

fun combinedReducers() = combineReducers(
    mapOf(
        QueryState::currentQuery to ::currentQuery,
        QueryState::currentTicket to ::currentTicket,
        QueryState::wordClouds to ::wordClouds
    )
)

fun currentQuery(state: String? = null, action: RAction): String? =
    when(action){
        UnSetQuery -> null
        is SetQuery -> action.query
        else -> state
    }


fun currentTicket(ticket: Ticket? = Ticket.NULL, action: RAction): Ticket =
    when(action){
        UnSetTicket -> Ticket.NULL
        is SetTicket -> action.ticket
        else -> ticket ?: Ticket.NULL
    }

fun wordClouds(wordClouds: Array<WordCloud?> = emptyArray(), action: RAction): Array<WordCloud?> =
    when(action){
        UnSetAllWordClouds -> emptyArray()
        is UnSetWordCloud -> {
            if (action.ordinal in wordClouds.indices){
                wordClouds[action.ordinal] = null
            }
            wordClouds
        }
        is SetWordCloud -> {
            val new = if (action.ordinal !in wordClouds.indices){
                wordClouds.copyOf(action.ordinal + 1)
            } else wordClouds

            new[action.ordinal] = action.wordCloud

            new
        }
        is GrowArrayWordCloud -> {
            if (action.newSize > wordClouds.size){
                wordClouds.copyOf(action.newSize)
            } else {
                wordClouds
            }
        }
        else -> wordClouds
    }


class SetQuery(val query: String): RAction
object UnSetQuery: RAction


class SetTicket(val ticket: Ticket): RAction
object UnSetTicket: RAction


object UnSetAllWordClouds: RAction
class GrowArrayWordCloud(val newSize: Int): RAction
class UnSetWordCloud(val ordinal: Int): RAction
class SetWordCloud(val ordinal: Int, val wordCloud: WordCloud): RAction