
# Klivvr Android Internship Assignment

This repository contains my solution for the Android Internship assignment. The task involves creating an Android application that efficiently handles and displays a large dataset of cities

## Project UI Overview:
![Mockup 2](https://github.com/user-attachments/assets/eaf305fa-b599-4223-873a-8d5b7ab78ba1)


## Features

- Dataset: The application loads a list of approximately 200,000 city entries provided in JSON format.
- Search and Filter: Implements an optimized search algorithm to filter cities by a given prefix. The search is case-insensitive and designed to be more efficient than a linear search, ensuring fast runtime during filtering.
- UI and UX: Displays the filtered cities in a responsive, scrollable list, sorted alphabetically by city name and country code. The list updates dynamically as the user types or modifies the filter.
- Kotlin and Android Jetpack: The application is developed using Kotlin and adheres to modern Android development practices, including the use of Android Jetpack components.
- Restrictions: The solution avoids database implementations and only uses third-party libraries for JSON serialization and dependency injection.
- Multiple sceen sizes are supported

## Searching Method

### Trie Algorithm
The time complexity of searching in a Trie data structure depends on the length of the word being searched , so I managed to use trie for searching in the city name to avoid linear time
Search Operation:

Time Complexity: 
𝑂(𝐿) 
- Explanation: In a Trie, each character of the word corresponds to one level in the Trie. Therefore, the time complexity is proportional to the length of the word 
"L is the number of characters in the word"

1.Trie Node Intialization: 
```Kotlin
class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isEndOfWord: Boolean = false
) {
    var word: String? = null
}
```
 2. Data Insertion to trie:

 ```Kotlin
class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        word.forEach { char ->
            current = current.children.computeIfAbsent(char) { TrieNode() }
        }
        current.isEndOfWord = true
        current.word = word
    }
}
```
 3. Searching about a prefix string into the trie:

 ```Kotlin
 private fun findPrefixNode(prefix: String): TrieNode? {
        var current = root
        for (char in prefix) {
            current = current.children[char] ?: return null
        }
        return current
    }


    private fun collectAllWords(node: TrieNode, result: MutableList<String>) {
        if (node.isEndOfWord) {
            node.word?.let { result.add(it) }
        }
        for (childNode in node.children.values) {
            collectAllWords(childNode, result)
        }
    }

    fun getWordsWithPrefix(prefix: String): List<String> {
        val result = mutableListOf<String>()
        val prefixNode = findPrefixNode(prefix) ?: return result
        collectAllWords(prefixNode, result)
        return result
    }
``` 
<h2>💻 Built with</h2>

Technologies used in the project:

*   Kotlin
*   XML
*   Hilt (Dependency Injection)
*   Gson
*   Clean Architecture
*   Repository Pattern
*   MVVM
*   Multi Module
*   Deep Linking
*   Trie Data Stracture
*   View Binding



## Contact
If you have any questions or feedback, feel free to contact me at elatarmohamed158@gmail.com
