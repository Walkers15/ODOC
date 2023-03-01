//https://www.acmicpc.net/problem/2839

package boj.kotlin

fun main() {
    val n  = readLine()!!.toInt()
    val t = n/5
    var min = Int.MAX_VALUE
    var j = 0;
    for(i in t downTo 0){//i : 5kg, j : 3kg
        while(i*5+j*3<=n){
            if(i*5+j*3 == n){
                if((i+j)<min) min = i+j
            }
            j++
        }
        j=0
    }
    if(min > 1700)
        print(-1)
    else print(min)
}
