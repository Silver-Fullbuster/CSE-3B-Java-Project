import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import Table from './Table.js';

import Pusher from 'pusher-js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {rows: []};
    // this.onSort = this.onSort.bind(this)
    
    this.insert = this.insert.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
  }
    
  componentDidMount() {
    this.pusher = new Pusher('ca81d4b257e5d3e65a84', {
	  cluster: 'ap2',
      encrypted: true,
    });
    this.channel = this.pusher.subscribe('highscores');
	
    this.channel.bind('insert', this.insert);
    this.channel.bind('update', this.update);
    this.channel.bind('delete', this.delete);
  }

  insert(data) {
    this.setState(prevState => ({
      rows: [ data, ...prevState.rows ]
    }));
  }

  update(data) {
    this.setState(prevState => ({
      rows: prevState.rows.map(el => 
              el.id === data.id ? data : el
      )
    }));
  }

  delete(id) {
    this.setState(prevState => ({
      rows: prevState.rows.filter(el => el.id !== String(id))
    }));
  }

  // onSort(event, sortKey){
  //   /*
  //   assuming your data is something like
  //   [
  //     {accountname:'foo', negotiatedcontractvalue:'bar'},
  //     {accountname:'monkey', negotiatedcontractvalue:'spank'},
  //     {accountname:'chicken', negotiatedcontractvalue:'dance'},
  //   ]
  //   */
  //   const data = this.state.data;
  //   data.sort((a,b) => a[sortKey].localeCompare(b[sortKey]))
  //   this.setState({data})
  // }
    
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" /> 
          <h1 className="App-title" style={{marginTop: 0.8 + 'em'}}>SCOREBOARD</h1>
        </header>
        <div class="container-fluid">
          <Table rows={this.state.rows} />
        </div>
      </div>
    );
  }
}

export default App;
