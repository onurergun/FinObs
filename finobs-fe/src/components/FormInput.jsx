import {React} from "react";
import "./formInput.css"

function FormInput(props) {
    const labelText = props.labelText;
    const inputType = props.inputType;
    const inputId = props.inputId;
    const inputName = props.inputName;
    const inputOnChange = props.inputOnChange;

    return (
        <div className="form-group form-input-parent">
            <label>{labelText}</label>
            <input className="form-control" type={inputType} id={inputId} name={inputName} onChange={inputOnChange}/>
        </div>
    );
}

export default FormInput;