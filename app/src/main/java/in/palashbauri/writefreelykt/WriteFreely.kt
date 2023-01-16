package `in`.palashbauri.writefreelykt

import `in`.palashbauri.writefreelykt.errors.*
import `in`.palashbauri.writefreelykt.jsonobjs.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

//url: String = "" , AuthKey : String = "" , Username : String = "", Err : String = ""
class WriteFreely {
    var Url = ""
    var AuthKey = ""
    var Username = ""

    fun Logout(): Boolean {

        val client = OkHttpClient()
        val apiUrl = Url + Endpoints.Logout
        val req = Request.Builder()
            .url(apiUrl)
            .header("Authorization", "Token $AuthKey")
            .delete()
            .build()
        client.newCall(req).execute().use { resp ->

            when(resp.code){
                204-> return true
                404 -> throw InvalidToken()
                400 -> throw LogoutMissingToken()
            }

            if (!resp.isSuccessful){
                throw ResponseFailed()
            }



            return false
        }
    }

    fun GetAllUserPosts() : Array<PostObj> {
        val apiUrl = Url + Endpoints.GetUserAllPosts
        val JsonBuilder = Json{ ignoreUnknownKeys = true }
        val client = OkHttpClient()
        val request = Request.Builder()
                    .url(apiUrl)
            .header("Authorization" , "Token $AuthKey")
            .header("Content-Type" , "application/json")
            .get()
            .build()

        client.newCall(request).execute().use { resp ->
            if (!resp.isSuccessful) throw ResponseFailed()
            if (resp.code == 401) throw InvalidToken()
            if (resp.body == null) throw EmptyResponse()

            val respObj = JsonBuilder.decodeFromString<AllPostsObj>(resp.body!!.string())
            return respObj.data
        }
    }



}




fun WfLogin(url: String, username: String, password: String): WriteFreely {
    var result = WriteFreely()
    result.Url = url
    result.Username = username
    val JsonBuilder = Json { ignoreUnknownKeys = true }
    val client = OkHttpClient()
    val api = url + Endpoints.Auth //API URL
    val postBodyObj = AuthObjForPost(alias = username, pass = password)
    val postBodyString = JsonBuilder.encodeToString(postBodyObj)


    val request = Request.Builder()
        .url(api)
        .post(postBodyString.toRequestBody(contentType = MEDIA_TYPE_JSON))
        .build()

    client.newCall(request).execute().use { resp ->
        var rawBody = ""


        if (resp.body != null) {
            rawBody = resp.body!!.string()
        } else {
            throw EmptyResponse()
        }


        when (resp.code) {
            401 -> {
                val errorObj = JsonBuilder.decodeFromString<AuthErrorObj>(rawBody)
                if (errorObj.error_msg.isNotEmpty()) {

                    throw AuthWrongCredentials(errorObj.error_msg)
                }

                throw AuthWrongCredentials()
            }

            400 -> {
                throw AuthBadFormatJson()
            }

            404 -> {
                throw AuthUserNotExist()
            }

            429 -> {
                throw AuthTooManyTries()
            }


        }


        if (!resp.isSuccessful) {
            throw ResponseFailed()
        }


        val authObj = JsonBuilder.decodeFromString<AuthObj>(rawBody)

        result.AuthKey = authObj.data.access_token

        return result


    }


}

fun main() {
    val un = "palash"
    val ps = "password"
    val url = "https://notes.palashbauri.in"

    try {
        val a = WfLogin(url, un, ps)
        println(a.AuthKey)
    } catch (e: Exception) {
        println(e)
    }

}


