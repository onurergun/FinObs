import {React} from "react";
import "./formButton.css"

function FormButton(props) {
    const label = props.label;
    const onClick = props.onClick;
    const isProcessing = props.processing;

    return (
        <div className="form-group text-center form-button">

            <button className="btn btn-primary btn-lg" type="button" onClick={onClick} disabled={isProcessing}>
                <span className={"spinner-border spinner-border-sm " + (!isProcessing && "visually-hidden")} role="status" aria-hidden="true"></span>
                <span className="m-3">{label}</span>
            </button>
        </div>
    );
}

export default FormButton;