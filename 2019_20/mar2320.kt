//https://www.acmicpc.net/problem/11047
package boj.kotlin

import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    var k : Int = sc.nextInt()
    val arr : Array<Int> = Array(n) {sc.nextInt()}
    var count = 0
    var index = 0
    arr.sortDescending()
    loop@ while(true){
        when {
            k - arr[index] >= 0 -> {
                count++
                k -= arr[index]
            }
            k == 0 -> {
                break@loop;
            }
            else -> {
                index++
            }
        }
    }
    print(count)
}
