import {React, useState} from "react";

import "./formInput.css"

function FormInputPassword(props) {
    const labelText = props.labelText;
    const inputId = props.inputId;
    const inputName = props.inputName;
    const inputOnChange = props.inputOnChange;
    const inputErrMessage = props.errorMessage;
    const isRequired = props.required;

    const [stateShowPassword, setStateShowPassword] = useState(false);

    function showHidePassword(event) {
        setStateShowPassword(prev => {return !prev});
    }

    return (
        <div className="form-group form-input-parent">
            <label>{labelText}</label>
            <div className="input-group">
                <input className={"form-control " + (inputErrMessage && "is-invalid")}
                       type={stateShowPassword ? "text" : "password"}
                       id={inputId}
                       name={inputName}
                       onChange={inputOnChange}
                       required={isRequired}
                />
                <span className="input-group-text">
                    <i className={stateShowPassword ? "fa-solid fa-eye-slash" : "fa-solid fa-eye"} id="togglePassword" style={{cursor: "pointer"}} onClick={showHidePassword}></i>
                </span>
                <div className="invalid-feedback">{inputErrMessage}</div>
            </div>
        </div>
    );
}

export default FormInputPassword;