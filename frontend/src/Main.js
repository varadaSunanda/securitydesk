import React, {Component} from "react";

import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";

import Visitor from "./Visitor";
import Baggage from "./Baggage";
import Dresscode from "./Dresscode";
import Vampire from "./Vampire";
import { library } from '@fortawesome/fontawesome-svg-core'
import { faSearch } from '@fortawesome/free-solid-svg-icons'

library.add(faSearch)

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            displayCategory: "all",
            products: props.products,
            productCategories: props.productCategories
        };
        this.setCategory = this.setCategory.bind(this);
    }
    setCategory(category) {
        this.setState({
            displayCategory: category
        });
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
                                                Shekhar R <b class="caret"></b></a>
                                            <ul className="dropdown-menu">
                                                <li><a href="#">Account Settings</a></li>
                                                <li><a href="#">Log out</a></li>
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
                                                        <NavLink exact to="/">
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
                                                        <NavLink exact to="/vampires">
                                                            <p>Late night stay</p>
                                                        </NavLink>
                                                    </li>
                                                </ul>
                                            </div>
                                        </nav>
                                    </div>
                                    <div className="smart-filter-section col-md-10">
                                        <Route exact path="/" component={Visitor}/>
                                        <Route path="/dresscode" component={Dresscode}/>
                                        <Route path="/baggage" component={Baggage}/>
                                        <Route path="/vampires" component={Vampire}/>
                                    </div>
                                    <div className="clearfix"></div>
                                </article>
                            </section>
                        </div>
                    </section>

                </div>
            </HashRouter>
        );
    }
}

export default Main;