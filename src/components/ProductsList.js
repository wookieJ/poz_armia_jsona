import React, { Component } from 'react'
import { connect } from "react-redux";



class ProductList extends Component {
  render() {
    return (
      <div>
        {this.props.products.map((item) => {
            <p> {item.title} </p>
        })}
      </div>
    )
  }
}


const mapStateToProps = (state) => {
    return {
        products: state.products,
    }
}

export default connect (mapStateToProps)(ProductList)