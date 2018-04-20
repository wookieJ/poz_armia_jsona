import React, { Component } from 'react'
import { connect } from "react-redux";
import ProductItem from "./ProductItem";


class ProductList extends Component {
  render() {
    return (
      <div>
        {this.props.products.map((item) => {
            <ProductItem />
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