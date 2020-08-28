package model

fun List<MsgBean>.toView():String{
    var toReturn:String=""
    this.forEach{toReturn="${it.user.login}:\n${it.text}\n\n"+toReturn}
    return toReturn
}