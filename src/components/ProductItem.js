import React, { Component } from "react";
import "../styles/main.css";
import { connect } from "react-redux";
import { detailsFetchData } from "../actions/index";

class ProductItem extends Component {
  handleClick = () => {
    const url = "https://poz-armia-jsona.herokuapp.com//offers/";
    this.props.fetchDetails(url + this.props.id);
  };

  render() {
    const url = this.props.img;

    return (
      <div className="productItem" onClick={this.handleClick}>
        <div className="image">
          <img src={url} />
        </div>
        <p id="description">{this.props.name}</p>
        <p id="price">{this.props.price.amount},-</p>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    details: state.details,
    detailsHasErrored: state.detailsHasErrored,
    detailsIsLoading: state.detailsIsLoading
  };
};

const mapDispatchToProps = dispatch => {
  return {
    fetchDetails: id => dispatch(detailsFetchData(id))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(ProductItem);
