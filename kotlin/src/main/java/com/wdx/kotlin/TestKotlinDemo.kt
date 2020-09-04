package com.wdx.kotlin

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/4 14:16
 */
class TestKotlinDemo {

}

fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
    return a + b
}

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

public fun sum3(a: Int, b: Int): Int = a + b
fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

public fun printSum1(a: Int, b: Int) {
    print(a + b)
}

fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
}


var age = null
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

/*shl(bits) – 左移位 (Java’s <<)
shr(bits) – 右移位 (Java’s >>)
ushr(bits) – 无符号右移位 (Java’s >>>)
and(bits) – 与
or(bits) – 或
xor(bits) – 异或
inv() – 反向*/
// 测试
fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '4'.toInt() // 显式转换为数字
}
open class Runoob() {

    var name: String = ""
    var url: String = ""
    var city: String = ""
    fun foo() {
        print("Foo")
    } // 成员函数
}
class Person constructor() : Runoob()  {
     constructor(firstName: String) : this() {

     }
    inner class Dog{

    }

}

val site = Runoob()
open class User () {

}
val user = User()
fun main(args: Array<String>) {
    // vars(1,2,3,4,5)  // 输出12345
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(1, 2))  // 输出 3
    val a: Int = 5
    val c: Int      // 如果不在声明时初始化则必须提供变量类型

    c = 30
    val d: Int = 5
    var value = d.shl(1)
    println("$value")
    println("$c $d")
    var h = c.and(d)
    println("$h")
    var x = c.xor(d)
    println("" + x)
    val s1 = "a is $a"
    //抛出空指针异常
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1
    println(ages2)

    val b: Byte = 1 // OK, 字面值是静态检测的
    val i: Int = b.toInt() // OK
    println(decimalDigitValue('1'))
    var status: Boolean = true
    var status1: Boolean = false
    println(status && !status1)

    val aarr = arrayOf(1, 2, 3)
    val xarr: IntArray = intArrayOf(1, 2, 3)
    xarr[0] = xarr[1] + xarr[2]
    //[0,2,4]
    val barr = Array(4, { i -> (i * 2) })

    //读取数组内容
    println(xarr[0])    // 输出结果：1
    println(aarr[0])    // 输出结果：1
    println(barr[3])    // 输出结果：2
    var str = "dsafasdfsd3323412"
    for (c in str) {
        println(c)
    }
    val text = """
多行字符串
    多行字符串
    """
    println(text)   // 输出有一些前置空格
    val text1 = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
    println(text1)    // 前置空格删除了
    val price = """
    $9.99
    """.trim()
    println(price)  // 求值结果为 $9.99
    var con = a > b
    val max = if (con) a else b
    println(max)
    val x1 = 5
    val y1 = 9
    if (x1 in 1..8) {
        println("x 在区间内")
    }
    x = 2

    when (x) {
        in 1..10 -> print("x == 1..10")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x 不是 1 ，也不是 2")
        }
    }

    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
    for (item in items) {
        println(item)
    }
    var ints = intArrayOf(1, 3, 4, 5, 0, 3)
    ints.forEach lit@{
        if (it == 0) return@lit
        println(it)
    }
    val abc : A = object : A(1), B {
        override val y = 15
    }

    println(abc.y)

    val b1 = BaseImpl(10)
    Derived(b1).print() // 输出 10


   // user.name = "第一次赋值"
  //  user.name = "第二次赋值"
}


interface Base {
    fun print()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b


class Example {
    var p: String by Delegate()
}

// 委托的类
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}
class DataProvider{}
object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {
        // ……
    }
    val a = DataProvider();
    val b = DataProvider();
    val allDataProviders: Collection<DataProvider> = listOf(a,b)
}
open class A(x: Int) {
    public open val y: Int = x
}

interface B {}


var person= Person()
open class Person1(name:String){
    /**次级构造函数**/
    constructor(name:String,age:Int):this(name){
        //初始化
        println("-------基类次级构造函数---------")
    }
}
fun Person.aaa(){

}
/**子类继承 Person 类**/
class Student:Person1{

    /**次级构造函数**/
    constructor(name:String,age:Int,no:String,score:Int):super(name,age){
        println("-------继承类次级构造函数---------")
        println("学生名： ${name}")
        println("年龄： ${age}")
        println("学生号： ${no}")
        println("成绩： ${score}")
    }
}
interface MyInterface{
    var name:String //name 属性, 抽象的
}

class MyImpl:MyInterface{
    override lateinit var name: String  //重写属性
}