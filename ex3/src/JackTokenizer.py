"""
This file is part of nand2tetris, as taught in The Hebrew University, and
was written by Aviv Yaish. It is an extension to the specifications given
[here](https://www.nand2tetris.org) (Shimon Schocken and Noam Nisan, 2017),
as allowed by the Creative Common Attribution-NonCommercial-ShareAlike 3.0
Unported [License](https://creativecommons.org/licenses/by-nc-sa/3.0/).
"""
import typing
from typing import List, Any


class JackTokenizer:
    """Removes all comments from the input stream and breaks it
    into Jack language tokens, as specified by the Jack grammar.

    # Jack Language Grammar

    A Jack file is a stream of characters. If the file represents a
    valid program, it can be tokenized into a stream of valid tokens. The
    tokens may be separated by an arbitrary number of whitespace characters,
    and comments, which are ignored. There are three possible comment formats:
    /* comment until closing */ , /** API comment until closing */ , and
    // comment until the line’s end.

    - ‘xxx’: quotes are used for tokens that appear verbatim (‘terminals’).
    - xxx: regular typeface is used for names of language constructs
           (‘non-terminals’).
    - (): parentheses are used for grouping of language constructs.
    - x | y: indicates that either x or y can appear.
    - x?: indicates that x appears 0 or 1 times.
    - x*: indicates that x appears 0 or more times.

    ## Lexical Elements

    The Jack language includes five types of terminal elements (tokens).

    - keyword: 'class' | 'constructor' | 'function' | 'method' | 'field' |
               'static' | 'var' | 'int' | 'char' | 'boolean' | 'void' | 'true' |
               'false' | 'null' | 'this' | 'let' | 'do' | 'if' | 'else' |
               'while' | 'return'
    - symbol: '{' | '}' | '(' | ')' | '[' | ']' | '.' | ',' | ';' | '+' |
              '-' | '*' | '/' | '&' | '|' | '<' | '>' | '=' | '~' | '^' | '#'
    - integerConstant: A decimal number in the range 0-32767.
    - StringConstant: '"' A sequence of Unicode characters not including
                      double quote or newline '"'
    - identifier: A sequence of letters, digits, and underscore ('_') not
                  starting with a digit. You can assume keywords cannot be
                  identifiers, so 'self' cannot be an identifier, etc'.

    ## Program Structure

    A Jack program is a collection of classes, each appearing in a separate
    file. A compilation unit is a single class. A class is a sequence of tokens
    structured according to the following context free syntax:

    - class: 'class' className '{' classVarDec* subroutineDec* '}'
    - classVarDec: ('static' | 'field') type varName (',' varName)* ';'
    - type: 'int' | 'char' | 'boolean' | className
    - subroutineDec: ('constructor' | 'function' | 'method') ('void' | type)
    - subroutineName '(' parameterList ')' subroutineBody
    - parameterList: ((type varName) (',' type varName)*)?
    - subroutineBody: '{' varDec* statements '}'
    - varDec: 'var' type varName (',' varName)* ';'
    - className: identifier
    - subroutineName: identifier
    - varName: identifier

    ## Statements

    - statements: statement*
    - statement: letStatement | ifStatement | whileStatement | doStatement |
                 returnStatement
    - letStatement: 'let' varName ('[' expression ']')? '=' expression ';'
    - ifStatement: 'if' '(' expression ')' '{' statements '}' ('else' '{'
                   statements '}')?
    - whileStatement: 'while' '(' 'expression' ')' '{' statements '}'
    - doStatement: 'do' subroutineCall ';'
    - returnStatement: 'return' expression? ';'

    ## Expressions

    - expression: term (op term)*
    - term: integerConstant | stringConstant | keywordConstant | varName |
            varName '['expression']' | subroutineCall | '(' expression ')' |
            unaryOp term
    - subroutineCall: subroutineName '(' expressionList ')' | (className |
                      varName) '.' subroutineName '(' expressionList ')'
    - expressionList: (expression (',' expression)* )?
    - op: '+' | '-' | '*' | '/' | '&' | '|' | '<' | '>' | '='
    - unaryOp: '-' | '~' | '^' | '#'
    - keywordConstant: 'true' | 'false' | 'null' | 'this'

    Note that ^, # correspond to shiftleft and shiftright, respectively.
    """

    """Removes all comments from the input stream and breaks it
    into Jack language tokens, as specified by the Jack grammar.
    """

    """Removes all comments from the input stream and breaks it
    into Jack language tokens, as specified by the Jack grammar.
    """

    def __init__(self, input_stream: str) -> None:
        """Initialize the JackTokenizer with the input stream.

        Args:
            input_stream (str): The input stream containing Jack language code.
        """
        self.input_lines = input_stream.splitlines()
        self.input = []  # Array to store tokens
        self.symbol_keys = {'{', '}', '(', ')', '[', ']', '.', ',', ';', '+', '-', '*', '/', '&',
                            '|', '<', '>', '=', '~'}
        self.keyword_keys = {"class", "constructor", "function", "method", "field", "static", "var", "int",
                             "char", "boolean", "void", "true", "false", "null", "this", "let", "do", "if",
                             "else", "while", "return"}
        self.process_lines()

    def process_lines(self) -> None:
        """Process input lines to tokenize them."""
        for line in self.input_lines:
            line_without_comments = self.remove_single_line_comments(line)
            self.tokenize_line(line_without_comments)

    def tokenize_line(self, line: str) -> None:
        """Tokenize a single line.

        Args:
            line (str): Input line to tokenize.
        """
        tokens = []
        token = ''
        for char in line:
            if char in self.symbol_keys:
                if token:
                    tokens.append(token)
                    token = ''
                tokens.append(char)
            elif char == ' ':
                if token:
                    tokens.append(token)
                    token = ''
            else:
                token += char
        if token:
            tokens.append(token)

        # Filter out empty tokens
        tokens = [token for token in tokens if token]

        # Add tokens to input
        self.input.extend(tokens)

    def remove_single_line_comments(self, line: str) -> str:
        """Remove single line comments from a single line.

        Args:
            line (str): Input line.

        Returns:
            str: Line with single line comments removed.
        """
        comment_index = line.find('//')
        if comment_index != -1:
            line = line[:comment_index]
        return line.strip()


# Create an instance of JackTokenizer with a sample input stream
input_stream = """class Main {
        function void main() {
            var int x;
            let x = 5;
            do Output.printInt(x);
            return;
        }
    }"""
tokenizer = JackTokenizer(input_stream)

# Access the tokenized output through the input attribute
tokens = tokenizer.input

# Print the resulting tokens
print(tokens)
