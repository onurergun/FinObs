import React, {useState} from 'react';

import "./login.css"
import FormInput from "../components/FormInput";
import FormInputPassword from "../components/FormInputPassword";
import FormButton from "../components/FormButton";
import AuthService from "../services/AuthService";
import Alert from "../components/Alert";

function Login(props) {
    const [stateValues, setStateValues] = useState({
        userName: "",
        password: ""
    });

    const [stateError, setStateError] = useState("");

    const [stateProcessing, setStateProcessing] = useState(false);

    const errorMessageDefault = "Invalid username or password";

    function onInputChange(event) {
        const {name, value} = event.target;

        setStateValues(prev => {
            return {
                ...prev,
                [name]: value
            };
        });

        setStateError(undefined);
    }

    function onButtonClick(event) {
        console.log("Login Button clicked");
        console.log(stateValues);

        event.preventDefault();
        setStateProcessing(true);
        AuthService.login(stateValues)
            .then(response => {
                if (response.status === 200) {
                    console.log("User login request successful");
                } else {
                    console.log("User login request failed: " + response.status +" - " +  response.statusText);
                    setStateError(errorMessageDefault);
                    setStateProcessing(false);
                }
            })
            .catch(err => {
                console.log("User login request internal error" + err);
                setStateError(errorMessageDefault + " INVALID");
                setStateProcessing(false);
            });
    }

    return (
        <div className="container mt-5">
            <h1 className="login-heading">Login</h1>
            <form>
                <FormInput labelText={"User Name"}
                           inputType={"text"}
                           inputId={"userName"}
                           inputName={"userName"}
                           errorMessage={undefined}
                           required={true}
                           inputOnChange={onInputChange}/>
                <FormInputPassword labelText={"Password"}
                                   inputId={"password"}
                                   inputName={"password"}
                                   errorMessage={undefined}
                                   required={true}
                                   inputOnChange={onInputChange}/>
                {stateError && <Alert message={stateError}></Alert>}
                <FormButton label={"Login"}
                            processing={stateProcessing}
                            onClick={onButtonClick}/>
            </form>
        </div>
    );
}

export default Login;