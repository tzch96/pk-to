# Changelog
All notable changes to this project will be documented in this file.

## 0.1.0 - 2019-11-23

### Added

- Added an expression tokenizer to convert an input string to an ArrayList of tokens
- Added an infix (e.g. 2 + 5 * 3) to postfix (e.g. 2 5 3 * +) converter implementing [Djikstra's shunting yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
- 