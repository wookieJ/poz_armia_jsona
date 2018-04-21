import React, { Component } from 'react'
import { connect } from "react-redux";
import ProductItem from "./ProductItem";


class ProductList extends Component {
  render() {
    return (
      <div>
       {/* {this.props.products && this.props.products.map(product => <p>{product.name}</p>)} */}
       {this.props.products && this.props.products.map(product => <ProductItem key = {product.id} />)}
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