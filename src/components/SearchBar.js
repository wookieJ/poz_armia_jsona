import React, { Component } from "react";

import { connect } from 'react-redux'
import { productsFetchData } from "../actions/index";
import PropTypes from "prop-types";
import '../styles/main.css'
import SortPanel from "./SortPanel";


const buttonStyle = {
    width: '50%',
    display: 'inline',
    border: '1px solid gray',
    
}

class SearchBar extends Component {
state = {
    searchTerm: '',
}




onChange = e => this.setState({searchTerm: e.target.value})

handleSubmit = (e) => {
    e.preventDefault()
    const url = 'https://poz-armia-jsona.herokuapp.com//offers?phrase='
    console.log(url + this.state.searchTerm);
    this.props.fetchData(url + this.state.searchTerm)
}


  render() {
    return (
        <div className = 'formDiv'>
            <form onSubmit = {this.handleSubmit}>
                <span className = 'logo'> emojillegr<span id='emoji'>🤑</span> </span>
                <input
                    className='input'
                    type="text"
                    id="searchTerm"
                    name="searchTerm"
                    placeholder="Czego szukasz? 🤷"
                    onChange = {this.onChange}
                />
                
                <button className = 'button' type = 'submit' >Szukaj</button>
            </form>   
        {this.props.products.length > 0 && <SortPanel term = {this.state.searchTerm}/> }    
            </div>
    );
  }
}

SearchBar.propTypes = {
    fetchData: PropTypes.func.isRequired,
    products: PropTypes.array.isRequired,
    hasErrored: PropTypes.bool.isRequired,
    isLoading: PropTypes.bool.isRequired
}


const mapStateToProps = (state) => {
    return {
        products: state.products,
        hasErrored: state.hasErrored,
        isLoading: state.isLoading
    }
}

const mapDispatchToProps = (dispatch) => {
    return{
        fetchData: (searchTerm) => dispatch(productsFetchData(searchTerm))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchBar)