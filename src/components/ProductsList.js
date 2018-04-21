import React, { Component } from "react";
import { connect } from "react-redux";
import ProductItem from "./ProductItem";

class ProductList extends Component {
  render() {
    
    return (
      <div className="productsListDiv">
        {this.props.products &&
          this.props.products.map(product => (
            <ProductItem
              key={product.id}
              name={product.name}
              price={product.price}
              img={product.image}
              id={product.id}
            />
          ))}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    products: state.products
  };
};

export default connect(mapStateToProps)(ProductList);
