import React from "react";
import Main from "../Main";
import CSSTransitionGroup from 'react-transition-group/CSSTransitionGroup'
import axios from "axios";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isVisible: true,
            userName: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const form = e.target;
        const user = {
            username: form.elements['username'].value,
            password: form.elements['password'].value
        };
        this.setState({
            isVisible: false,
            userName: 'Shekhar'
        });
        /*axios.get(`/validateLogin/${user}`)
            .then(res => {
                this.setState({
                    isVisible: true,
                    userName: 'Shekhar'
                });
            });*/
        return false;
    }

    render() {
        let component = this.state.isVisible ? <Modal onSubmit={ this.handleSubmit } key='modal'/> : <Main user={this.state.userName} />;
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
