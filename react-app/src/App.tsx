import React from 'react';
import {Sidebar} from "./components/sidebar/Sidebar";
import {Body} from "./components/body/Body";

export default class App extends React.Component<any, any> {
    // https://codepen.io/tutsplus/pen/gOObdjQ
    public render() {
        return (<div><Sidebar/> <Body/></div>);
    }
}

