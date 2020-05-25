import React, { Component } from 'react'
import axios from "axios";

class BaggageForm extends Component {
    constructor() {
        super();
        this.formSubmit = this.formSubmit.bind(this);
        this.getTokens = this.getTokens.bind(this);

        this.state = {
            tokens : []
        }
    }

    formSubmit(event) {
        event.preventDefault();
        const form = event.target;
        const id = form.elements["id"].value;
        const name = form.elements["name"].value;
        const token = form.elements["token"].value;
        if (id && name && token) {
            this.props.checkinBaggage(name, id, token);
            form.reset();
        }
    }

    getTokens() {
        axios.get('/token')
            .then(res => {
                this.setState({tokens: res.data})
            })
    }

    render() {
        if(this.props.fetchTokens) {
            this.props.fetchTokens = false;
            this.getTokens();
        }

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
                                <div className="col-md-2">
                                    <div className="row">
                                        <div className="col-md-4">
                                            <p className="input-label blue-tag">Token</p>
                                            <div>
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

export default BaggageForm;
