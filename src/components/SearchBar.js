import React, { Component } from "react";
import { Button, Form, Message } from "semantic-ui-react";
import { connect } from 'react-redux'
import { productsFetchData } from "../actions/index";
import PropTypes from "prop-types";


const style = {
    width: '50%',
    display: 'inline-block'
}

const buttonStyle = {
    width: '50%',
    display: 'inline'
}

class SearchBar extends Component {
state = {
    searchTerm: '',
}



onChange = e => this.setState({searchTerm: e.target.value})

  render() {
    return (
            <Form style ={style}>
                <Form.Field>
                <input
                    type="text"
                    id="searchTerm"
                    name="searchTerm"
                    placeholder="Czego szukasz?"
                    onChange = {this.onChange}
                    //onSubmit = {this.props.fetchData('http://jsonplaceholder.typicode.com/posts')}
                    onSubmit = {this.props.fetchData(`https://allegroapi.io/offers?phrase=${this.state.searchTerm}`)}
                />
                </Form.Field>
                <Button style = {buttonStyle} >Szukaj</Button>
            </Form>
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