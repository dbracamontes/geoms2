import React, { Component } from "react";
// import PropTypes from 'prop-types';
class SearchResult extends Component {

  state = {
    totalItems:0,
    pages:0,
    currentPage:1,
    startItem:0,
    endItem:0
  }

  constructor(props) {
    super(props);
    this.clickPage = this.clickPage.bind(this);
  }

  clickPage(page){
    // console.log("test click page");
    // console.log(event);
    // this.setState({
     // currentPage: event
    // });
    this.props.clickPage(page);
  }

  componentDidMount() {
    console.log(this.props.states);
    const statesLength = this.props.states.length;
    const itemsToShow = this.props.itemsToShow;
    const pages = (statesLength / itemsToShow) + (statesLength % itemsToShow === 0 ? 0 : 1 );
    this.setState({
        totalItems:statesLength,
        pages:pages,
        startItem: (this.state.currentPage * itemsToShow),
        endItem: (this.state.currentPage * itemsToShow + itemsToShow) 
    });
  }

  getPagination(){
    const paginatorLinks = [];
    for(var it=1;it<this.state.pages;it++){
      const page = it;
      paginatorLinks.push(
        <li className="page-item">
          <a className="page-link" onClick={() => this.clickPage(page)} href="#">{page}</a>
          </li>
      );
    }

    return paginatorLinks;
  }

  render() {
      return (  
        <div className="row" key="states">
          <nav aria-label="Page navigation example">
            <ul className="pagination">
              <li className="page-item">
                <a className="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                  <span className="sr-only">Previous</span>
                </a>
              </li>
              {this.getPagination()}
              <li className="page-item">
                <a className="page-link" href="#"   aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                  <span className="sr-only">Next</span>
                </a>
              </li>
            </ul>
          </nav>
		</div>
    );         
    }
}

/*State.propTypes = {
  name: PropTypes.element.isRequired
};*/

export default SearchResult;