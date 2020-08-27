package fr.harmony.serveur_harmony


var listMsgs: ArrayList<MsgBean> = ArrayList()
var listUser: ArrayList<UserBean> = ArrayList()

//Recherche le même nom de user, puis compare les mot de passe des deux Users
fun checkPwd(user: UserBean): Boolean? {

    val userCompare = listUser.find { it.login == user.login && it.pwd == user.pwd }

    if (userCompare != null) return userCompare.pwd == user.pwd
    else return null
}

fun addUser(user: UserBean) {
    listUser.add(user)
}

fun addMsg(msg: MsgBean) {
    listMsgs.add(msg)
}

fun getUserBySession(userIdSession: Long): UserBean? {

    for (user in listUser) {
        if (user.idSession == userIdSession) return user
    }
    return null
}

fun getListMsg(): ArrayList<MsgBean> {
    return listMsgs
}

//Retourne true si le login existe déjà
fun checkLoginDoublon(user: UserBean): Boolean {
    for (userOnList in listUser) {
        if (userOnList.login == user.login)
            return true
    }
    return false
}