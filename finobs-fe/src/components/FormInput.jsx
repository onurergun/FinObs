import {React} from "react";
import "./formInput.css"

function FormInput(props) {
    const labelText = props.labelText;
    const inputType = props.inputType;
    const inputId = props.inputId;
    const inputName = props.inputName;
    const inputOnChange = props.inputOnChange;
    const inputErrMessage = props.errorMessage;
    const isRequired = props.required;

    return (
        <div className="form-group form-input-parent">
            <label>{labelText}</label>
            <input className={"form-control " + (inputErrMessage && "is-invalid")}
                   type={inputType}
                   id={inputId}
                   name={inputName}
                   onChange={inputOnChange}
                   required={isRequired}
            />
            <div className="invalid-feedback">{inputErrMessage}</div>
        </div>
    );
}

export default FormInput;