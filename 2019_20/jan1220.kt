//https://www.acmicpc.net/problem/2884
//일요일은 휴일이니까 쉬운 거 풀래요,,
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    var hour = sc.nextInt()
    var min = sc.nextInt()
    var oriTime = hour*60+min
    var setTime = oriTime - 45
    if(setTime < 0){
        hour = 23
        min = 60+setTime
        print(hour)
        print(" ")
        print(min)
    } else {
        hour = setTime / 60
        min = setTime % 60;
        print(hour)
        print(" ")
        print(min)
    }
}
