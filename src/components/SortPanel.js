import React, { Component } from 'react'
import '../styles/main.css'
import { productsFetchData } from "../actions/index";
import { connect } from "react-redux";

class SortPanel extends Component {

  handleClick(term){
    const url = 'https://poz-armia-jsona.herokuapp.com//offers?name='
    console.log(url + term + '&sort=+price');
    this.props.fetchData(url + term + '&sort=+price')
  }

  render() {
    console.log(this.props.term);
    
    return (
      <div className = 'sortPanel'>
        <p style={{fontWeight: 'bold'}} >Sortuj po:  <span onClick={this.handleClick(this.props.term)} style={{marginLeft: '0.5em'}}>Cena ⇗</span> <span style={{marginLeft: '0.5em'}}>Cena ⇘</span></p>
      
      </div>
    )
  }
}


const mapDispatchToProps = (dispatch) => {
  return{
      fetchData: (searchTerm) => dispatch(productsFetchData(searchTerm))
  }
}

export default connect(null, mapDispatchToProps)(SortPanel)