//반복문 출력으로 하루 날로먹기

package boj.kotlin

fun main() {
    val n = readLine()!!.toInt()
    for(i in 1..9) println("$n * $i = ${n*i}")
}
