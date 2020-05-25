import React, { Component } from "react";

class LateNightForm extends Component {
    constructor() {
        super();
        this.formSubmit = this.formSubmit.bind(this);
    }

    formSubmit(event) {
        event.preventDefault();
        const form = event.target;
        const id = form.elements["id"].value;
        const name = form.elements["name"].value;
        if (id && name) {
            this.props.addPerson(id, name);
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
                                            <p className="input-label green-tag">Employee ID</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="id" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4">
                                    <div className="row">
                                        <div className="col-md-4">
                                            <p className="input-label purple-tag">Employee Name</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="name" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4">
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

export default LateNightForm;
