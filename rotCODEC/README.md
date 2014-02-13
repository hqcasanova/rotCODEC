rotCODEC 0.15
=============

A no-frills program to encode/decode a given piece of text using the 
ROT 13/5 cipher. See [this Wikipedia article](http://en.wikipedia.org/wiki/ROT13) 
for more information on ROT13 and ROT5.

Features
--------

The program has two modes of operation: asynchronous (command-line) and synchronous 
(graphical interface). The latter supports:
- Pasting from the clipboard.
- Text is encoded/decoded as it is typed/pasted.   

Minimum requirements
--------------------

Java SE Runtime Environment. If not already installed, you can download it from [Oracle](http://java.com/en/download/index.jsp). 
The application was developed with version 7 but may work with other versions.

Command-line mode
-----------------

Syntax: `rotCODEC [list_of_files | sync]`

#### Examples

`java -jar rotCODEC.jar challenge.txt output.txt`

Enters the so-called asynchronous mode of operation. Takes the text in 
"challenge.txt", applies the ROT13/5 cipher and saves the 
result into "output.txt".

`java -jar rotCODEC.jar sync`

Enters synchronous mode of operation. Launches a window into which the user
can type some text. As the user types, the program automatically ciphers or 
deciphers it on the fly. It supports pasting from the clipboard.

`java -jar rotCODEC.jar`

Same as above.

Feedback
--------

This software is no longer maintained. Nevertheless, if you have any 
comments, feel free to leave a message on my [website](http://www.hqcasanova.com).