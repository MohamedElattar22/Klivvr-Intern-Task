package com.mobjoy.klivvrinternshiptask.searchalgorithm

import android.os.Build
import androidx.annotation.RequiresApi


class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isEndOfWord: Boolean = false
) {
    var word: String? = null
}

class Trie {

    private val root = TrieNode()

    @RequiresApi(Build.VERSION_CODES.N)
    fun insert(word: String) {
        var current = root
        word.forEach { char ->
            current = current.children.computeIfAbsent(char) { TrieNode() }
        }
        current.isEndOfWord = true
        current.word = word
    }


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
}

