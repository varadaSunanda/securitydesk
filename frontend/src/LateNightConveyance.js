import React, {Component} from 'react';
import axios from 'axios';
import Form from "./LNCForm";


class LateNightConveyance extends Component {
    constructor(props) {
        super(props);

        this.state = {
            people: []
        };

        this.addPerson = this.addPerson.bind(this);
        this.deletePerson = this.deletePerson.bind(this);
    }

    componentDidMount() {
        axios.get('/getLNCEntry')
            .then(res => {
                this.setState({people: res.data})
            })
    }

    addPerson(id, name) {
        const bodyFormData = {
            name: name,
            employeeId: id
        };
        axios.get('http://localhost:8080/')
            .then(res => {
                console.log("index");
            })
    }

    deletePerson(id) {
        return () => {
            this.setState(prevState => ({
                people: prevState.people.filter(person => person.id !== id)
            }));
        };
    }
    render() {
        return (
            <section className="smart-filter-content">

                <h2 className="smart-filter-heading">Employee Register</h2>

                        <div>

                            <h2 className="smart-filter-heading">Enter employee details:</h2>

                            <Form addPerson={this.addPerson} />
                        </div>

                <div>
                    <h2 className="smart-filter-heading">Employee List:</h2>
                    <table className="responsive-table">
                        <thead>
                        <tr>
                            <th scope="col">Employee-Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">CheckIn Time</th>
                            <th scope="col">CheckOut</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.people.map((person, index) => {
                            return (
                        <tr key={person.name}>
                            <th scope="row">{person.employeeId}</th>
                            <td data-title="purpose">{person.name}</td>
                            <td data-title="id">{person.checkInTime}</td>
                            <td data-title="checkout button"> <button type="submit" onClick={this.deletePerson(person.employeeId)}>Check out</button></td>
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


export default LateNightConveyance;
