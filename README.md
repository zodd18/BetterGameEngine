# BetterGameEngine

This project is a fork of my old game engine https://github.com/zodd18/Simple-Game-Engine.git

# Improvements

Clean code, better architecture

- Now the game itself is just a package in the project, it's launched by the GController. In this way, in order to develop a game with this engine, the developer does not need to edit anything about the game engine (previous version lacked of good architecture and this wasn't possible).

- Now rendering layer exists so you can choose the order in which layers containing game components (aka GComponent objects) will be rendered.

# Current state

BetterGameEngine is still being developed.
