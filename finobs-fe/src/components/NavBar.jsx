import {React} from "react"
import {Link} from "react-router-dom";


import "./navBar.css"

function NavBar(props) {
    return (
        <nav className="navbar navbar-light bg-light shadow">
            <div className="container-fluid">
                <Link className="navbar-brand ms-3" to="/">
                    <img src="finobs.png" alt="" width="50" height="50" className="text-center"/>
                    <span className="logo-name mx-3">FinObs</span>
                </Link>

                <div className="d-flex me-5">
                    <span className="navbar-nav mx-3">
                        <Link className="nav-link lead" to="/login">Log In</Link>
                    </span>
                    <span className="navbar-nav mx-3">
                        <Link className="nav-link lead" to="/signup">Sign Up</Link>
                    </span>
                </div>
            </div>
        </nav>
    )
};

export default NavBar;