import React, { Component } from "react";
import "../styles/main.css";
import { productsFetchData } from "../actions/index";
import { connect } from "react-redux";

class SortPanel extends Component {

  handlePriceUp(term){
    const url = "https://poz-armia-jsona.herokuapp.com//offers?phrase=";
    this.props.fetchData(url + term + "&sort=+price");
  }

  handlePriceDown(term){
    const url = "https://poz-armia-jsona.herokuapp.com//offers?phrase=";
    this.props.fetchData(url + term + "&sort=-price");
  }

  handlePopUp(term){
    const url = "https://poz-armia-jsona.herokuapp.com//offers?phrase=";
    this.props.fetchData(url + term + "&sort=+popularity");
  }

  handlePopDown(term){
    const url = "https://poz-armia-jsona.herokuapp.com//offers?phrase=";
    this.props.fetchData(url + term + "&sort=-popularity");
  }

  render() {
    console.log(this.props.term);

    return (
      <div className="sortPanel">
        <p style={{ fontWeight: "bold" }}>
          Sortuj po: {/* cena */}
          <span
            className="spans"
            onClick={() => this.handlePriceUp(this.props.term)}
          >
            Cena ↗️
          </span>{" "}
          <span
            className="spans"
            onClick={() => this.handlePriceDown(this.props.term)}
          >
            Cena ↘️
          </span>
          {/* popularność */}
          <span
            className="spans"
            onClick={() => this.handlePopUp(this.props.term)}
          >
            Popularność ↗️
          </span>{" "}
          <span
            className="spans"
            onClick={() => this.handlePopDown(this.props.term)}
          >
            Popularność ↘️
          </span>
        </p>
      </div>
    );
  }
}

const mapDispatchToProps = dispatch => {
  return {
    fetchData: searchTerm => dispatch(productsFetchData(searchTerm))
  };
};

export default connect(null, mapDispatchToProps)(SortPanel);
