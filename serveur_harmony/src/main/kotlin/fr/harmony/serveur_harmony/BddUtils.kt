package fr.harmony.serveur_harmony

import kotlin.random.Random


var listMsgs: ArrayList<MsgBean> = ArrayList()
var listUser: ArrayList<UserBean> = ArrayList()

//Recherche le même nom de user, puis compare les mot de passe des deux Users
//Si bon mot de passe -> true
//Si mauvais mot de passe -> false
//Si pas de user reconnue -> null
fun checkPwd(user: UserBean): Boolean? {
    val userCompare = listUser.find { it.login == user.login }

    if (userCompare != null) return userCompare.pwd == user.pwd
    else return null
}

fun addUser(user: UserBean) {
    if (user.login.isNullOrBlank()) {
        throw MyException(ResponseApiEnumBean.ERR_UNKNOWN_USER.rab)
    }
    listUser.add(user)
}

fun addMsg(msg: MsgBean) {
    listMsgs.add(msg)
}

fun getUserBySession(userIdSession: Long?): UserBean? {

    if (userIdSession != null) {
        return listUser.find { it.idSession == userIdSession }
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

//Crée un idSession aléatoirement
//TODO vérifier que l'Id Session n'existe pas déjà sur un autre utilisateur
fun randomIdSession(): Long {
    return Random.nextLong(10_000_000)
}