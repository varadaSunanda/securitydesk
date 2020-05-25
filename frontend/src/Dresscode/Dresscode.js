import React, { Component } from 'react'
import axios from 'axios';
import DresscodeForm from "./dresscode-form";
import {NotificationManager} from "react-notifications";

class Dresscode extends Component {
    constructor(props) {
        super(props);

        this.state = {
            totalViolation: []
        };

       this.addViolation = this.addViolation.bind(this);
       this.deleteEntry = this.deleteEntry.bind(this);
    }

    componentDidMount() {
       this.getData();
    }

    getData = () => {
        axios.get('/getDresscode')
            .then(res => {
                this.setState({totalViolation: res.data});
            });
    };

    addViolation(id, name, violation) {
        const bodyFormData = {
            name: name,
            employeeId: id,
            violation: violation
        };
        axios.post('/addDresscode', bodyFormData)
            .then(res => {
                this.setState({totalViolation: res.data});
                NotificationManager.success('Successfully added violation', 'Successful!', 2000);
            })
    }

    deleteEntry(employeeId,checkinTime) {
        const bodyFormData = {
                   employeeId: employeeId,
                   checkinTime: checkinTime
               };
               axios.post('/deleteDresscode', bodyFormData)
                   .then(res => {
                       this.setState({totalViolation: res.data});
                       NotificationManager.success('Successfully deleted violation', 'Successful!', 2000);
                   })
    }

    render() {
        return (
            <section className="smart-filter-content">
                <h2 className="smart-filter-heading">Dresscode Violations</h2>
                <div>
                    <h2 className="smart-filter-heading">Enter  details:</h2>
                    <DresscodeForm addViolation={this.addViolation} />
                </div>
                <div>
                    <h2 className="smart-filter-heading">Employee List:</h2>
                    <table className="responsive-table">
                       <thead>
                           <tr>
                               <th scope="col">Employee-Id</th>
                               <th scope="col">Name</th>
                               <th scope="col">Violations</th>
                               <th scope="col">Date</th>
                               <th scope="col">Delete</th>
                           </tr>
                       </thead>
                       <tbody>
                          {this.state.totalViolation.map((person, index) => {
                              return (
                                  <tr key={person.name}>
                                      <th scope="row" data-title="employeeId">{person.employeeId}</th>
                                      <td data-title="name">{person.name}</td>
                                      <td data-title="violation">{person.violation}</td>
                                      <td data-title="id">{person.checkinTime}</td>
                                      <td data-title="checkout button"><button type="submit" onClick={(e) => this.deleteEntry(person.employeeId, person.checkinTime)}>delete</button></td>
                                  </tr>
                              );
                          })}
                       </tbody>
                    </table>
                </div>
            </section>
        );
    }
}

export default Dresscode;
