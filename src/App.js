import React, { Component } from "react";
import SearchBar from "./components/SearchBar";
import ProductsList from "./components/ProductsList";

class App extends Component {
  render() {
    return (
      <div>
        <SearchBar />
        <ProductsList />
      </div>
    );
  }
}

export default App;
