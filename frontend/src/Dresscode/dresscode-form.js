import React, { Component } from 'react'
import Select from 'react-select';
import axios from "axios";

class DresscodeForm extends Component {
    constructor() {
        super();
        this.formSubmit = this.formSubmit.bind(this);

        this.state = {
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
        const violation=form.elements["violation"].value;
        if (id && name && violation) {
            this.props.addViolation(id, name,violation);
            form.reset();
            this.setState({selectedOption: null});
        }
    }

    componentDidMount() {
        axios.get('/employee')
            .then(res => {
                this.setState({employees: res.data})
            })
    }



    render() {

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
                                        <div className="col-md-4">
                                            <p className="input-label purple-tag">Employee Name</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="name" type="text" readOnly value={selectedEmp ? selectedEmp.name : null}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-2">
                                    <div className="row">
                                        <div className="col-md-4">
                                            <p className="input-label purple-tag">Violation</p>
                                        </div>
                                        <div className="col-md-8">
                                            <input id="violation" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-2">
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

export default DresscodeForm;
