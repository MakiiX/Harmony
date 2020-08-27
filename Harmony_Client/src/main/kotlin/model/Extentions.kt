package model

fun List<MsgBean>.toString():String{
    var toReturn:String=""
    this.forEach{toReturn+="${it.user.login}:\n${it.text}\n\n"}
    return toReturn
}