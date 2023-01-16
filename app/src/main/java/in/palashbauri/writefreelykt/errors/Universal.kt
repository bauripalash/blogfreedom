package `in`.palashbauri.writefreelykt.errors

class EmptyResponse(msg: String = "Got empty response from server") : Exception(msg)
class ResponseFailed(msg: String = "Failed to get a response from server") : Exception(msg)
class InvalidToken(msg: String = "Token is invalid or expired") : Exception(msg)