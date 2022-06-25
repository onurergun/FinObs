import React from 'react';
import {Link} from "react-router-dom";

function Error(props) {
    return (
        <div className="d-flex align-items-center justify-content-center mt-5 mb-auto">
            <div className="text-center">
                <h1 className="display-1 fw-bold">404</h1>
                <p className="fs-3"><span className="text-danger">Opps!</span> Page not found...</p>
                <p className="lead">
                    The page you’re looking for doesn’t exist.
                </p>
                <Link to="/" className="btn btn-primary btn-lg">Go Home</Link>
            </div>
        </div>
    );
}

export default Error;