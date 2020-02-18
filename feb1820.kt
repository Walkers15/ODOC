//https://www.acmicpc.net/problem/15552
package boj.kotlin

import java.io.*

fun main() {
    //val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val n = readLine()!!.toInt()
    for(i in 1..n) {
        val str = readLine()
        val arr = str!!.split(" ")
        val result = (arr[0].toInt() + arr[1].toInt()).toString()
        bw.write("$result\n")
    }
    bw.flush()
    bw.close()
}
