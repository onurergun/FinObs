import {React, useState} from "react"
import FormInput from "../components/FormInput";
import FormInputPassword from "../components/FormInputPassword";
import FormButton from "../components/FormButton";
import "./signUp.css"

import UserService from "../services/UserService";

function SignUp(props) {
    const [stateValues, setStateValues] = useState({
        firstName: "",
        lastName: "",
        userName: "",
        email: "",
        password: "",
        passwordRepeat: ""
    });

    const [stateErrors, setStateErrors] = useState({
        firstNameErr: undefined,
        lastNameErr: undefined,
        userNameErr: undefined,
        emailErr: undefined,
        passwordErr: undefined,
        passwordRepeatErr: undefined
    });

    const [stateProcessing, setStateProcessing] = useState(false);

    function setFormErrors(resData) {
        let updatedErrors = {
            firstNameErr: undefined,
            lastNameErr: undefined,
            userNameErr: undefined,
            emailErr: undefined,
            passwordErr: undefined,
            paswordRepeatErr: undefined
        };

        if (resData) {
            const firstNameError = resData.errors.firstName;
            const lastNameError = resData.errors.lastName;
            const userNameError = resData.errors.userName;
            const emailError = resData.errors.email;
            const passwordError = resData.errors.password;

            updatedErrors = {
                firstNameErr: firstNameError,
                lastNameErr: lastNameError,
                userNameErr: userNameError,
                emailErr: emailError,
                passwordErr: passwordError,
                paswordRepeatErr: undefined
            };
        }

        setStateErrors(prev => {
            return {
                ...prev,
                ...updatedErrors
            }
        });
    }

    function onButtonClick(event) {
        console.log("SignUp Button clicked");
        console.log(stateValues);

        event.preventDefault();
        setStateProcessing(true);
        setFormErrors(undefined);
        UserService.createUser(stateValues)
            .then(response => {
                if (response.status === 200) {
                    console.log("User create request successful");
                } else {
                    console.log("User create request failed: " + response.status + " " + response.statusText);
                    setFormErrors(response.data);
                }
                setStateProcessing(false);
            });
    }

    function onInputChange(e) {
        // console.log(e.target.id + " - " + e.target.value);
        const {name, value} = e.target;

        setStateValues(prev => {
            return {
                ...prev,
                [name]: value
            };
        });

        const errName = name + "Err";
        setStateErrors(prev => {return {...prev, [errName]: undefined}});

        if (name === "password" || name === "passwordRepeat") {
            if (name === "password") {
                setStateErrors(prev => {return {...prev, passwordRepeatErr: (value !== stateValues.passwordRepeat ? "does not match" : undefined)}});
            } else if (name === "passwordRepeat") {
                setStateErrors(prev => {return {...prev, passwordRepeatErr: (value !== stateValues.password ? "does not match" : undefined)}});
            }
        }
    }

    return (
        <div className="container">
            <h1 className="sign-up-heading">Sign Up</h1>
            <form>
                <FormInput labelText={"User Name"}
                           inputType={"text"}
                           inputId={"userName"}
                           inputName={"userName"}
                           errorMessage={stateErrors.userNameErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"First Name"}
                           inputType={"text"}
                           inputId={"firstName"}
                           inputName={"firstName"}
                           errorMessage={stateErrors.firstNameErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Last Name"}
                           inputType={"text"}
                           inputId={"lastName"}
                           inputName={"lastName"}
                           errorMessage={stateErrors.lastNameErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInput labelText={"Email"}
                           inputType={"email"}
                           inputId={"email"}
                           inputName={"email"}
                           errorMessage={stateErrors.emailErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInput>
                <FormInputPassword labelText={"Password"}
                           inputId={"password"}
                           inputName={"password"}
                           errorMessage={stateErrors.passwordErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInputPassword>
                <FormInputPassword labelText={"Password Repeat"}
                           inputId={"passwordRepeat"}
                           inputName={"passwordRepeat"}
                           errorMessage={stateErrors.passwordRepeatErr}
                           required={true}
                           inputOnChange={onInputChange}>
                </FormInputPassword>
                <FormButton label={"Sign Up"}
                            processing={stateProcessing}
                            onClick={onButtonClick}>
                </FormButton>
            </form>
        </div>
    );
}

export default SignUp;