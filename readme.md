Problem Statement
============================
You have a text document, which is your input. We can assume A sample is given blow:
words.txt:
I am a software engineer.
And I am enjoying my work at EA.

For each word, you can identify it with a position (row number, column number).
For example, you have the following positions for "I":
(0, 0), (1, 1)

And another input of your program is a list of pairs of words, which is also stored in a text file:
pairs.txt:
I am
work at
EA And

For each pair, you have to determine whether they appear in the document adjacently
with the exact order and print out their positions (if they appear in multiple places).
The solution should be both time and space efficient, should also be scalable to process
big text file and long list of pairs of words, and finally should be easily expanded
to solve the problem of k adjacent words (k>2).

So your output for the sample inputs are:
I am : (0, 0) (0, 1); (1, 1), (1, 2);
work at: (1, 5), (1, 6);
EA And: null

Software Requirement
==========================
##### 1 JDK 1.8 (As I am using JDK 1.8 stream for Parallel processing)
##### 2 Maven 3.0.x

Build Instruction and Execution
==========================
##### 1 go to command prompt and pass the following command for building
    mvn clean install
##### 2 once build complete go to "target" folder and copy "EA-bin.tar" to the distnation folder
    cd target
    mkdir ~/releases
    cp EA-bin.tar ~/releases
##### 3 Finally extract EA-bin.tar into destination folder
    cd ~/releases
    tar -xvf EA-bin.tar

##### 4 finally execute the program using following command
    java -jar CodingChallange-1.0-SNAPSHOT.jar <<Big File Path>> <<Pair File Path>> [Optional number to denote K adjacent word]
    if not specified K adjacent word will be 2
