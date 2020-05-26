import React from "react";
import Main from "../Main";
import CSSTransitionGroup from 'react-transition-group/CSSTransitionGroup'
import axios from "axios";

class Login extends React.Component {
    constructor(props) {
        super(props);
        let userData = JSON.parse(window.sessionStorage.getItem('userData'));
        if (userData) {
            this.state = {
                isVisible: userData,
                userName: userData.employeeName
            };
        } else {
            this.state = {
                isVisible: false,
                userName: ''
            };
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.setLogout = this.setLogout.bind(this);
    }

    setLogout() {
        this.setState({
            isVisible: false
        });
        window.sessionStorage.setItem('userData', null);
    }

    handleSubmit(e) {
        e.preventDefault();
        const form = e.target;
        const user = {
            employeeID: form.elements['username'].value,
            password: form.elements['password'].value
        };
        axios.post(`/user/validateLogin`, user)
            .then(res => {
                this.setState({
                    isVisible: res.data.employeeName,
                    userName: res.data.employeeName,
                });
                window.sessionStorage.setItem('userData', JSON.stringify(res.data));
            });
        return false;
    }

    render() {
        let component = this.state.isVisible ? <Main user={this.state.userName} setLogout={this.setLogout} /> : <Modal onSubmit={ this.handleSubmit } key='modal'/> ;
        return <CSSTransitionGroup transitionName="animation" transitionAppear={true} transitionAppearTimeout={500} transitionEnterTimeout={500} transitionLeaveTimeout={300}>
            { component }
        </CSSTransitionGroup>
    }
}

class Modal extends React.Component {
    render() {
        return <div className='Modal'>
            <Logo />

            <form onSubmit= { this.props.onSubmit }>
                <Input type='text' id='username' placeholder='username' />
                <Input type='password' id='password' placeholder='password' />
                <button> Sign In</button>
            </form>
        </div>
    }
}

class Input extends React.Component {
    render() {
        return <div className='Input'>
            <input type={ this.props.type } id={ this.props.id } placeholder={ this.props.placeholder } required autocomplete='false'/>
            <label for={ this.props.name } ></label>
        </div>
    }

}

class Logo extends React.Component {
    render() {
        return <div className="logo">
            <img src="../images/IBS_Software.svg"/>
        </div>
    }
}


export default Login;
