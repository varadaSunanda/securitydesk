import React, { Component } from "react";

class Form extends Component {
    constructor() {
        super();
        this.formSubmit = this.formSubmit.bind(this);
    }

    formSubmit(event) {
        event.preventDefault();
        const form = event.target;
        const id = form.elements["id"].value;
        const name = form.elements["name"].value;
        const purpose = form.elements["purpose"].value;
        if (name && id && purpose) {
            this.props.addPerson(name, id, purpose);
            form.reset();
        }
    }

    render() {
        return (
            <form onSubmit={this.formSubmit}>
                <div className="item active">
                    <div className="row">
                        <div className="employee-detail-form">
                            <div className="row">
                                <div className="col-md-4">
                                    <div className="row">
                                        <div className="col-md-4">
                                            <p className="input-label green-tag">Visitor Name</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="name" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4">
                                    <div className="row">
                                        <div className="col-md-4">
                                            <p className="input-label purple-tag">Purpose</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="purpose" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-3">
                                    <div className="row">
                                        <div className="col-md-6">
                                            <p className="input-label red-tag">Tag ID</p>
                                        </div>
                                        <div className="col-md-6">
                                            <input id="id" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-1">
                                    <button type="submit">ADD</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

export default Form;