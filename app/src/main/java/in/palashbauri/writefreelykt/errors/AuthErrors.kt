package `in`.palashbauri.writefreelykt.errors


//Login
class AuthWrongCredentials(msg: String = "Wrong Username or Password or URL") : Exception(msg)
class AuthUserNotExist(msg: String = "User doesn't exist on this server") : Exception(msg)
class AuthBadFormatJson(msg: String = "Sent data in bad format") : Exception(msg)
class AuthTooManyTries(msg: String = "Too many login tries") : Exception(msg)


//Logout

class LogoutMissingToken(msg : String = "Token missing; Internal error"): Exception(msg)