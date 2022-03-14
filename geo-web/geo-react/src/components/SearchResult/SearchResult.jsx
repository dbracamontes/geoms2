import React, { Component } from "react";
// import PropTypes from 'prop-types';
import axios from 'axios';
import StatePagination from "../../state/StatePagination";

class SearchResult extends Component {

  state = {
    states: [],
    loading: true,
    startItem:0,
    lastItem:9,
    itemsToShow:9
  }

  constructor(props) {
    super(props);
    this.clickState = this.clickState.bind(this);
    this.clickPage = this.clickPage.bind(this);
  }

  clickState() {
    console.log("clicking card");
    // it should redirect to a new component that shows state info
    axios.get('http://localhost:8080/geo/cities/1')
      .then(res => {
        const states = res.data;
        this.setState({ states: states});
      });
  }

  clickPage(currentPage) {
    console.log(this.state.startItem);
   const lastItem = currentPage * this.state.itemsToShow - 1;

    this.setState({
      startItem: lastItem - this.state.itemsToShow,
      lastItem: lastItem
    })
  } 

  componentDidMount() {
    console.log("componetn did mount");
    axios.get('http://localhost:8080/geo/states')
      .then(res => {
        const states = res.data;
        this.setState({ states:states, loading:false});
      })
      .catch(error =>{
        console.log("errror component idd mount");
        console.log(error);
      });
  }

  render() {
    if(this.state.loading){
      return <div>loading...</div>
    } else {
      let columns=[];
      this.state.states.slice(this.state.startItem,this.state.lastItem).forEach((state,index) => {
      // this.state.states.forEach((state,index) => {
        columns.push(
        <div className="col-sm" key={index}>
          <div className="card" id="state">
            <img className="card-img-top" src={"http://127.0.0.1:9001/states/aguascalientes/Aguascalientes_in_Mexico.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minio%2F20201117%2F%2Fs3%2Faws4_request&X-Amz-Date=20201117T023704Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=990d2450ec420c01c528ddd5a3fdd06624b421e02adea2719291d196af1ab57e"} alt={state.name} />
            <div className="card-body">
              <h4 className="card-title">{state.name}</h4>
              <p className="card-text">
                {index}
              </p>
              <button onClick={this.clickState} className="card-link btn btn-primary">Card link</button>
            </div>
          </div>
        </div>);
        
        // force wrap to next row every 3 columns
        if ((index+1)%3===0) {columns.push(<div className="w-100" key={null}></div>)}
      })
      
      return (  
        <div className="row" key="states">
		      {columns}
          <StatePagination 
            states={this.state.states} 
            itemsToShow={9}
            clickPage={this.clickPage}
          />
			  </div>
      );         
    }
  }
}
/*State.propTypes = {
  name: PropTypes.element.isRequired
};*/

export default SearchResult;