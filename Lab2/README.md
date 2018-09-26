# KDM-Lab2

CS5560 Knowledge Discovery Management - Lab Assignment 2



# Introduction

For CS5560 Knowledge Discovery Management, Lab Assignment 2 will be working on 30 abstracts I have found to be used as data sets for my team's project that has been changed to Mental Illness. These abstracts are unique to the other team members' abstracts in order to retrieve and process as wide of range of data as possible. The abstracts, as opposed to Lab Assignment 1, were retrieved using a modified ICP under the search of `mental+illness+treatment` from NCBI's PubMed.

The project's goal is to construct a Knowledge Graph for Mental Illness. These 30 abstracts, for this assignment, will undergo Spark processing with basic Natural Language Processing (NLP) such as lemmatization. Then the abstracts will have Valid Word Filtering and Valid Medical Word Filtering performed on them. Additionally, the top 20 Term Frequency (TF) words will be found with their associated Word2Vec vectors.



## Tasks

For the 30 abstracts relevant to the project topic, Mental Illness perform the following:

1. Perform Basic NLP (Tokenization, Lemmatization) and provide the statistics
2. Perform Valid Word Filtering & Valid Medical Word Filtering and provide the statistics
3. Find the top 20 TF-IDF terms and provide the Word2Vec vectors
4. Fid the top TF-IDF of the medical words and provide the Word2Vec Vectors



## Setup

The following was provided by the instructor via zip files:

* WordNet-3.0 
* WinUtils.exe

[JetBrains IntelliJ](https://www.jetbrains.com/idea/) was used as the IDE to complete the assignment. This assignment required using the Java and Scala programming languages.



# WordNet Count

For the 30 abstracts, the total word count of each abstract was obtained and the total words recognized by WordNet was obtained. The synonyms of the words recognized by WordNet were also found.

![getWordCount](../docs/Lab2/wordcount/getWordCount.gif)



# Source Code

The source code for this ICP was provided by the class instructor Mayanka ChandraShekar: [mckw9@mail.umkc.edu](mckw9@mail.umkc.edu)