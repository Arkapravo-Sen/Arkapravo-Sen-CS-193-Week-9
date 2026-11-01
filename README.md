# Java Terminal Chess

A two-player terminal chess game built in Java to provide a lightweight, local strategy game experience that runs instantly in any command-line environment.

## The Problem

As a computer science student, I enjoy playing strategy games like chess with friends between classes, but many online platforms require accounts, heavy web browsers, or constant internet connections[cite: 5]. I care about solving this because I want to build a lightweight, local terminal-based chess game that runs instantly in any command-line environment and helps me deepen my understanding of object-oriented inheritance, complex move validation, and 2D board state management in Java[cite: 3, 5, 7].

## How It Works

This application is a command-line interface (CLI) program that enables two players to play a head-to-head game of chess directly within the terminal[cite: 7]. The application maintains an 8x8 grid board object, alternates turns between White and Black, parses player coordinate inputs, validates piece movement rules and checks, and handles game-ending checkmate or stalemate conditions[cite: 7, 9]. If you have a diagram, embed it here:

![System diagram](docs/diagram.png)

## Tech Stack

- **Language:** Java (JDK 17+)[cite: 3, 11]
- **Libraries:** `java.util.Scanner`, `java.util.ArrayList`[cite: 11]
- **External tools:** Standard terminal command line / command prompt[cite: 7]

## Setup

```bash
git clone [https://github.com/Arkapravo-Sen/Arkapravo-Sen-CS-193-Week-9](https://github.com/Arkapravo-Sen/Arkapravo-Sen-CS-193-Week-9)
cd Arkapravo-Sen-CS-193-Week-9
javac src/Main.java
java src.Main