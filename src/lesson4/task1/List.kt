@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (element in v) {
        result += sqr(element)
    }
    return sqrt(result)

}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var count = 0.0
    var sum = 0.0
    for (element in list) {
        count++
        sum += element
    }
    if (count > 0) {
        return sum / count
    } else {
        return 0.0
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var x = mean(list)
    for (i in 0 until list.size) {
        var element = list[i] - x
        list[i] = element
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    for (i in 0 until a.size) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var result = 0.0
    var xdegree = 1.0
    for (element in p) {
        result += element * xdegree
        xdegree *= x
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0
    for (i in 0 until list.size) {
        if (i != 0) {
            var element = list[i] + sum
            sum += list[i]
            list[i] = element
        } else {
            sum += list[0]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var k = 2
    var x = n
    val result = mutableListOf<Int>()
    do {
        if (isPrime(k) == true) {
            if (x % k == 0) {
                result.add(k)
                x /= k
            }

            if (x % k != 0) {
                k++
                x *= 0
                x += n
            }
        } else k++
    } while (k <= n)
    return result

}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var k = 2
    var x = n
    var string: String = ""
    do {
        if (isPrime(k) == true) {
            if (x % k == 0) {
                var result = "$k*"
                string += result
                x /= k
            }
            if (x % k != 0) {
                k++
                x *= 0
                x += n
            }
        } else k++
    } while (k <= n)
    return string.substring(0, string.length - 1)
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var number = n
    var number2 = 0
    var count = 0
    var base1 = 1
    do {
        base1 *= base
        count++
    } while (base1 < number)
    base1 /= base
    do {
        result.add(number / base1)
        number2 += number % base1
        number *= 0
        number += number2
        number2 *= 0
        if (base1 > 1) {
            base1 /= base
        }
        count--

    } while (count > 0)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var string = ""
    var number = n
    var number2 = 0
    var count = 0
    var base1 = 1
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    do {
        base1 *= base
        count++
    } while (base1 < number)
    base1 /= base
    do {
        if (number / base1 < 10) {
            string += number / base1
            number2 += number % base1
            number *= 0
            number += number2
            number2 *= 0
            if (base1 > 1) {
                base1 /= base
            }
            count--
        } else {
            string += alphabet[(number / base1) - 10]
            number2 += number % base1
            number *= 0
            number += number2
            number2 *= 0
            if (base1 > 1) {
                base1 /= base
            }
            count--
        }

    } while (count > 0)
    return string
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var count = digits.size
    var number = 0
    var base1 = 1
    var k = 0
    do {
        if (count > 1) {
            for (i in 2..count) {
                base1 *= base
            }
        }
        number += digits[k] * base1
        k++
        count--
        base1 *= 0
        base1 += 1
    } while (count > 0)
    return number
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var number = 0
    var count = str.length
    var base1 = 1
    var k = 0
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    do {
        if (count > 1) {
            for (i in 2..count)
                base1 *= base
        }
        if (str[k] in 'a'..'z') {
            number += (alphabet.indexOf(str[k], 0) + 10) * base1
            k++
            count--
            base1 *= 0
            base1 += 1
        }
        if (str[k] in '0'..'9') {
            number += str[k].toInt() * base1
            k++
            count--
            base1 *= 0
            base1 += 1
        }
    } while (count > 0)
    return number

}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result: String = ""
    var num = n
    var count = 0
    if (n > 999) {
        count += 1000
    }
    if (n > 99 && count == 0) {
        count += 100
    }
    if (n > 9 && count == 0) {
        count += 10
    }
    if (n <= 9 && count == 0) {
        count += 1
    }
    do {
        if (num < 10) {
            when {
                num < 4 -> {
                    for (i in 1..num) {
                        result += "I"
                    }
                }
                num == 4 -> {
                    result += "IV"
                }
                num == 5 -> {
                    result += "V"
                }

                num in 6..8 -> {
                    result += "V"
                    for (i in 1..(num - 5)) {
                        result += "I"
                    }
                }
                num == 9 -> {
                    result += "IX"
                }
            }
        }
        if (num / 10 < 10 && num in 10..99) {
            when {
                num / 10 in 1..3 -> {
                    for (i in 1..(num / 10)) {
                        result += "X"
                    }
                }
                num / 10 == 4 -> {
                    result += "XL"
                }
                num / 10 == 5 -> {
                    result += "L"
                }
                num / 10 in 6..8 -> {
                    result += "L"
                    for (i in 1..((num / 10) - 5))
                        result += "X"
                }
                num / 10 == 9 -> {
                    result += "XC"
                }
            }
        }
        if (num / 100 < 10 && num in 100..999) {
            when {
                num / 100 in 1..3 -> {
                    for (i in 1..(num / 100))
                        result += "C"
                }
                num / 100 == 4 -> {
                    result += "CD"
                }
                num / 100 == 5 -> {
                    result += "D"
                }
                num / 100 in 6..8 -> {
                    result += "D"
                    for (i in 1..((num / 100) - 5)) {
                        result += "C"
                    }
                }
                num / 100 == 9 -> {
                    result += "CM"
                }
            }
        }
        if (num / 1000 < 10 && num >= 1000) {
            for (i in 1..(num / 1000)) {
                result += "M"
            }
        }
        num -= (num / count) * count
        count /= 10
    } while (num > 0)
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()