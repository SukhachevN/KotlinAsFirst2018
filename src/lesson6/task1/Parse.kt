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
    if (errorString(str) != "ok") {
        return ""
    }
    val result = StringBuilder()
    if (parts[0].toIntOrNull() != null) {
        if (parts[0].toInt() <= 9) {
            result.append('0')
        }
        result.append((parts[0].toInt()).toString() + '.')
    }
    if (parts[1] in month) {
        if (month.indexOf(parts[1]) < 9) {
            result.append('0')
        }
        result.append((month.indexOf(parts[1]) + 1).toString() + '.')
    } else {
        return ""
    }
    result.append(parts[2])
    return if (errorString(result.toString()) != "ok") "" else result.toString()
}

fun errorString(str: String): String {
    val dmy = mutableListOf<Int>()
    var parts = str.split(".")
    if (parts.size == 3) {
        for (part in parts) {
            if (part.isEmpty()) {
                return ""
            }
            for (char in part) {
                if (char !in '0'..'9') {
                    return ""
                }
            }
            dmy.add(part.toInt())
        }
        if (daysInMonth(dmy[1], dmy[2]) >= dmy[0] && dmy[1] in 1..12 && dmy[0] > 0) {
            return "ok"
        }
    } else {
        parts = str.split(" ")
        if (parts.size == 3) {
            for (part in parts) {
                for (char in part) {
                    if (char !in 'а'..'я' && char !in '0'..'9') {
                        return ""
                    }
                }
            }
            return "ok"
        } else {
            return ""
        }
    }
    return ""
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
    val result = StringBuilder()
    if (errorString(digital) != "ok") {
        return errorString(digital)
    }
    val x = parts[0].toInt()
    result.append("$x ")
    result.append(month[parts[1].toInt() - 1] + " ")
    result.append(parts[2])
    return result.toString()
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
    val result = StringBuilder()
    var flag = true
    var count = 0
    var check = 0
    for (i in 0 until phone.length) {
        var x = true
        if (phone[i] == '+') {
            if (i == 0) {
                result.append("+")
                x = false
            } else {
                return ""
            }
        }
        if (phone[i] == '(') {
            if (check != 0) {
                return ""
            }
            check++
            flag = false
        }
        if (phone[i] == ')') {
            if (count == 0 || flag) {
                return ""
            } else {
                flag = true
            }
        }
        if (phone[i] in '0'..'9' && x) {
            result.append(phone[i])
            if (!flag) {
                count++
            }
        } else {
            if (phone[i] !in setOf(' ', '-', '(', ')') && x) {
                return ""
            }
        }
    }
    if (result.isNotEmpty() && flag) {
        return result.toString()
    }
    return ""
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
    val parts = jumps.split(" ")
    for (part in parts) {
        var count = 0
        for (char in part) {
            if (char in '0'..'9') {
                count++
            } else {
                if ((char != '%' && char != '-') || part.length != 1) {
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
    val parts = jumps.split(" ")
    val s = listOf('%', '-', '+')
    if (jumps == "") {
        return -1
    }
    var checkNumber = false
    for (i in 0..(parts.size - 1)) {
        if (i % 2 == 1 && parts[i].toIntOrNull() != null) {
            return -1
        }
        var count = 0
        for (char in parts[i]) {
            if (char in '0'..'9') {
                count++
                if (parts[i].toIntOrNull() == null) {
                    return -1
                }
            } else {
                if (char !in s) {
                    return -1
                }
            }
        }
        if (count == parts[i].length) {
            checkNumber = true
            if (parts[i].toIntOrNull() != null) {
                element = parts[i].toInt()
            }
        }
        if (parts[i].contains("+") && checkNumber && element > max) {
            max = element
            checkNumber = false
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
    var x = true
    val parts = expression.split(" ")
    if (expression == "" || expression[0] == ' ') {
        throw IllegalArgumentException()
    }
    var last = ""
    for (part in parts) {
        var count = 0
        for (char in part) {
            if (char in '0'..'9' && x && part.toIntOrNull() != null) {
                count++
            } else {
                if ((char == '-' || char == '+') && !x && part.toIntOrNull() == null) {
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
        last = part
    }
    if (last == "-" || last == "+") {
        throw IllegalArgumentException()
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
        count += part.length
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
        if ((element.size != 2 && a == 1) || (element.size != 3 && a != 1)) {
            return ""
        }
        val list = element.toMutableList()
        if (list[a].toDoubleOrNull() == null) {
            return ""
        }
        if (list[a].toDouble() > max) {
            max = list[a].toDouble()
            x = list[a - 1]
            a = 2
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
