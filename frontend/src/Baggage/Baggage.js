import React, { Component } from 'react'
import axios from "axios";
import BaggageForm from "./baggage-form";
import { NotificationManager } from 'react-notifications';

class Baggage extends Component {
    constructor(props){
        super(props);

        this.state = {
            baggage : []
        };

        this.checkinBaggage = this.checkinBaggage.bind(this);
        this.checkoutBaggage = this.checkoutBaggage.bind(this);
    }

    componentDidMount() {
        axios.get('/baggage')
            .then(res => {
                this.setState({baggage: res.data})
            })
    }

    checkinBaggage(name, id, token) {
        const bodyFormData = {
            name: name,
            employeeId: id,
            token: token
        };

        axios.post('/baggage', bodyFormData)
            .then(res => {
                this.setState({baggage: res.data});
                NotificationManager.success('Successfully added baggage', 'Successful!', 2000);
            }).catch(err => {
            NotificationManager.error('Employee data already added', 'Error!');
        })
    }

    checkoutBaggage(id) {
        axios.put('/baggage/'+id)
            .then(res => {
                this.setState({baggage: res.data});
                NotificationManager.success('Successfully checkout baggage', 'Successful!', 2000);
            }).catch(err => {
            NotificationManager.error(err.message, 'Error!');
        })
    }


    render() {
        let fetchTokens = true;

        return (
            <section className="smart-filter-content">
                <h2 className="smart-filter-heading">Check-in Baggage</h2>
                <div>
                    <h2 className="smart-filter-heading">Enter details:</h2>
                    <BaggageForm checkinBaggage={this.checkinBaggage} fetchTokens={fetchTokens}/>
                </div>
                <div>
                    <h2 className="smart-filter-heading">Baggage Check-ins</h2>
                    <table className="responsive-table">
                        <thead>
                            <tr>
                                <th scope="col">Employee-Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Token</th>
                                <th scope="col">CheckIn Time</th>
                                <th scope="col">CheckOut</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.state.baggage.map((bag, index) => {
                            return (
                                <tr key={bag.name}>
                                    <th scope="row">{bag.employeeId}</th>
                                    <td data-title="name">{bag.name}</td>
                                    <td data-title="token">{bag.token}</td>
                                    <td data-title="checkInTime">{bag.checkinTime}</td>
                                    <td data-title="checkout button"> <button type="submit" onClick={() => this.checkoutBaggage(bag.employeeId)}>Check out</button></td>
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

export default Baggage;
