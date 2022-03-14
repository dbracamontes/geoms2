import React, { Component } from 'react';
import './State.css';
import imagen from './img_avatar1.png';
import PropTypes from 'prop-types';
import axios from 'axios';

class State extends Component {

  state = {
    state: undefined
  }

  constructor(props) {
    super(props);
    this.clickState = this.clickState.bind(this);
  }

  clickState() {
    axios.get('http://localhost:8080/geo/cities/1')
      .then(res => {
        const state = res.data;
        this.setState({ state });
        console.log(res);
        console.log("state" + state);
      })
  }

  render() {
    return (
      <div className="card" id="state">
        <img className="card-img-top" src={imagen} alt={this.props.name} />
        <div className="card-body">
          <h4 className="card-title">{this.props.name}</h4>
          <p className="card-text">
            {this.props.name}
                    </p>
          <button onClick={this.clickState} className="card-link btn btn-primary">Card link</button>
        </div>
      </div>
    );
  }

}

State.propTypes = {
  name: PropTypes.element.isRequired
};

export default State;