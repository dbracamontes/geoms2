import React, { Component } from 'react';
import './App.css';
import Header from './components/Header/Header.jsx';
import SearchBar from './components/SearchBar/SearchBar.jsx';
import SearchResult from './components/SearchResult/SearchResult';
import 'typeface-roboto';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Router>
        <main className="container">
          <Header />
          <SearchBar />
          <SearchResult />
        </main>
        <Route>
          <Route path="/state" element={<SearchResult/>} />
        </Route>
      </Router>
    );
  }
}

export default App;
