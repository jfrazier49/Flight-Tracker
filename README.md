# Flight Finder Application

This project utilizes a GraphADT to help users find flights or sets of flights from one location to another based on their search criteria. Users can search for either the cheapest flights or the flights with the shortest distance.

## Overview

Each node in the graph represents an airport, and the edges symbolize flights between airports. The weight of each edge can represent either the price of the flights or the distance of the flight. Through a JavaFX interface, users can select their starting and ending airports, as well as their search criteria (price or distance). The application then uses Dijkstra’s algorithm to find and display the optimal flight options based on the user's preferences.

## Features

- **Graph Representation:** Airports are represented as nodes and flights as edges, with weights corresponding to either price or distance.
- **Search Criteria:** Users can choose to search based on the cheapest flights or the shortest distance.
- **Dijkstra’s Algorithm:** The application uses Dijkstra’s algorithm to compute the optimal flight options.
- **JavaFX Interface:** A user-friendly interface allows users to select airports, search criteria, and view results.
- **DOT Files:** Two DOT files are used to represent the graph with weights corresponding to price and distance respectively.
