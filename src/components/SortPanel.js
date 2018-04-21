import React, { Component } from "react";
import "../styles/main.css";
import { productsFetchData } from "../actions/index";
import { connect } from "react-redux";

class SortPanel extends Component {
  handleClick(type, term) {
    const url = "https://poz-armia-jsona.herokuapp.com//offers?phrase=";
    switch (type) {
      case "up":
        this.props.fetchData(url + term + "&sort=+price");
        console.log('1');
        
      case "down":
        this.props.fetchData(url + term + "&sort=-price");
        console.log('2');
      case "popUp":
        this.props.fetchData(url + term + "&sort=+popularity");
        console.log('3');
      case "popDown":
        this.props.fetchData(url + term + "&sort=-popularity");
        console.log('4');
      default:
        break;
    }
  }

  render() {
    console.log(this.props.term);

    return (
      <div className="sortPanel">
        <p style={{ fontWeight: "bold" }}>
          Sortuj po: {/* cena */}
          <span
            className="spans"
            onClick={() => this.handleClick("up", this.props.term,)}
          >
            Cena ↗️
          </span>{" "}
          <span
            className="spans"
            onClick={() => this.handleClick("down", this.props.term)}
          >
            Cena ↘️
          </span>
          {/* popularność */}
          <span
            className="spans"
            onClick={() => this.handleClick("popUp", this.props.term)}
          >
            Popularność ↗️
          </span>{" "}
          <span
            className="spans"
            onClick={() => this.handleClick("popDown", this.props.term)}
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
