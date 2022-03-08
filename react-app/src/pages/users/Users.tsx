import React from "react";
import axios from 'axios';

interface Profile {
    id: string;
    firstName: string;
    lastName: string;
}

export class Users extends React.Component<any, { profiles: any }> {

    public constructor(props: any) {
        super(props);

        this.state = {profiles: []};
    }

    public componentDidMount(): void {
        axios.get<Profile[]>(`https://jsonplaceholder.typicode.com/users`)
            .then(response => {
                const profiles = response.data;
                console.log(response.data)
                this.setState({profiles});
            })
    }

    public render(): JSX.Element {
        return <ul>
            {
                this.state.profiles
                    .map((profile: { id: React.Key | null | undefined; name: boolean | React.ReactChild | React.ReactFragment | React.ReactPortal | null | undefined; }) =>
                        <li key={profile.id}>{profile.name}</li>
                    )
            }
        </ul>;
    }
}