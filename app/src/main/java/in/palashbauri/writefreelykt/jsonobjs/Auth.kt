package `in`.palashbauri.writefreelykt.jsonobjs

import kotlinx.serialization.Serializable

@Serializable
data class AuthObjUser(val username: String, val email: String, val created: String)

@Serializable
data class AuthObjData(val access_token: String, val user: AuthObjUser)

@Serializable
data class AuthObj(val code: Int, val data: AuthObjData)

@Serializable
data class AuthObjForPost(val alias: String, val pass: String)

@Serializable
data class AuthErrorObj(val code: Int, val error_msg: String)