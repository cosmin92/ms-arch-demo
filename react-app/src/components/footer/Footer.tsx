import React from "react";
import './Footer.scss';

export class Footer extends React.Component<any, any> {
    public render() {
        return (
            <footer className="page-footer">
                <span>made by </span>
                <a href="https://georgemartsoukos.com/" target="_blank">
                    <img width="24" height="24"
                         src="https://assets.codepen.io/162656/george-martsoukos-small-logo.svg"
                         alt="George Martsoukos logo"/>
                </a>
            </footer>
        );
    }
}