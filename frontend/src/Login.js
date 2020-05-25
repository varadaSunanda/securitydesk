import React from "react";
import Main from "./Main";
import CSSTransitionGroup from 'react-transition-group/CSSTransitionGroup'


// Main app
class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isVisible: true
        }
        // Bindings
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleRemount = this.handleRemount.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        this.setState({
            isVisible: false
        }, function() {
            console.log(this.state.isVisible)
        });
        return false;
    }
    handleRemount(e) {
        this.setState({
            isVisible: true
        }, function() {
            console.log(this.state.isVisible)
        });
        e.preventDefault();
    }
    render() {

        // const for React CSS transition declaration
        let component = this.state.isVisible ? <Modal onSubmit={ this.handleSubmit } key='modal'/> : <ModalBack onClick={ this.handleRemount } key='bringitback'/>;
        return <CSSTransitionGroup transitionName="animation" transitionAppear={true} transitionAppearTimeout={500} transitionEnterTimeout={500} transitionLeaveTimeout={300}>
            { component }
        </CSSTransitionGroup>
    }
}

// Modal
class Modal extends React.Component {
    render() {
        return <div className='Modal'>
            <Logo />

            <form onSubmit= { this.props.onSubmit }>
                <Input type='text' name='username' placeholder='username' />
                <Input type='password' name='password' placeholder='password' />
                <button> Sign In</button>
            </form>
            <a href='#'>Lost your password ?</a>
        </div>
    }
}

// Generic input field
class Input extends React.Component {
    render() {
        return <div className='Input'>
            <input type={ this.props.type } name={ this.props.name } placeholder={ this.props.placeholder } required autocomplete='false'/>
            <label for={ this.props.name } ></label>
        </div>
    }

}

// Fake logo
class Logo extends React.Component {
    render() {
        return <div className="logo">
            <img src="/images/IBS_Software.svg"/>
        </div>
    }
}

// Button to brind the modal back
class ModalBack extends React.Component {
    render() {
        return <Main/>
    }
}


export default Login;
