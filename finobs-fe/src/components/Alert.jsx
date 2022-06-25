import React from 'react';
import "./alert.css"

function Alert (props) {
    const {message} = props;

    return (
        <div className="alert alert-danger alert-parent text-center">
            {/*<svg className="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">*/}
            {/*    <use xlink:href="#exclamation-triangle-fill"/>*/}
            {/*</svg>*/}
            <div className="text-center">
                <span className="fa-solid fa-circle-exclamation mx-0"></span>
                <span className="mx-3">{message}</span>
            </div>
        </div>
    );
}

export default Alert;