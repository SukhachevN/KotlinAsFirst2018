@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")
    val parts = str.split(" ")
    var dmy = mutableListOf<Int>()
    var count = 1
    var result = ""
    while (count <= 3) {
        for (part in parts) {
            if (count == 3) {
                result += part
                count++
            }
            if (count == 2) {
                if (part in month) {
                    if (month.indexOf(part) > 9) {
                        result += (month.indexOf(part) + 1).toString() + '.'
                    } else {
                        result += '0' + (month.indexOf(part) + 1).toString() + '.'
                    }
                    count++
                } else {
                    return ""
                }
            }
            if (count == 1) {
                if (part.toInt() <= 31) {
                    if (part.toInt() > 9) {
                        result += part + '.'
                        count++
                    } else {
                        val x = part.toInt()
                        result += '0' + "$x" + '.'
                        count++
                    }
                } else {
                    return ""
                }
            }
        }
    }
    val parts2 = result.split(".")
    for (part in parts2) {
        dmy.add(part.toInt())
    }
    if (daysInMonth(dmy[1], dmy[2]) <= dmy[0]) {
        return ""
    } else {
        return result
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")
    val parts = digital.split(".")
    val numbers = "0123456789"
    var count = 1
    var result = ""
    val dmy = mutableListOf<Int>()
    for (part in parts) {
        for (char in part) {
            if (char !in numbers) {
                return ""
            }
        }
        dmy.add(part.toInt())
    }
    if (daysInMonth(dmy[1], dmy[2]) <= dmy[0] || dmy[1] !in 1..12 || parts.size != 3) {
        return ""
    }
    while (count <= 3) {
        for (part in parts) {
            if (count == 3) {
                result += part
                count++
            }
            if (count == 2) {
                result += month[part.toInt() - 1] + " "
                count++
            }
            if (count == 1) {
                val x = part.toInt()
                result += "$x "
                count++
            }

        }
    }
    return result
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    var result = ""
    val num = "0123456789"
    for (i in 0 until phone.length) {
        var x = true
        if (phone[i] == '+') {
            if (i == 0) {
                result += "+"
                x = false
            } else {
                return ""
            }
        }
        if (phone[i] in num && x) {
            result += phone[i]
        } else {
            if (phone[i] != ' ' && phone[i] != '-' && phone[i] != '(' && phone[i] != ')' && x) {
                return ""
            }
        }
    }
    return result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var max = -1
    val num = "0123456789"
    val parts = jumps.split(" ")

    for (part in parts) {
        var count = 0
        for (char in part) {
            if (char in num) {
                count++
            } else {
                if (char != '%' && char != '-' && char != ' ') {
                    return -1
                }
            }
        }
        if (part.toIntOrNull() != null) {
            if (count == part.length && part.toInt() > max) {
                max = part.toInt()
            }
        }
    }
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var max = -1
    var element = -2
    val num = "0123456789"
    val parts = jumps.split(" ")
    val s = listOf('%', '-', '+', ' ')
    if (jumps == "") {
        return -1
    }
    var x = false
    for (part in parts) {
        var count = 0
        for (char in part) {
            if (char in num) {
                count++
            } else {
                if (char !in s) {
                    return -1
                }
            }
        }
        if (count == part.length) {
            x = true
            if (part.toIntOrNull() != null) {
                element = part.toInt()
            }
        }
        if (part == "+" && x && element > max) {
            max = element
            x = false
        }
    }
    if (parts.last() != "-" && parts.last() != "+" && parts.last() != "%") {
        if (parts.last().toInt() > max) {
            max = parts.last().toInt()
        }
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var result = 0
    var sign = '+'
    val num = "0123456789"
    var x = true
    val parts = expression.split(" ")
    if (expression == "") {
        throw IllegalArgumentException()
    }
    for (part in parts) {
        var count = 0
        for (char in part) {
            if (char in num && x) {
                count++
            } else {
                if ((char == '-' || char == '+') && !x) {
                    sign = char
                    x = true
                } else {
                    throw IllegalArgumentException()
                }
            }

        }
        if (count == part.length && x) {
            if (sign == '+') {
                result += part.toInt()
            } else {
                result -= part.toInt()
            }
            x = false

        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var word = ""
    var count = 0
    var space = -1
    val parts = str.split(" ")
    for (part in parts) {
        space++
        for (char in part) {
            count++
        }
        if (part.toLowerCase() == word) {
            space--
            return count + space - 2 * part.length
        } else {
            word = part.toLowerCase()
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    var max = -1.0
    var x = ""
    var a = 1
    val parts = description.split(";")
    for (part in parts) {
        val element = part.split(" ")
        if (element.size != 2 && a == 1) {
            return ""
        }
        if (element.size != 3 && a != 1) {
            return ""
        }
        val list = mutableListOf<String>()
        for (y in element) {
            list.add(y)
        }
        if (a == 1) {
            if (list[1].toDouble() > max) {
                if (list[1].toDoubleOrNull() == null) {
                    return ""
                }
                max = list[1].toDouble()
                x = list[0]
            }
            a = 2
        } else {
            if (list[2].toDouble() > max) {
                if (list[2].toDoubleOrNull() == null) {
                    return ""
                }
                max = list[2].toDouble()
                x = list[1]
            }
        }
    }
    return x
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
