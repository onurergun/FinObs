import {React, useState} from "react"
import FormInput from "../components/FormInput";
import FormButton from "../components/FormButton";
import "./signUp.css"

import UserService from "../services/UserService";

function SignUp(props) {
    const [values, setValues] = useState({
        firstName: "",
        lastName: "",
        userName: "",
        email: "",
        password: "",
        passwordRepeat: ""
    });

    function onInputChange(e) {
        // console.log(e.target.id + " - " + e.target.value);
        const {name, value} = e.target;

        setValues(prev => {
            return {
                ...prev,
                [name]: value
            };
        });
    }

    function onButtonClick(event) {
        event.preventDefault();
        console.log("SignUp Button clicked");
        console.log(values);

        UserService.createUser(values)
            .then(response => {
                if (response.status === 200) {
                    console.log("User create request successful");
                } else {
                    console.log("User create request failed: " + response.status + " " + response.statusText);
                    // TODO: Handle errors
                }
            });
    }

    return (
        <div className="container">
            <h1 className="sign-up-heading">Sign Up</h1>
            <form>
                <FormInput labelText={"User Name"}
                           inputType={"text"}
                           inputId={"userName"}
                           inputName={"userName"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"First Name"}
                           inputType={"text"}
                           inputId={"firstName"}
                           inputName={"firstName"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Last Name"}
                           inputType={"text"}
                           inputId={"lastName"}
                           inputName={"lastName"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Email"}
                           inputType={"email"}
                           inputId={"email"}
                           inputName={"email"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Password"}
                           inputType={"password"}
                           inputId={"password"}
                           inputName={"password"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Password Repeat"}
                           inputType={"password"}
                           inputId={"passwordRepeat"}
                           inputName={"passwordRepeat"}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormButton label={"Sign Up"}
                            onClick={onButtonClick}>
                </FormButton>
            </form>
        </div>
    );
}

export default SignUp;