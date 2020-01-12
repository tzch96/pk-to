# Changelog
All notable changes to this project will be documented in this file.

## 0.4.0 - 2019-01-15

### Changed

- Changed function plugin loading to iterate over plugins directory instead of loading from single jar

### Added

- A web application implementation in Spring Boot

## 0.3.0 - 2019-12-18

### Changed

- Moved functions to plugins folder as compiled jars
- Changed code to use Singleton creational pattern

### Added

- Dynamically loading function plugins at runtime
- Log4j2 error logger

### Removed

- Removed Functions class

## 0.2.0 - 2019-11-28

### Changed

- Moved two-argument operation calculation function to Operators class
- Renamed operands for two-argument operations as leftOperand and rightOperand
- Edited exception throwing when dividing by zero

### Added

- Added support for single-argument functions, currently defined in Functions.java

## 0.1.0 - 2019-11-25

### Added

- Added an expression tokenizer to convert an input string to an ArrayList of tokens
- Added an infix (e.g. 2 + 5 * 3) to postfix (e.g. 2 5 3 * +) converter implementing [Djikstra's shunting yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
- Added a postfix expression evaluator