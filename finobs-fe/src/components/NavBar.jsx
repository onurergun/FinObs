import {React} from "react"

import "./navBar.css"

function NavBar(props) {
    return (
        <nav className="navbar navbar-light bg-light shadow">
            <div className="container-fluid">
                <a className="navbar-brand ms-3" href="#">
                    <img src="finobs.png" alt="" width="50" height="50" className="text-center"/>
                    <span className="logo-name mx-3">FinObs</span>
                </a>

                <div className="d-flex me-5">
                    <span className="navbar-nav mx-3">
                        <a className="nav-link lead" href="#">Log In</a>
                    </span>
                    <span className="navbar-nav mx-3">
                        <a className="nav-link lead" href="#">Sign Up</a>
                    </span>
                </div>
            </div>
        </nav>
    )
};

export default NavBar;