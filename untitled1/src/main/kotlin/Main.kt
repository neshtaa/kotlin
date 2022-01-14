abstract class employee(
    var firstName: String,
    var exp: Int,
    var baseSalary: Double,
    var mymanager: manager? = null,
) {
    open fun giveSalary(): Double {
        if (exp >= 2){
            return baseSalary + 200
        }
        if (exp >= 5){
            return baseSalary * 1.2 + 500
        }

        else
        return baseSalary
    }
}
class developer(
    firstName: String,
    exp: Int,
    baseSalary: Double
) : employee(firstName, exp, baseSalary)

class designer(
    firstName: String,
    exp: Int,
    baseSalary: Double,
    var coef: Double
) : employee(firstName, exp, baseSalary) {
    override fun giveSalary(): Double {
        return super.giveSalary() * coef
    }
}
class manager(
    firstName: String,
    exp: Int,
    baseSalary: Double,
    var team: MutableList<employee> = mutableListOf()
) : employee(firstName, exp, baseSalary) {
    override fun giveSalary(): Double {
        val devCount = team.filter { employee -> employee is developer }.count()
        val result = 0.0
        if (devCount > 5) {
            return result + 200
        }
        if (devCount > 10) {
            return result + 300
        }
        else return result

        return super.giveSalary() + result
    }
}
class department(
    var managers: MutableList<manager> = mutableListOf()
){
    fun giveAllSalary(){
        managers.forEach {manager ->
            manager.giveSalary()
            manager.team.forEach {employee ->
                employee.giveSalary()
            }
        }
    }
}
fun main() {
    val dev1 = developer(firstName = "aaaaa", exp = 1, baseSalary = 2.0)
    val dev2 = developer(firstName = "bbbbb", exp = 2, baseSalary = 3.0)
    val dev3 = developer(firstName = "ccccc", exp = 3, baseSalary = 4.0)
    val dev4 = developer(firstName = "ddddd", exp = 4, baseSalary = 5.0)
    val dev5 = developer(firstName = "eeeee", exp = 5, baseSalary = 6.0)
    val dev6 = developer(firstName = "fffff", exp = 6, baseSalary = 7.0)
    val dev7 = developer(firstName = "ggggg", exp = 7, baseSalary = 8.0)
    val man1 = manager(firstName = "hhhhh", exp = 1, baseSalary = 3.0)
    val man2 = manager(firstName = "iiiii", exp = 2, baseSalary = 4.0)
    val des1 = designer(firstName = "jjjjj", exp = 1, baseSalary = 2.0, coef = 0.2)
    val des2 = designer(firstName = "kkkkk", exp = 2, baseSalary = 3.0, coef = 0.4)
    val des3 = designer(firstName = "lllll", exp = 3, baseSalary = 4.0, coef = 0.6)
    val des4 = designer(firstName = "mmmmm", exp = 4, baseSalary = 5.0, coef = 0.8)
    man1.team.add(dev1)
    man1.team.add(dev2)
    man1.team.add(dev3)
    man1.team.add(dev4)
    man1.team.add(des1)
    man1.team.add(des1)
    man2.team.add(dev5)
    man2.team.add(dev6)
    man2.team.add(dev7)
    man2.team.add(des3)
    man2.team.add(des4)
    val dep = department()
    dep.giveAllSalary()

}