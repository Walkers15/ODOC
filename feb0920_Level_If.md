### https://www.acmicpc.net/problem/9498
```
fun main() {
    when(readLine()!!.toInt()){
        in 90..100 -> print("A")
        in 80..89 -> print("B")
        in 70..79 -> print("C")
        in 60..69 -> print("D")
        else -> print("F")
    }
}
```
   
### https://www.acmicpc.net/problem/2753
```
fun main() {
    val year = readLine()!!.toInt()
    if(year % 4 == 0){
        when {
            year % 100 != 0 -> print(1)
            year % 400 == 0 -> print(1)
            else -> print(0)
        }
    } else print(0)
}
```
   
### https://www.acmicpc.net/problem/10817
```
import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    val a = sc.nextInt();
    val b = sc.nextInt();
    val c = sc.nextInt();
    if(a>b){
        if(b>c) print(b)
        else if(a>c) print(c)
        else print(a)
    }else if(a>c) print(a)
    else if(b>c) print(c)
    else print(b)
}
```
