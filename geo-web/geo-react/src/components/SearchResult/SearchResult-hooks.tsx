import * as React from "react";
import axios from 'axios';
import StatePagination from "../../state/StatePagination";

interface Props {
}

interface State {
  data:any;
  loading:boolean;
  startItem:number;
  lastItem:number;
  itemsToShow:number;
}

const SearchResult: React.FC<Props> = (props: Props) => {
  const initState:State = {
    data:[],
    loading: true,
    startItem: 0,
    lastItem: 9,
    itemsToShow:9
  };
  const [states,setStates] = React.useState(
    initState
  ); 

  
  React.useEffect(()=>{
    axios.get('http://localhost:8080/geo/states')
      .then(res => {
        setStates({
          ...states,
          data: res.data
        });
        console.log(states);
      });
  },[]);

  const statesElements = () => {
    return states.data.map(
      item => {
      return (
      <div className="card" id="state">
        <img className="card-img-top" src={item.name} alt={item.name} />
        <div className="card-body">
          <h4 className="card-title">{item.name}</h4>
          <p className="card-text">
            {item.name}
          </p>
        </div>
      </div>)
      });
  };


  if(states.loading){

  }
  return (
    <React.Fragment>
      { statesElements }
    </React.Fragment>
  );
};

export default SearchResult; 