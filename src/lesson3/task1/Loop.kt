@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var num = n
    do {
        count++
        num /= 10
    } while (num > 0);
    return count
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) {
        return 1
    } else return fib(n - 2) + fib(n - 1)
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = if (m >= n) m else n
    if ((m % n == 0) || (n % m == 0)) {
        return k
    } else {
        do {
            k++
        } while (k % m != 0 || k % n != 0)
        return k

    }
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var divisor = 1
    do {
        divisor++
    } while (n % divisor != 0)
    return divisor
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divisor = n
    do {
        divisor--
    } while (n % divisor != 0 && divisor > 0)
    return divisor


}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var divisor = 1
    do {
        divisor++
    } while ((m % divisor > 0 && n % divisor > 0) || divisor <= m)
    if (m % divisor == 0 && n % divisor == 0) {
        return true
    } else {
        return false
    }


}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 0
    var result = 0
    do {
        k++
        if (sqr(k) >= m && sqr(k) <= n) {
            result += 1
        }
    } while (sqr(k) <= n)
    return result > 0
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var number = x
    if (number != 1) {
        do {
            count++
            if (number % 2 == 0) {
                number /= 2
            } else {
                number *= 3
                number += 1
            }
        } while (number != 1)
        return count
    } else {
        return 0
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var sinx = x
    var k = x
    var number = 1
    var n = 1
    do {
        k *= x * x
        number += 2
        sinx -= (k / factorial(number)) * n
        n *= -1
    } while (abs(k / factorial(number)) >= eps)
    return sinx
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var cosx = 1.0
    var k = 1.0
    var number = 0
    var n = 1
    do {
        k *= x * x
        number += 2
        cosx += (k / factorial(number)) * n
        n *= -1
    } while (abs(k / factorial(number)) >= eps)
    return cosx
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var count = 0
    var divisor = 1
    var revertnumber = 0
    do {
        divisor *= 10
        count++
        number /= 10
    } while (number > 0)
    if (count > 1) {
        number += n
        divisor /= 10
        do {
            revertnumber += (number % 10) * divisor
            divisor /= 10
            number /= 10
            count--

        } while (divisor > 0 && count != 0)
        return revertnumber
    } else {
        return n
    }
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number = n
    var divisor = 1
    var count = 0
    do {
        divisor *= 10
        number /= 10
        count++
    } while (number / divisor > 0)
    number += n
    divisor /= 10
    if (count > 1) {
        do {

            if (number / divisor == number % 10) {
                count -= 2

            }
            number -= divisor * (number / divisor)
            number /= 10
            divisor /= 10


        } while (divisor > 0)
        if (count == 0 || count == 1) {
            return true
        } else {
            return false
        }
    } else {
        return true

    }
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    var sum = 0
    val numeral = n % 10
    do {
        if (n < 10) {
            sum += n
        }
        sum += number % 10
        number /= 10
    } while (number > 0)
    if (numeral != 0) {
        if (sum % numeral != 0) {
            return true
        } else {
            return false
        }
    } else {
        return false
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 0
    var x = 0
    var k = 0
    var number = 0
    do {
        x*=0
        x += sqr(k)
        if (x < 10) {
            count += 1
        } else {
            do {
                count += 1
                x /= 10
            } while (x > 0 || count < n)
        }
        k++
    } while (count < n)
    number += sqr(k)
    if (count == n) {
        return number % 10
    } else {
        do {
            number /= 10
        } while (number != x)
        return number % 10

    }

}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = 0
    var k = 1
    var x = 0
    var number = 0
    do {
        x += fib(k)
        if (x < 10) {
            count += 1
        } else {
            do {
                count += 1
                x /= 10
            } while (x / 10 > 0 || count < n)
        }
        k++
    } while (count < n)
    number += fib(k)
    if (count == n) {
        return number % 10
    } else {
        do {
            number /= 10
        } while (number != x)
        return x % 10

    }
}

