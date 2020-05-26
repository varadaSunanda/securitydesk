import React, { Component } from 'react'
import axios from "axios";

import Select from 'react-select';

class BaggageForm extends Component {
    constructor() {
        super();
        this.formSubmit = this.formSubmit.bind(this);
        this.getTokens = this.getTokens.bind(this);

        this.state = {
            tokens : [],
            selectedOption : null,
            employees : []
        }
    }

    handleChange = selectedOption => {
        this.setState(
            { selectedOption: selectedOption },
            () => console.log(`Option selected:`, this.state.selectedOption)
        );
    };

    formSubmit(event) {
        event.preventDefault();
        const form = event.target;
        const id = this.state.selectedOption.value;
        const name = form.elements["name"].value;
        const token = form.elements["token"].value;
        if (id && name && token) {
            this.props.checkinBaggage(name, id, token);
            form.reset();
            this.setState({selectedOption: null});
        }
    }

    getTokens() {
        axios.get('/token')
            .then(res => {
                this.setState({tokens: res.data})
            })
    }

    componentDidMount() {
        axios.get('/employee')
            .then(res => {
                    this.setState({employees: res.data})
            })
    }

    render() {

        if(this.props.fetchTokens) {
            this.props.fetchTokens = false;
            this.getTokens();
        }

        const options = this.state.employees.map((employee => {return {"value" : employee.employeeId, "label" : "A-"+employee.employeeId}}));
        const selectedEmp = this.state.employees.find((emp) => {
            if (this.state.selectedOption && emp.employeeId == this.state.selectedOption.value)
                return emp;
        })

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
                                        <Select inputId="id"
                                                value={this.state.selectedOption}
                                                onChange={this.handleChange}
                                                placeholder="Employee ID"
                                                options={options} />
                                    </div>
                                </div>
                                <div className="col-md-4">
                                    <div className="row">
                                        <div className="col-md-6">
                                            <p className="input-label purple-tag">Employee Name</p>
                                        </div>
                                        <div className="col-md-6">
                                            <input id="name" type="text" readOnly value={selectedEmp ? selectedEmp.name : null}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4">
                                    <div className="row">
                                        <div className="col-md-6">
                                            <p className="input-label blue-tag">Token</p>
                                        </div>
                                        <div className="col-md-6">
                                                <select id="token">
                                                    {this.state.tokens.map((token, index) => {
                                                        return (
                                                            <option value={token.tokenId}>{token.tokenId}</option>
                                                        );
                                                    })}
                                                </select>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-2">
                                    <div className="row">
                                    <button type="submit">ADD</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

export default BaggageForm;
