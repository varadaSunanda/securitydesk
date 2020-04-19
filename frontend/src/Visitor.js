import React, {Component} from 'react';
import Form from "./Form";
import axios from 'axios';

class Visitor extends Component {
    constructor(props) {
        super(props);

        this.state = {
            people: []
        };

        this.addPerson = this.addPerson.bind(this);
        this.deletePerson = this.deletePerson.bind(this);
    }

    componentDidMount() {
        axios.get('/getAllVisitor')
            .then(res => {
                this.setState({people: res.data});
            });
    }

    addPerson(name, id, purpose) {
        const people = {
            name: name,
            tagId: id,
            purpose: purpose
        };
        axios.post('/addVisitor', people)
            .then(res => {
                this.setState({people: res.data});
            });
    }

    deletePerson(e, tagId) {
        e.preventDefault();
        axios.get(`/updateVisitor/${tagId}`)
            .then(res => {
                this.setState({people: res.data});
            });
    }
    render() {
        return (
            <section className="smart-filter-content">

                <h2 className="smart-filter-heading">Employee Register</h2>
                <p className="smart-filter-text">Choose from below sections to register.</p>

                        <div>

                            <h2 className="smart-filter-heading">Enter visitor details:</h2>

                            <Form addPerson={this.addPerson} />
                        </div>

                <div>
                    <h2 className="smart-filter-heading">Checked in visitors:</h2>
                    <table className="responsive-table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Purpose</th>
                            <th scope="col">Tag ID</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.people.map((person) => {
                            return (
                        <tr key={person.name}>
                            <th scope="row">{person.name}</th>
                            <td data-title="purpose">{person.purpose}</td>
                            <td data-title="id">{person.tagId}</td>
                            <td data-title="checkout button"> <button type="submit" onClick={(e) => this.deletePerson(e, person.tagId)}>Check out</button></td>
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

export default Visitor;
