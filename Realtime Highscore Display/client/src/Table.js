import React, { Component } from 'react';
import './App.css';

export default class Table extends Component {
    render() {
      const rowsMapped =this.props.rows.map(row => (
            <tr key={row.id}>
              <td>{row.ID}</td>
              <td>{row.Name}</td>
               <td>{row.TimeTaken}</td>
               <td>{row.Type}</td>
            </tr>
          ));
      
      return (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th onClick={e => this.onSort(e, 'TimeTaken')}>Time Taken</th>
              <th>Type</th>
            </tr>
          </thead>
          <tbody>
            {rowsMapped}
          </tbody>
        </table>
      );
    }
}