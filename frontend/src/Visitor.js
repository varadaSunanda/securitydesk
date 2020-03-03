import React, {Component} from 'react';
import Form from "./Form";

class Visitor extends Component {
    constructor(props) {
        super(props);

        this.state = {
            people: []
        };

        this.addPerson = this.addPerson.bind(this);
        this.deletePerson = this.deletePerson.bind(this);
    }

    addPerson(name, id, purpose) {
        this.setState(prevState => ({
            people: [...prevState.people, {name, id, purpose}]
        }));
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
                        {this.state.people.map((person, index) => {
                            return (
                        <tr key={person.name}>
                            <th scope="row">{person.name}</th>
                            <td data-title="purpose">{person.purpose}</td>
                            <td data-title="id">{person.id}</td>
                            <td data-title="checkout button"> <button type="submit" onClick={this.deletePerson(person.id)}>Check out</button></td>
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