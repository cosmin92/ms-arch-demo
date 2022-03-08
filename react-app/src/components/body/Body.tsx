import React from "react";
import {Routes, Route} from "react-router-dom";
import {Header} from "../header/Header";
import {Footer} from "../footer/Footer";
import {Users} from "../../pages/users/Users";
import {Dashboard} from "../../pages/dashboard/Dashboard";

export class Body extends React.Component<any, any> {
    public render() {
        return (
            <section className="page-content">
                <Header/>
                <Routes>
                    <Route path="/" element={<Dashboard/>}/>
                    <Route path="/profiles" element={<Users/>}/>
                </Routes>
                <Footer/>
            </section>
        );
    }
}

