@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
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
    } while (abs(num) > 0);
    return count
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 0
    var k1 = 1
    var k2 = 1
    if (n <= 2) {
        return 1
    }
    for (i in 3..n) {
        result = k1 + k2
        k1 = k2
        k2 = result
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = max(m, n)

    return if ((m % n == 0) || (n % m == 0)) k
    else {
        var divisor = k / 2 + 1
        do {
            divisor--
        } while (m % divisor != 0 || n % divisor != 0)
        m * n / divisor
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
    } while (n % divisor != 0 && divisor <= n / 2)
    return if (n % divisor == 0) divisor else n
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divisor = n / 2 + 1
    do {
        divisor--
    } while (n % divisor != 0)
    return if (divisor >= 1) divisor else n
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
    } while ((m % divisor > 0 || n % divisor > 0) && divisor <= max(m, n) / 2 + 1)
    return (m % divisor != 0 && n % divisor != 0)
}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = sqrt(n.toDouble()).toInt() - 1
    var result = 0
    if (m > 1) {
        k++
        if (sqr(k) >= m && sqr(k) <= n) {
            result += 1
        }
    } else {
        return true
    }
    return result != 0
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
    var SinX = x
    var k = x
    var number = 1
    var n = 1.0
    do {
        k *= x * x
        number += 2
        SinX -= (k / factorial(number)) * n
        n *= -1.0
    } while (k / factorial(number) >= eps)
    return SinX
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var CosX = 1.0
    var k = 1.0
    var number = 0
    var n = -1
    do {
        k *= x * x
        number += 2
        CosX += (k / factorial(number)) * n
        n *= -1
    } while (k / factorial(number) >= eps)
    return CosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num = n
    var count = 0
    var div = 1
    var rnum = 0
    if (n > 9) {
        do {
            div *= 10
            count++
            num /= 10
        } while (num > 0)
        num += n
        div /= 10
        do {
            rnum += (num % 10) * div
            div /= 10
            num /= 10
            count--

        } while (div > 0 && count != 0)
        return rnum
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
    var count = 1
    do {
        divisor *= 10
        number /= 10
        count++
    } while (number >= 10)
    number = n
    if (count > 1) {
        do {
            if (number / divisor == number % 10) {
                count -= 2
            } else {
                return false
            }
            if ((number / (divisor / 10)) % 10 != 0) {
                number -= divisor * (number / divisor)
                number /= 10
                divisor /= 100
            } else {
                if (((number - number % 10) / 10) % 10 == 0) {
                    number -= divisor * (number / divisor)
                    number /= 100
                    divisor /= 10000
                    count -= 2
                }
            }
        } while (divisor > 0)
        if (count <= 0) {
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
    var k: Int
    val numeral = n % 10
    if (n > 9) {
        number -= numeral
        number /= 10
        do {
            k = number % 10
            number -= k
            number /= 10
        } while (number > 0 && k == numeral)
        return k != numeral
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
    var x: Int
    var k = 1
    var number: Int
    do {
        x = sqr(k)
        if (x < 10) {
            count += 1
            k++
        } else {
            do {
                count += 1
                x /= 10
            } while (x > 0 && count < n)
            k++
        }
    } while (count < n)
    return if (n < 4) sqr(k-1)
    else {
        number = sqr(k-1)
        if (x == 0) sqr(k-1) % 10
        else {
            do {
                number -= number % 10
                number /= 10
            } while (number != x && number > 10)
            number % 10
        }
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
    var k = 0
    var x: Int
    var number: Int
    do {
        x = fib(k)
        if (x < 10) {
            count += 1
            k++
        } else {
            do {
                count += 1
                x /= 10
            } while (x > 0 && count < n)
            k++
        }
    } while (count < n)
    number = fib(k)
    return if (n < 7) fib(k)
    else {
        if (x != 0) fib(k - 1) % 10
        else {
            do {
                number -= number % 10
                number /= 10
            } while (number != x && number > 9)
            number
        }
    }
}
