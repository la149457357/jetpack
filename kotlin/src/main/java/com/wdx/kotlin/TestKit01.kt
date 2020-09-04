package com.wdx.kotlin

import kotlin.reflect.KProperty

/**
 *
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/4 20:28
 */

/**
 * 定义一个生物接口，有一个run抽象方法和一个抽象属性
 */
interface Creature {
    fun run()
    val type: String
}

/**
 * 定义一个委托类，实现了Creature接口
 */
class DelegateClass : Creature {
    override val type: String
        get() = "委托类该属性"

    override fun run() {
        println("代理类执行run方法")
    }
}
//①委托类作为构造器形参传入（常用）
/**
 * 定义一个Human类，实现了Creature接口，委托类作为形参传入，由形参委托类对象作为委托对象
 */
class Human(delegateClass: DelegateClass) : Creature by delegateClass
//②新建委托类对象
/**
 * 定义一个Dog类，实现了Creature接口，新建一个委托类作为委托对象
 */
class Dog : Creature by DelegateClass()
//③新建委托类对象，并自己实现方法/属性
/**
 * 定义一个pig类，实现了Creature接口，新建一个委托类作为委托对象，并自己实现了抽象方法
 */
class Pig : Creature by DelegateClass() {
    override fun run() {
        println("自己执行实现的run方法")
    }

    override val type: String
        get() = "自己实现的type属性"
}

fun main(args: Array<String>) {
    //作为形参（委托对象可复用）
    val delegateClass = DelegateClass()
    val human = Human(delegateClass)
    human.run()//结果：代理类执行run方法
    println(human.type)//结果：委托类该属性
    val human2 = Human(delegateClass)
    human2.run()//结果：代理类执行run方法
    println(human2.type)//结果：委托类该属性

    //新建对象
    val dog = Dog()
    dog.run()//结果：代理类执行run方法
    println(dog.type)//结果：委托类该属性

    //新建对象并自己实现方法/属性
    val pig = Pig()
    pig.run()//结果：自己执行实现的run方法
    println(pig.type)//结果：自己实现的type属性

    //属性委托



}
