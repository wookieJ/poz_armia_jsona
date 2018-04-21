import React, { Component } from "react";
import { connect } from "react-redux";
import ProductItem from "./ProductItem";
import { RingLoader } from 'react-spinners';

class ProductList extends Component {
  render() {
    
    return (
      <div className="productsListDiv">
         { this.props.isLoading && <RingLoader color={'#123abc'} loading={this.props.isLoading} />}
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
    isLoading: state.isLoading,
    products: state.products
  };
};

export default connect(mapStateToProps)(ProductList);
