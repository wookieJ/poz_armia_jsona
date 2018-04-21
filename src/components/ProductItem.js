import React, { Component } from 'react'
import '../styles/main.css'
import { connect } from "react-redux";
import { detailsFetchData } from "../actions/index";


class ProductItem extends Component {

  handleClick = () => {
    const url = 'https://poz-armia-jsona.herokuapp.com//offers/'
    console.log(url + this.props.id);
    
    this.props.fetchDetails(url + this.props.id)
    console.log(this.props.details);
    
  }

  
  render() {
    const url = this.props.img
    console.log(url);
    
    
    return (
      <div className = "productItem" onClick = {this.handleClick}>
        <div className = 'image'> 
            <img src = {url}></img>
           </div>
          <p>{this.props.name}</p>
          <p>cena:{this.props.price.amount}{this.props.price.currency}</p>
        
    </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
      details: state.details,
      detailsHasErrored: state.detailsHasErrored,
      detailsIsLoading: state.detailsIsLoading
  }
}

const mapDispatchToProps = (dispatch) => {
  return{
      fetchDetails: (id) => dispatch(detailsFetchData(id))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProductItem)