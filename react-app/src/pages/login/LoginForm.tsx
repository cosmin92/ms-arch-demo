import React from "react";


import './LoginForm.css';

export class LoginForm extends React.Component<any, any> {
    render() {
        return (
            <div className="wrapper">
                <div className='form-container'>
                    <h1>MS-Demo<br/>architecture</h1>
                    <form className={'form'} onSubmit={this.handleSubmit}>
                        <h2>Sign in</h2>
                        <input type="email" name="email" placeholder="Enter your e-mail"/>
                        <input type="password" name="password" placeholder="Enter your password"/>
                        <a className="forgot-password">Forgot password?</a>
                        <input type="submit" className="submit-btn" name="submit"/>
                    </form>
                </div>
            </div>
        );
    }

    public handleSubmit(event: React.FormEvent<HTMLFormElement>): void {
        event.preventDefault();

        console.log(event);
    }
}