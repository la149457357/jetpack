package com.wdx.kotlin

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/4 14:16
 */
    fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }
    fun sum1(a: Int, b: Int): Int{
        return a+b
    }
    fun sum2(a: Int, b: Int) = a + b

    public fun sum3(a: Int, b: Int): Int = a + b
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }
    public fun printSum1(a: Int, b: Int) {
        print(a + b)
    }

    fun vars(vararg v:Int){
        for(vt in v){
            print(vt)
        }
    }


var age = null
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

// 测试
fun main(args: Array<String>) {
   // vars(1,2,3,4,5)  // 输出12345
    val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    println(sumLambda(1,2))  // 输出 3
    val a: Int = 1
    val c: Int      // 如果不在声明时初始化则必须提供变量类型
    c = 1
    val d : Int =1
    val s1 = "a is $a"
//抛出空指针异常
//不做处理返回 null
    val ages1 = age?.toInt()
//age为空返回-1
    val ages2 = age?.toInt() ?: -1
    print(ages2)

    val b: Byte = 1 // OK, 字面值是静态检测的
    val i: Int = b.toInt() // OK

}