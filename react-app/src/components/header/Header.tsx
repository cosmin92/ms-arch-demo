import React from "react";
import './Header.scss';

export class Header extends React.Component<any, any> {
    public render() {
        return (
            <header className="main-header">
                <form className="search-form">
                    <input type="search" placeholder="Search Pages..."/>
                    <button type="submit" aria-label="submit form">
                        <svg aria-hidden="true" viewBox="0 0 16 16">
                            <path
                                d="M6.57,1A5.57,5.57,0,1,1,1,6.57,5.57,5.57,0,0,1,6.57,1m0-1a6.57,6.57,0,1,0,6.57,6.57A6.57,6.57,0,0,0,6.57,0Z"/>
                            <rect x="11.84" y="9.87" width="2" height="5.93"
                                  transform="translate(-5.32 12.84) rotate(-45)"/>
                        </svg>
                    </button>
                </form>
                <div className="admin-profile">
                    <span className="greeting">Hello admin</span>
                    <div className="notifications">
                        <span className="badge">1</span>
                        <svg viewBox="0 0 16 16">
                            <path
                                d="M8,0a8,8,0,1,0,8,8A8,8,0,0,0,8,0ZM8,15a7,7,0,0,1-5.19-2.32,2.71,2.71,0,0,1,1.7-1,13.11,13.11,0,0,0,1.29-.28,2.32,2.32,0,0,0,.94-.34,1.17,1.17,0,0,0-.27-.7h0A3.61,3.61,0,0,1,5.15,7.49,3.18,3.18,0,0,1,8,4.07a3.18,3.18,0,0,1,2.86,3.42,3.6,3.6,0,0,1-1.32,2.88h0a1.13,1.13,0,0,0-.27.69,2.68,2.68,0,0,0,.93.31,10.81,10.81,0,0,0,1.28.23,2.63,2.63,0,0,1,1.78,1A7,7,0,0,1,8,15Z"/>
                        </svg>
                    </div>
                </div>
            </header>
        );
    }
}