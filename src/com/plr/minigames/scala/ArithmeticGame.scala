package com.plr.minigames.scala

import java.text.MessageFormat
import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.Random

object ArithmeticGame {
  def main(args: Array[String]): Unit = {
    println("欢迎来到三则运算小游戏，请根据题目输入正确的值并得分！")
    val score = cur(0, 0)
    println(MessageFormat.format("回答完毕，最终得分为 {0} 分", score))
  }

  @tailrec
  def cur(count: Int, score: Int): Int = if (count <= 4) cur(count + 1, score + display(count)) else score

  def display(count: Int): Int = {
    val random = new Random()
    val a = random.between(-100, 101);
    val b = random.between(-100, 101);
    val method = random.between(0, 3)
    val bStr = if (b >= 0) b else "(" + b + ")"
    val result = method match {
      case 0 => a + b
      case 1 => a - b
      case 2 => a * b
    }
    val methodStr = method match {
      case 0 => "+"
      case 1 => "-"
      case 2 => "*"
    }
    println(MessageFormat.format("题目{0}：{1}{2}{3}=", count + 1, a, methodStr, bStr))
    try {
      val correct = StdIn.readInt() == result
      println(if (correct) "恭喜你回答正确！加 1 分。" else "回答错误。")
      if (correct) 1 else 0
    } catch {
      case e: NumberFormatException => println("回答错误"); 0
    }
  }
}
