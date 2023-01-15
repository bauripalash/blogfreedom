package `in`.palashbauri.writefreelykt

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

const val AUTH_EP = "/api/auth/login"
const val LOGOUT_EP = "/api/auth/me"
val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

//url: String = "" , AuthKey : String = "" , Username : String = "", Err : String = ""
class WriteFreely {
    var Url  = ""
    var AuthKey = ""
    var Username = ""
    var Err = ""

    fun Logout() : Boolean {
        val client  = OkHttpClient()
        val api_url = Url + LOGOUT_EP
        val req = Request.Builder()
            .url(api_url)
            .header("Authorization" , "Token $AuthKey")
            .delete()
            .build()
        client.newCall(req).execute().use { resp ->
            if (resp.code == 204){
                return  true
            }
            return  false
        }
    }

}


@Serializable
data class AuthObjUser(val username : String ,val email : String ,val created : String )

@Serializable
data class AuthObjData(val access_token : String ,val user : AuthObjUser)

@Serializable
data class AuthObj(val code : Int  ,val data : AuthObjData)

@Serializable
data class AuthObjForPost(val alias : String , val pass : String)

fun WfLogin( url : String , username : String , password : String) : WriteFreely{
    var result = WriteFreely()
    result.Url = url
    result.Username = username
    val JsonBuilder  = Json { ignoreUnknownKeys = true }
    val client = OkHttpClient()
    val api = url + AUTH_EP //API URL
    val postBodyObj = AuthObjForPost(alias = username , pass = password)
    val postBodyString = JsonBuilder.encodeToString(postBodyObj)


    val request = Request.Builder()
                .url(api)
                .post(postBodyString.toRequestBody(contentType = MEDIA_TYPE_JSON))
                .build()

    client.newCall(request).execute().use {resp ->

        if (resp.code == 401)  {
            result.Err = "Wrong Username or Password or URL"
            return result
        }// throw Exception("Wrong Username or Password or URL")

        if (!resp.isSuccessful){
            result.Err = "Failed to send request"
            return result
        }

        if (resp.body == null) {
            result.Err = "Got Invalid/Empty Response"
            return result
        }

        val rawbody = resp.body!!.string()

        val authObj = JsonBuilder.decodeFromString<AuthObj>(rawbody , )



        //println(authObj)
        result.AuthKey = authObj.data.access_token

        return  result


    }



}

fun main(){
    val un = "username"
    val ps = "password"
    val url = "https://notes.palashbauri.in"

    val a = WfLogin(url , un , ps)
    println(a.AuthKey)

}


