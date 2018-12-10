@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson3.task1.digitNumber
import java.io.File


/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    val text = File(inputName).readLines()
    for (element in substrings) {
        var count = 0
        for (string in text) {
            if (element.toLowerCase() in string.toLowerCase()) {
                val parts = string.toLowerCase().split(element.toLowerCase())
                if (parts.size > 1) {
                    count += parts.size - 1
                } else {
                    count++
                }
            }
        }
        result[element] = count
    }
    return result
}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val text = File(inputName).readLines()
    File(outputName).bufferedWriter().use {
        val letters1 = listOf('Ж', 'Ч', 'Ш', 'Щ')
        val lettersTrue = listOf('И', 'А', 'У', 'и', 'а', 'у')
        val lettersFalse = listOf('Ы', 'Я', 'Ю', 'ы', 'я', 'ю')
        for (string in text) {
            var count = string.split(" ").size
            for (word in string.split(" ")) {
                var lastLetter = ' '
                var letter: Char
                for (char in word) {
                    letter = char
                    if (letter in lettersFalse && lastLetter in letters1) {
                        it.write(lettersTrue[lettersFalse.indexOf(char)].toInt())
                    } else {
                        it.write(char.toInt())
                    }
                    lastLetter = letter.toUpperCase()
                }
                count--
                if (count != 0) {
                    it.write(" ")
                }
            }
            it.newLine()
        }
    }
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val text = File(inputName).readLines()
    var maxLength = -1
    for (string in text) {
        var spaceAfter = 0
        for (i in (string.length - 1) downTo 0) {
            if (string[i] == ' ') {
                spaceAfter++
            } else {
                break
            }
        }
        if (string.length - spaceAfter > maxLength) {
            maxLength = string.length - spaceAfter
        }
    }
    File(outputName).bufferedWriter().use {
        for (string in text) {
            var spacesBefore = 0
            for (char in string) {
                if (char == ' ') {
                    spacesBefore++
                } else {
                    break
                }
            }
            var spaceAfter = 0
            for (i in (string.length - 1) downTo 0) {
                if (string[i] == ' ') {
                    spaceAfter++
                } else {
                    break
                }
            }
            var count = 0
            do {
                if (string.length - spaceAfter < maxLength) {
                    it.write(" ")
                    count++
                } else break
            } while (2 * count + string.length + 1 + spacesBefore - spaceAfter < maxLength)
            it.write(string)
            it.newLine()
        }
    }
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val text = File(inputName).readLines()
    var maxLength = -1
    for (string in text) {
        var count = 0
        for (i in 0..(string.length - 1)) {
            if (string[i] == ' ') {
                count++
            } else {
                break
            }
        }
        if (string.length - count > maxLength) {
            maxLength = string.length - count
        }
    }
    File(outputName).bufferedWriter().use {
        for (string in text) {
            val words = mutableListOf<String>()
            for (word in string.split(" ")) {
                if (word != "") {
                    words.add(word)
                }
            }
            if (words.size == 1) {
                it.write(words.joinToString(separator = ""))
            } else {
                if (words.isNotEmpty()) {
                    do {
                        var flag = true
                        var i = 0
                        for (word in string.split(" ")) {
                            if (word != "") {
                                if (flag) {
                                    flag = false
                                } else {
                                    if (words.joinToString(separator = "").length == maxLength) {
                                        break
                                    } else {
                                        words[i] = " " + words[i]
                                    }
                                }
                                i++
                            }
                        }
                    } while (words.joinToString(separator = "").length < maxLength)
                    it.write(words.joinToString(separator = ""))
                }
            }
            it.newLine()
        }
    }
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    val text = File(inputName).readLines()
    for (string in text) {
        for (word in string.split(" ")) {
            var cleanWord = ""
            for (char in word) {
                if (char.toLowerCase() in 'a'..'z' || char.toLowerCase() in 'а'..'ё') {
                    cleanWord += char.toLowerCase()
                } else {
                    if (cleanWord.isNotEmpty()) {
                        if (cleanWord in result.keys) {
                            result[cleanWord] = result[cleanWord]!! + 1
                        } else {
                            result[cleanWord] = 1
                        }
                    }
                    cleanWord = ""
                }
            }
            if (cleanWord.isNotEmpty()) {
                if (cleanWord in result.keys) {
                    result[cleanWord] = result[cleanWord]!! + 1
                } else {
                    result[cleanWord] = 1
                }
            }
        }
    }
    if (result.size > 20) {
        var max: Int
        var maxName: String
        val map = mutableMapOf<String, Int>()
        var count = 0
        do {
            max = Int.MIN_VALUE
            maxName = ""
            for ((key, value) in result) {
                if (value > max) {
                    max = value
                    maxName = key
                }
            }
            map.put(maxName, max)
            result.remove(maxName)
            count++
        } while (count < 20)
        return map
    }
    return result
}

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val text = File(inputName).readLines()
    File(outputName).bufferedWriter().use {
        for (string in text) {
            var count = string.split(" ").size
            for (word in string.split(" ")) {
                for (char in word) {
                    if (dictionary[char.toLowerCase()] != null) {
                        if (char == char.toLowerCase()) {
                            it.write(dictionary[char.toLowerCase()]!!.toLowerCase())
                        } else {
                            if (dictionary[char.toLowerCase()]!!.length == 1) {
                                if (dictionary[char.toLowerCase()] == dictionary[char.toLowerCase()]!!.toUpperCase()) {
                                    it.write(dictionary[char.toLowerCase()]!!.toUpperCase())
                                } else {
                                    it.write(dictionary[char.toLowerCase()]!!.toLowerCase())
                                }
                            } else {
                                var flag = true
                                if (char == char.toUpperCase()) {
                                    for (sim in dictionary[char.toLowerCase()]!!) {
                                        if (flag) {
                                            it.write(sim.toUpperCase().toString())
                                            flag = false
                                        } else {
                                            it.write(sim.toLowerCase().toString())
                                        }
                                    }
                                } else {
                                    it.write(dictionary[char.toLowerCase()])
                                }
                            }
                        }
                    } else {
                        if (dictionary[char.toUpperCase()] != null) {
                            if (char == char.toLowerCase()) {
                                it.write((dictionary[char.toUpperCase()]!!).toLowerCase())
                            } else {
                                if (dictionary[char.toUpperCase()]!!.length == 1) {
                                    if (dictionary[char.toUpperCase()] == dictionary[char.toUpperCase()]!!.toUpperCase()) {
                                        it.write(dictionary[char.toUpperCase()]!!.toUpperCase())
                                    } else {
                                        it.write(dictionary[char.toUpperCase()]!!.toLowerCase())
                                    }
                                } else {
                                    var flag = true
                                    if (char == char.toUpperCase()) {
                                        for (sim in dictionary[char.toUpperCase()]!!) {
                                            if (flag) {
                                                it.write(sim.toUpperCase().toString())
                                                flag = false
                                            } else {
                                                it.write(sim.toLowerCase().toString())
                                            }
                                        }
                                    } else {
                                        it.write(dictionary[char.toUpperCase()])
                                    }
                                }
                            }
                        } else {
                            it.write(char.toString())
                        }
                    }
                }
                count--
                if (count != 0) {
                    it.write(" ")
                }
            }
            it.newLine()
        }
    }
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    val text = File(inputName).readLines()
    val list = mutableListOf<String>()
    var count = 0
    var maxLength = -1
    for (string in text) {
        var flag = true
        val letters = mutableSetOf<Char>()
        for (char in string) {
            if (char.toLowerCase() !in letters) {
                letters.add(char.toLowerCase())
            } else {
                flag = false
            }
        }
        if (flag) {
            if (string !in list) {
                list.add(string)
            }
            if (string.length > maxLength) {
                maxLength = string.length
            }
        }
    }
    for (element in list) {
        if (element.length == maxLength) {
            count++
        }
    }
    File(outputName).bufferedWriter().use {
        for (element in list) {
            if (element.length == maxLength) {
                if (count != 1) {
                    it.write("$element, ")
                    count--
                } else {
                    it.write(element)
                }
            }
        }
    }
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val text = File(inputName).readLines()
    File(outputName).bufferedWriter().use {
        it.write("<html><body>")
        it.write("<p>")
        for (string in text) {
            if (string.isEmpty()) {
                it.write("</p>")
                it.write("<p>")
            }
            var i = false
            var b = false
            var s = false
            var bi = false
            if (string.length > 3) {
                for (k in 0..(string.length - 3)) {
                    if (string[k] == '*' && string[k + 1] == '*' && string[k + 2] == '*' && !b && !i) {
                        if (!bi) {
                            it.write("<b><i>")
                            bi = true
                        } else {
                            it.write("</i></b>")
                            bi = false
                        }
                    }
                    if (string[k] != '~' && string[k] != '*') {
                        it.write(string[k].toString())
                    }
                    if (string[k] == '~' && string[k + 1] == '~') {
                        if (!s) {
                            it.write("<s>")
                            s = true
                        } else {
                            it.write("</s>")
                            s = false
                        }
                    }
                    if (!bi) {
                        if (string[k] == '*' && string[k + 1] == '*' && string[k + 2] != '*') {
                            if (!b) {
                                it.write("<b>")
                                b = true
                            } else {
                                it.write("</b>")
                                b = false
                            }
                        }
                        if (string[k] == '*' && string[k + 1] != '*' && string[k - 1] != '*') {
                            if (!i) {
                                it.write("<i>")
                                i = true
                            } else {
                                it.write("</i>")
                                i = false
                            }
                        }
                    }
                }
                if (string[string.length - 2] == '*') {
                    if (i) {
                        it.write("</i>")
                    }
                } else {
                    if (string[string.length - 2] != '~')
                        it.write((string[string.length - 2].toString()))
                }
                it.write(string[string.length - 1].toString())
            } else {
                if (string.length >= 2) {
                    if (string[0] == '*' && string[string.length - 1] == '*') {
                        it.write("<i>")
                        if (string.length == 3) {
                            it.write(string[1].toString())
                        }
                        it.write("</i>")
                    }
                } else {
                    it.write(string)
                }
            }
            it.newLine()
        }
        it.write("</p>")
        it.write("</body></html>")
    }
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    val space = mutableListOf<String>()
    var line = ""
    var count = 1
    val digitList = rhv.toString().toMutableList()
    val valueList = mutableListOf<Int>()
    val spaceBefore = StringBuilder()
    for (i in 0..((digitNumber(rhv)) - 1)) {
        spaceBefore.append(" ")
    }
    for (i in 0..(digitNumber(lhv) - digitNumber(rhv) + spaceBefore.length - 2)) {
        space.add(" ")
    }
    for (i in 0..((digitNumber(rhv)) - 1 + digitNumber(lhv))) {
        line += "-"
    }
    File(outputName).bufferedWriter().use {
        it.write(spaceBefore.toString())
        it.write("$lhv")
        it.newLine()
        it.write("*")
        it.write(space.joinToString(separator = ""))
        it.write("$rhv")
        it.newLine()
        it.write(line)
        it.newLine()
        for (i in 0..(digitNumber(rhv) - 1)) {
            val value = lhv * (digitList.last().toInt() - 48)
            valueList += value * count
            if (i != 0) {
                it.write("+")
                space.remove(space.first())
                if (digitList.size != 1) {
                    it.write((space - " ").joinToString(separator = ""))
                }
                it.write("$value")
            } else {
                it.write(spaceBefore.toString())
                it.write("$value")
            }
            if (space.isNotEmpty()) {
                space.remove(space.first())
            }
            it.newLine()
            count *= 10
            digitList.remove(digitList.last())
        }
        it.write(line)
        it.newLine()
        val result = valueList.sum()
        it.write(" $result")
    }
}

/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

