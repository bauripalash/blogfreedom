package `in`.palashbauri.writefreelykt.jsonobjs

import kotlinx.serialization.Serializable

@Serializable
data class BlogCollection(val alias : String ,
                          val title : String,
                          val description : String,
                          val style_sheet: String,
                          val public : Boolean,
                          val views : Int
)