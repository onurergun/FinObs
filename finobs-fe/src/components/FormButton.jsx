import {React} from "react";
import "./formButton.css"

function FormButton(props) {
    const label = props.label;
    const onClick = props.onClick;

    return (
        <div className="form-group text-center form-button">
            <button className="btn btn-primary btn-lg" onClick={onClick}>{label}</button>
        </div>
    );
}

export default FormButton;