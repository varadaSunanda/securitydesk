import React, {Component} from "react";

import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";

import Visitor from "./Visitor/Visitor";
import Baggage from "./Baggage/Baggage";
import Dresscode from "./Dresscode/Dresscode";
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import LateNightConveyance from "./Latenight/LateNightConveyance";

import 'react-notifications/lib/notifications.css';
import { NotificationContainer } from 'react-notifications';

library.add(faSearch)

class Main extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <HashRouter>
                <div className="main">
                    <header>
                        <div className="container-fluid">
                            <div className="row table-class">
                                <div className="logo col-md-6 table-cell-class"><img src="/images/IBS_Software.svg"/></div>
                                <div className="user-profile col-md-6 table-cell-class">
                                    <ul class="nav navbar-nav navbar-right">
                                        <li className="dropdown">
                                            <a href="#" data-toggle="dropdown" className="profile-dropdown dropdown-toggle">
                                                {this.props.user} <b class="caret"></b></a>
                                            <ul className="dropdown-menu">
                                                <li><a onClick={this.props.setLogout}>Log out</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div className="clearfix"></div>
                            </div>
                        </div>
                    </header>

                    <section className="content">
                        <div className="container-fluid">
                            <section className="content-left col-md-12">
                                <article className="smart-filters">
                                    <div className="smart-filter-nav col-md-2">
                                        <nav className="navbar navbar-expand-lg navbar-light bg-light">


                                            <div className="collapse navbar-collapse">
                                                <ul className="nav nav-tabs">
                                                    <li>
                                                        <NavLink exact to="/visitor">
                                                            <p>Visitor</p>
                                                        </NavLink>
                                                    </li>
                                                    <li>
                                                        <NavLink exact to="/dresscode">
                                                            <p>Dress code violation</p>
                                                        </NavLink>
                                                    </li>
                                                    <li>
                                                        <NavLink exact to="/baggage">
                                                            <p>Baggage check-in</p>
                                                        </NavLink>
                                                    </li>
                                                    <li>
                                                        <NavLink exact to="/lateNightConveyance">
                                                            <p>Late night stay</p>
                                                        </NavLink>
                                                    </li>
                                                </ul>
                                            </div>
                                        </nav>
                                    </div>
                                    <div className="smart-filter-section col-md-10">
                                        <Route exact path="/visitor" component={Visitor}/>
                                        <Route path="/dresscode" component={Dresscode}/>
                                        <Route path="/baggage" component={Baggage}/>
                                        <Route path="/lateNightConveyance" component={LateNightConveyance}/>
                                    </div>
                                    <div className="clearfix"></div>
                                </article>
                            </section>
                        </div>
                    </section>
                    <NotificationContainer />
                </div>
            </HashRouter>
        );
    }
}

export default Main;
