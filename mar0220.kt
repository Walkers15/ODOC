package boj.kotlin
import java.util.*
fun main() {
    var sc = Scanner(System.`in`)
    var n = sc.nextInt();
    var m = sc.nextInt();
    var v = sc.nextInt();
    if(m>=v){
        print(-1)
    }else {
        var x = n.toDouble() / (v - m).toDouble();
        print(x.toInt()+1)
    }
}