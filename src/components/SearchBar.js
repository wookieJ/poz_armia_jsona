import React, { Component } from "react";

import { connect } from 'react-redux'
import { productsFetchData } from "../actions/index";
import PropTypes from "prop-types";
import '../styles/main.css'



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

  render() {
    return (
        <div className = 'formDiv'>
            <form>
                <span className = 'logo'> emojillegro </span>
                <input
                    className='input'
                    type="text"
                    id="searchTerm"
                    name="searchTerm"
                    placeholder="Czego szukasz?"
                    onChange = {this.onChange}
                    //onSubmit = {this.props.fetchData('http://jsonplaceholder.typicode.com/posts')}
                    onSubmit = {this.props.fetchData('https://poz-armia-jsona.herokuapp.com/search?name=name')}
                />
                
                <button className = 'button' >Szukaj</button>
            </form>
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