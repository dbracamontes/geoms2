import React, { Component } from 'react';
import logo from '../../logo.svg';
import './Header.css';

class Header extends Component{

    render(){
        return(
            <header className="row" key="header">
                <img src={logo} className="App-logo" alt="logo" />
            </header>
        );
    }

}

export default Header;
