@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val x1 = number/1000
    val x2 = (number/100)%10
    val x3 = (number/10)%10
    val x4 = number%10
    if (x1+x2==x3+x4) {
        return true
    }
    return false
 }

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    for (i in -7..7) {
    if ((x1==x2 || y1==y2 ) || (((x1==x2+i) && (y1==y2+i)) || (x1==x2+i && y1 == x2-i))) {
                return true
            }

    }
    return false
}

/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    if ( month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
        return 31
    }
    if (month==2) {
        if ((year % 4) == 0) {
            return 29
        }
        if ((year % 4)>0) {
            return 28
        }
    }
    if ( month==4 || month==6 || month== 9 || month == 11) {
        return 30
    }
    return 0

}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {
      if (r1<=r2) {
            if (sqrt(sqr(x2-x1) + sqr(y2-y1))+r1<=r2)  {
                return true
            }
          }

    return false

}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    if (a >= r) {
        if (b >= s) {
            return true

        }
        if (c >= s) {
            return true

        }
    } else {
        if (a >= s) {
            if (b >= r) {
                return true

            }
            if (c >= r) {
                return true

            }
        }
    }
    if (b >= r) {
        if (c >= s) {
            return true
        }
    } else {
        if (b >= s) {
            if (c >= r) {
                return true
            }
        }
    }
    return false
}
