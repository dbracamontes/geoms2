import React, { Component } from 'react';

class SearchBar extends Component {
  render() {
    return (
      <nav className="row" key="searchBar">
        <div className="input-group mb-3">
          <input type="text" className="form-control" placeholder="Search by State" />
          <div className="input-group-append">
            <span className="input-group-text"><i className="fas fa-search"></i></span>
          </div>
        </div>
      </nav>
    );
  }
}

export default SearchBar;